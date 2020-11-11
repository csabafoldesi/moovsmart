package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.security.AuthenticatedAccountDetails;
import com.progmasters.moovsmart.security.UserDetailsImpl;
import com.progmasters.moovsmart.service.AccountService;
import com.progmasters.moovsmart.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService accountService;
    private AccountValidator accountValidator;
    private AccountUpdateValidator accountUpdateValidator;
    private AccountNewPasswordValidator accountNewPasswordValidator;
    private AccountResendEmailValidator accountResendEmailValidator;
    private AccountSendNewPasswordEmailValidator accountSendNewPasswordEmailValidator;

    @Autowired
    public AccountController(AccountService accountService, AccountValidator accountValidator,
                             AccountUpdateValidator accountUpdateValidator,
                             AccountNewPasswordValidator accountNewPasswordValidator,
                             AccountResendEmailValidator accountResendEmailValidator,
                             AccountSendNewPasswordEmailValidator accountSendNewPasswordEmailValidator) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
        this.accountUpdateValidator = accountUpdateValidator;
        this.accountNewPasswordValidator = accountNewPasswordValidator;
        this.accountResendEmailValidator = accountResendEmailValidator;
        this.accountSendNewPasswordEmailValidator = accountSendNewPasswordEmailValidator;
    }

    @InitBinder("accountCreateCommand")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @InitBinder("accountUpdateCommand")
    protected void initBinderUpdate(WebDataBinder binder) {
        binder.addValidators(accountUpdateValidator);
    }

    @InitBinder("accountNewPassword")
    protected void initBinderNewPassword(WebDataBinder binder) {
        binder.addValidators(accountNewPasswordValidator);
    }

    @InitBinder("resendEmailCommand")
    protected void initBinderResend(WebDataBinder binder) {
        binder.addValidators(accountResendEmailValidator);
    }

    @InitBinder("sendNewPasswordEmailCommand")
    protected void initBinderSendNewPassword(WebDataBinder binder) {
        binder.addValidators(accountSendNewPasswordEmailValidator);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthenticatedAccountDetails> getAccountInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        logger.debug("User details requested after successful authentication.");
        return new ResponseEntity<>(new AuthenticatedAccountDetails(userDetails), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<AccountProfileDetails> getAccountProfileInfo() {
        logger.debug("Account details requested for profile page.");
        return new ResponseEntity<>(accountService.getAccountProfileInfo(), HttpStatus.OK);
    }

    @GetMapping("/registration-form-init-data")
    public ResponseEntity<AccountFormInitData> getRegistrationFormInitData() {
        logger.debug("Registration form initial data requested.");
        return new ResponseEntity<>(accountService.getRegistrationFormInitData(), HttpStatus.OK);
    }

    @GetMapping("/new-password-form-data/{newPasswordKey}")
    public ResponseEntity<AccountDetailsNewPasswordFormData> getAccountByNewPasswordKey(@PathVariable("newPasswordKey") String newPasswordKey) {
        logger.debug("Account new password form data requested.");
        return new ResponseEntity<>(accountService.getNewPasswordFormData(newPasswordKey), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin/statistics")
    public ResponseEntity<AccountStatistics> getAccountStatistics() {
        logger.debug("Account statistics requested for admin.");
        return new ResponseEntity<>(accountService.getAccountStatistics(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody @Valid AccountCreateCommand accountCreateCommand) {
        logger.debug("Account creation requested...");
        Long accountId = accountService.createAccount(accountCreateCommand);
        logger.debug("Account successfully created with id {}.", accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> resendActivationEmail(@RequestBody @Valid ResendEmailCommand resendEmailCommand) {
        logger.debug("Activation email resend requested...");
        accountService.resendActivationEmail(resendEmailCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new-password")
    public ResponseEntity<Void> sendNewPasswordEmail(@RequestBody @Valid SendNewPasswordEmailCommand sendNewPasswordEmailCommand) {
        logger.debug("New password email send requested...");
        accountService.sendNewPasswordEmail(sendNewPasswordEmailCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activation/{activationKey}")
    public ResponseEntity<Void> activateAccount(@PathVariable("activationKey") String activationKey) {
        accountService.activateAccount(activationKey);
        logger.debug("Account is activated with activation key: {}", activationKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/new-password/{newPasswordKey}")
    public ResponseEntity<Void> saveNewPassword(@PathVariable("newPasswordKey") String newPasswordKey,
                                                @RequestBody @Valid AccountNewPassword accountNewPassword) {
        accountService.saveNewPassword(newPasswordKey, accountNewPassword);
        logger.debug("New password is saved to account with new password key: {}", newPasswordKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAccount(@RequestBody @Valid AccountUpdateCommand accountUpdateCommand) {
        logger.debug("Account update requested...");
        Long accountId = accountService.updateAccount(accountUpdateCommand);
        logger.debug("Account successfully updated with id {}.", accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/own")
    public ResponseEntity<Void> deleteOwnAccount() {
        if (accountService.deleteOwnAccount()) {
            logger.debug("Account deletion (inactivation) of logged in user has been requested.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.debug("Unable to delete account of logged in user.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
