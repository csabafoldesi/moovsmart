package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.PropertyCondition;
import com.progmasters.moovsmart.domain.PropertyHeating;
import com.progmasters.moovsmart.domain.PropertyParking;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.dto.BatchLoadResult;
import com.progmasters.moovsmart.dto.BatchLoadResultItem;
import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.validation.PropertyFormValidator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@Transactional
public class BatchUploadService {
    private static final Logger logger = LoggerFactory.getLogger(BatchUploadService.class);
    private final String[] columnNames = new String[]{"name", "numberOfRooms", "price", "floorArea", "lotSize", "balconySize",
            "yearBuilt", "description", "propertyType", "propertyCondition", "propertyHeating", "propertyParking",
            "country", "zipCode", "city", "street"};

    private final PropertyFormValidator propertyFormValidator;
    private final PropertyService propertyService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;


    @Autowired
    public BatchUploadService(PropertyFormValidator propertyFormValidator, PropertyService propertyService, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        this.propertyFormValidator = propertyFormValidator;
        this.propertyService = propertyService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.request = request;
    }

    public byte[] getFileForBatchUpload() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            CellStyle notLockedCellStyle = workbook.createCellStyle();
            notLockedCellStyle.setLocked(false);
            notLockedCellStyle.setWrapText(true);
            CellStyle lockedCellStyle = workbook.createCellStyle();
            lockedCellStyle.setLocked(true);

            XSSFSheet sheet = workbook.createSheet("Moovsmart");
            Row row = sheet.createRow(0);

            int columnIndex = 0;
            Cell cell;
            for (String columnName : columnNames) {
                cell = row.createCell(columnIndex);
                cell.setCellValue(columnName);
                cell.setCellStyle(lockedCellStyle);
                sheet.autoSizeColumn(columnIndex);
                columnIndex++;
            }

            sheet.addValidationData(createNumberValidation(sheet, 1));
            sheet.addValidationData(createNumberValidation(sheet, 2));
            sheet.addValidationData(createNumberValidation(sheet, 3));
            sheet.addValidationData(createNumberValidation(sheet, 4));
            sheet.addValidationData(createNumberValidation(sheet, 5));
            sheet.addValidationData(createNumberValidation(sheet, 6));

            String[] options = Arrays.stream(PropertyType.values()).map(PropertyType::getDisplayName).toArray(String[]::new);
            sheet.addValidationData(createValidation(sheet, options, 8));
            options = Arrays.stream(PropertyCondition.values()).map(PropertyCondition::getDisplayName).toArray(String[]::new);
            sheet.addValidationData(createValidation(sheet, options, 9));
            options = Arrays.stream(PropertyHeating.values()).map(PropertyHeating::getDisplayName).toArray(String[]::new);
            sheet.addValidationData(createValidation(sheet, options, 10));
            options = Arrays.stream(PropertyParking.values()).map(PropertyParking::getDisplayName).toArray(String[]::new);
            sheet.addValidationData(createValidation(sheet, options, 11));

            for (int i = 0; i < columnNames.length; i++) {
                sheet.setDefaultColumnStyle(i, notLockedCellStyle);
            }
            sheet.lockFormatColumns(false);
            sheet.protectSheet("");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            logger.debug("XLSX file is generated for batch upload");
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private XSSFDataValidation createValidation(XSSFSheet sheet, String[] options, int rowIndex) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(options);
        CellRangeAddressList columnRange = new CellRangeAddressList(-1, -1, rowIndex, rowIndex);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, columnRange);
        validation.setShowErrorBox(true);
        validation.setEmptyCellAllowed(false);
        return validation;
    }

    private XSSFDataValidation createNumberValidation(XSSFSheet sheet, int rowIndex) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createNumericConstraint(
                XSSFDataValidationConstraint.ValidationType.DECIMAL,
                XSSFDataValidationConstraint.OperatorType.GREATER_OR_EQUAL,
                String.valueOf(0),
                String.valueOf(0)
        );
        CellRangeAddressList columnRange = new CellRangeAddressList(-1, -1, rowIndex, rowIndex);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, columnRange);
        validation.setShowErrorBox(true);
        validation.setEmptyCellAllowed(true);
        return validation;
    }

    public BatchLoadResult parseUploadedFile(MultipartFile upload) {
        BatchLoadResult result;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(upload.getInputStream());
            if (checkWorkbookIsValid(workbook)) {
                result = parseSheet(workbook.getSheetAt(0));
            } else {
                logger.debug("Batch uploading problem. The input file is invalid: wrong headers found.");
                String errorMessage = messageSource.getMessage(new DefaultMessageSourceResolvable("batch-upload.invalid.wrong-headers"), localeResolver.resolveLocale(request));
                result = new BatchLoadResult(errorMessage);
            }
        } catch (Exception e) {
            logger.debug("Batch uploading problem: {}", e.getMessage());
            String errorMessage = messageSource.getMessage(new DefaultMessageSourceResolvable("batch-upload.invalid.wrong-format"), localeResolver.resolveLocale(request));
            result = new BatchLoadResult(errorMessage);
        }
        logger.debug("Batch uploading finished.");
        return result;
    }

    private boolean checkWorkbookIsValid(XSSFWorkbook workbook) {
        boolean isValid = true;
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            isValid = isValid && columnNames[i].equals(row.getCell(i).getStringCellValue());
        }
        return isValid;
    }

    private BatchLoadResult parseSheet(XSSFSheet sheet) {
        BatchLoadResult result = new BatchLoadResult();
        DataFormatter formatter = new DataFormatter(Locale.US);
        int lastRowIndex = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                PropertyForm propertyForm = new PropertyForm();
                try {
                    propertyForm.setNumberOfRooms(Integer.valueOf(formatter.formatCellValue(row.getCell(1))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: numberOfRooms", ex.getMessage()));
                }
                try {
                    propertyForm.setPrice(Integer.valueOf(formatter.formatCellValue(row.getCell(2))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: price", ex.getMessage()));
                }
                try {
                    propertyForm.setFloorArea(Integer.valueOf(formatter.formatCellValue(row.getCell(3))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: floorArea", ex.getMessage()));
                }
                try {
                    propertyForm.setLotSize(Integer.valueOf(formatter.formatCellValue(row.getCell(4))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: lotSize", ex.getMessage()));
                }
                try {
                    propertyForm.setBalconySize(Integer.valueOf(formatter.formatCellValue(row.getCell(5))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: balconySize", ex.getMessage()));
                }
                try {
                    propertyForm.setYearBuilt(Integer.valueOf(formatter.formatCellValue(row.getCell(6))));
                } catch (NumberFormatException ex) {
                    logger.debug(MessageFormat.format("{0}: yearBuilt", ex.getMessage()));
                }

                propertyForm.setName(formatter.formatCellValue(row.getCell(0)));
                propertyForm.setDescription(formatter.formatCellValue(row.getCell(7)));
                propertyForm.setCountry(formatter.formatCellValue(row.getCell(12)));
                propertyForm.setZipCode(formatter.formatCellValue(row.getCell(13)));
                propertyForm.setCity(formatter.formatCellValue(row.getCell(14)));
                propertyForm.setStreet(formatter.formatCellValue(row.getCell(15)));

                String name = formatter.formatCellValue(row.getCell(8));
                PropertyType[] propertyTypeList = Arrays.stream(PropertyType.values())
                        .filter(p -> p.getDisplayName().equals(name)).toArray(PropertyType[]::new);
                if (propertyTypeList.length == 1) {
                    propertyForm.setPropertyType(propertyTypeList[0].toString());
                }

                String name1 = formatter.formatCellValue(row.getCell(9));
                PropertyCondition[] propertyConditionList = Arrays.stream(PropertyCondition.values())
                        .filter(p -> p.getDisplayName().equals(name1)).toArray(PropertyCondition[]::new);
                if (propertyConditionList.length == 1) {
                    propertyForm.setPropertyCondition(propertyConditionList[0].toString());
                }

                String name2 = formatter.formatCellValue(row.getCell(10));
                PropertyHeating[] propertyHeatingList = Arrays.stream(PropertyHeating.values())
                        .filter(p -> p.getDisplayName().equals(name2)).toArray(PropertyHeating[]::new);
                if (propertyHeatingList.length == 1) {
                    propertyForm.setPropertyHeating(propertyHeatingList[0].toString());
                }

                String name3 = formatter.formatCellValue(row.getCell(11));
                PropertyParking[] propertyParkingList = Arrays.stream(PropertyParking.values())
                        .filter(p -> p.getDisplayName().equals(name3)).toArray(PropertyParking[]::new);
                if (propertyParkingList.length == 1) {
                    propertyForm.setPropertyParking(propertyParkingList[0].toString());
                }

                Errors errors = new BeanPropertyBindingResult(propertyForm, PropertyForm.class.getName());
                propertyFormValidator.validate(propertyForm, errors);

                BatchLoadResultItem resultItem = new BatchLoadResultItem(propertyForm, parseErrors(errors));
                result.getResultItems().add(resultItem);

                if (!errors.hasErrors()) {
                    propertyService.createProperty(propertyForm);
                }
            }
        }
        return result;
    }

    private Map<String, String> parseErrors(Errors errors) {
        Map<String, String> errorList = new HashMap<>();
        for (FieldError fieldError : errors.getFieldErrors()) {
            errorList.put(fieldError.getField(), messageSource.getMessage(fieldError, Locale.getDefault()));
        }
        return errorList;
    }

}
