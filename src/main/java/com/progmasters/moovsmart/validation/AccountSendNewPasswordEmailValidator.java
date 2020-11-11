package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.SendNewPasswordEmailCommand;
import com.progmasters.moovsmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountSendNewPasswordEmailValidator implements Validator {

    private AccountService accountService;

    @Autowired
    public AccountSendNewPasswordEmailValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SendNewPasswordEmailCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SendNewPasswordEmailCommand sendNewPasswordEmailCommand = (SendNewPasswordEmailCommand) o;

        String email = sendNewPasswordEmailCommand.getEmailReset();

        if (email == null || email.isEmpty()) {
            errors.rejectValue("emailReset", "send.new-password.email.empty");
        } else if (!emailFormatRight(email)) {
            errors.rejectValue("emailReset", "send.new-password.email.invalid-format");
        } else if (!accountService.emailAlreadyRegistered(email)) {
            errors.rejectValue("emailReset", "send.new-password.email.not-registered");
        }
    }

    private boolean emailFormatRight(String email) {
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
