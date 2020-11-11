package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.dto.MessageForm;
import com.progmasters.moovsmart.service.AccountService;
import com.progmasters.moovsmart.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MessageFormValidator implements Validator {

    private AccountService accountService;
    private PropertyService propertyService;

    @Autowired
    public MessageFormValidator(AccountService accountService, PropertyService propertyService) {
        this.accountService = accountService;
        this.propertyService = propertyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MessageForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MessageForm messageForm = (MessageForm) o;

        Long toId = messageForm.getToId();
        Long propertyId = messageForm.getPropertyId();
        String content = messageForm.getContent().trim();
        Account loggedAccount = accountService.findAccountByEmail(getUserDetails().getUsername());

        if (toId == null) {
            errors.rejectValue("toId", "message.toId.empty");
        } else if (toId <= 0) {
            errors.rejectValue("toId", "message.toId.invalid");
        } else if (toId.equals(loggedAccount.getId())) {
            errors.rejectValue("toId", "message.toId.same-user");
        } else if (!accountService.accountRegistered(toId)) {
            errors.rejectValue("toId", "message.toId.not-existing-account");
        }

        if (propertyId == null) {
            errors.rejectValue("propertyId", "message.propertyId.empty");
        } else if (propertyId <= 0) {
            errors.rejectValue("propertyId", "message.propertyId.invalid");
        } else if (!propertyService.propertyRegistered(propertyId)) {
            errors.rejectValue("propertyId", "message.propertyId.not-existing-property");
        }

        if (content == null || content.equals("")) {
            errors.rejectValue("content", "message.content.empty");
        } else if (content.length() > 2000) {
            errors.rejectValue("content", "message.content.invalid-length");
        }
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
}
