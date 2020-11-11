package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.ResendEmailCommand;
import com.progmasters.moovsmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountResendEmailValidator implements Validator {

    private AccountService accountService;

    @Autowired
    public AccountResendEmailValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ResendEmailCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResendEmailCommand resendEmailCommand = (ResendEmailCommand) o;

        String email = resendEmailCommand.getEmailResend();
        String activationKey = null;
        if (accountService.emailAlreadyRegistered(email)) {
            activationKey = accountService.findAccountByEmail(email).getActivationKey();
        }

        if (email == null || email.isEmpty()) {
            errors.rejectValue("emailResend", "resend.email.empty");
        } else if (!emailFormatRight(email)) {
            errors.rejectValue("emailResend", "resend.email.invalid-format");
        } else if (!accountService.emailAlreadyRegistered(email)) {
            errors.rejectValue("emailResend", "resend.email.not-registered");
        } else if (activationKey == null || activationKey.equals("")) {
            errors.rejectValue("emailResend", "resend.email.activation-key-not-found");
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
