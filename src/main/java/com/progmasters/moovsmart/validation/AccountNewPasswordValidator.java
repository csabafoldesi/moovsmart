package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.AccountNewPassword;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountNewPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountNewPassword.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountNewPassword accountNewPassword = (AccountNewPassword) o;

        String password = accountNewPassword.getPassword().trim();

        if (password == null || password.equals("")) {
            errors.rejectValue("password", "account.new-password.empty");
        } else if (password.length() < 3 || password.length() > 30) {
            errors.rejectValue("password", "account.new-password.invalid");
        }
    }
}
