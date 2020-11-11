package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.AccountUpdateCommand;
import com.progmasters.moovsmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountUpdateValidator implements Validator {

    private AccountService accountService;

    @Autowired
    public AccountUpdateValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountUpdateCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountUpdateCommand accountUpdateCommand = (AccountUpdateCommand) o;

        String fullName = accountUpdateCommand.getFullName();
        String email = accountUpdateCommand.getEmail();
        String passwordOriginal = accountUpdateCommand.getPasswordOriginal().trim();
        String password = accountUpdateCommand.getPassword().trim();
        String passwordConfirm = accountUpdateCommand.getPasswordConfirm().trim();
        String role = accountUpdateCommand.getRole();
        String phoneNumber = accountUpdateCommand.getPhoneNumber();

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
        } else if (accountService.updateEmailAlreadyRegistered(email)) {
            errors.rejectValue("email", "account.email.already-registered");
        } else if (!accountService.emailAlreadyRegistered(email)
                && (password == null || password.equals(""))) {
            errors.rejectValue("email", "account.email.password-necessary");
        }

        if (passwordOriginal == null || (passwordOriginal.equals("")
                && !accountService.emailAlreadyRegistered(email))) {
            errors.rejectValue("passwordOriginal", "account.passwordOriginal.empty");
        } else if (!passwordOriginal.equals("")
                && passwordOriginal.length() < 3 || passwordOriginal.length() > 30) {
            errors.rejectValue("passwordOriginal", "account.passwordOriginal.invalid");
        } else if ((password != null && !password.equals(""))
                || (passwordConfirm != null && !passwordConfirm.equals(""))
                || !accountService.emailAlreadyRegistered(email)) {
            if (passwordOriginal.equals("")) {
                errors.rejectValue("passwordOriginal", "account.passwordOriginal.empty");
            } else if (!accountService.checkOriginalPassword(passwordOriginal)) {
                errors.rejectValue("passwordOriginal", "account.passwordOriginal.not-original");
            }
        } else if ((password == null || password.equals(""))
                && (passwordConfirm == null || passwordConfirm.equals(""))
                && accountService.emailAlreadyRegistered(email)) {
            if (!passwordOriginal.equals("")) {
                errors.rejectValue("passwordOriginal", "account.passwordOriginal.not-necessary");
            }
        }

        if (password == null || (password.equals("")
                && !accountService.emailAlreadyRegistered(email))) {
            errors.rejectValue("password", "account.password.empty");
        } else if (!password.equals("")
                && password.length() < 3 || password.length() > 30) {
            errors.rejectValue("password", "account.password.invalid");
        }

        if (passwordConfirm == null || (passwordConfirm.equals("")
                && !accountService.emailAlreadyRegistered(email))) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.empty");
        } else if (!passwordConfirm.equals("")
                && passwordConfirm.length() < 3 || passwordConfirm.length() > 30) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.invalid");
        } else if (!password.equals(passwordConfirm)) {
            errors.rejectValue("passwordConfirm", "account.passwordConfirm.mismatch");
        }

        if (role == null || role.isEmpty()) {
            errors.rejectValue("role", "account.role.empty");
        } else if (role.length() > 15) {
            errors.rejectValue("role", "account.role.invalid");
        } else if (role.equals("ROLE_ADMIN") && !checkAdminRole(role)) {
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

    private boolean checkAdminRole(String role) {
        boolean isAdminLoggedIn = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals(role))) {
            isAdminLoggedIn = true;
        }
        return isAdminLoggedIn;
    }
}
