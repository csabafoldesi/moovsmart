package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.AccountCreateCommand;
import com.progmasters.moovsmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountValidator implements Validator {

    private AccountService accountService;

    @Autowired
    public AccountValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountCreateCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountCreateCommand accountCreateCommand = (AccountCreateCommand) o;

        String fullName = accountCreateCommand.getFullName();
        String email = accountCreateCommand.getEmail();
        String passwordOriginal = accountCreateCommand.getPasswordOriginal().trim();
        String password = accountCreateCommand.getPassword().trim();
        String passwordConfirm = accountCreateCommand.getPasswordConfirm().trim();
        String role = accountCreateCommand.getRole();
        String phoneNumber = accountCreateCommand.getPhoneNumber();

        if (fullName == null || fullName.equals("")) {
            errors.rejectValue("fullName", "account.fullName.empty");
        } else if (fullName.length() < 3 || fullName.length() > 30) {
            errors.rejectValue("fullName", "account.fullName.invalid-length");
        } else if (!fullNameFormatRight(fullName)) {
            errors.rejectValue("fullName", "account.fullName.invalid");
        }

        if (email == null || email.equals("")) {
            errors.rejectValue("email", "account.email.empty");
        } else if (email.length() < 3 || email.length() > 50) {
            errors.rejectValue("email", "account.email.invalid-length");

        } else if (!emailFormatRight(email)) {
            errors.rejectValue("email", "account.email.invalid-format");
        } else if (accountService.emailAlreadyRegistered(email)) {
            errors.rejectValue("email", "account.email.already-registered");
        }

        if (!passwordOriginal.equals("")) {
            errors.rejectValue("password", "account.password.not-empty");
        }

        if (password == null || password.equals("")) {
            errors.rejectValue("password", "account.password.empty");
        } else if (password.length() < 3 || password.length() > 30) {
            errors.rejectValue("password", "account.password.invalid");
        }

        if (passwordConfirm == null || passwordConfirm.equals("")) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.empty");
        } else if (passwordConfirm.length() < 3 || passwordConfirm.length() > 30) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.invalid");
        } else if (!password.equals(passwordConfirm)) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.mismatch");
        }

        if (role == null || role.isEmpty()) {
            errors.rejectValue("role", "account.role.empty");
        } else if (role.length() > 15) {
            errors.rejectValue("role", "account.role.invalid");
        } else if (role.equals("ROLE_ADMIN")) {
            errors.rejectValue("role", "account.role.unauthorized");
        }

        if (phoneNumber != null && !phoneNumber.isEmpty() && !phoneNumberFormatRight(phoneNumber)) {
            errors.rejectValue("phoneNumber", "account.phoneNumber.invalid-format");
        } else if (phoneNumber != null && phoneNumber.length() > 18) {
            errors.rejectValue("phoneNumber", "account.phoneNumber.invalid-length");
        }
    }

    private boolean fullNameFormatRight(String fullName) {
        String regex = "[A-ZÁÉÍÓÖŐÜŰa-záéíóöőúüű]+[ ]{0,1}[A-ZÁÉÍÓÖŐÜŰa-záéíóöőúüű]+[ ]{0,1}[A-ZÁÉÍÓÖŐÜŰa-záéíóöőúüű]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    private boolean emailFormatRight(String email) {
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean phoneNumberFormatRight(String phoneNumber) {
        String regex = "[0-9()\\-+ ]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
