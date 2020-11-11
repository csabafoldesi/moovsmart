package com.progmasters.moovsmart.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class UploadValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommonsMultipartFile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println(o);
    }
}
