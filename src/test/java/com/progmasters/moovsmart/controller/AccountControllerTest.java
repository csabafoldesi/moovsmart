package com.progmasters.moovsmart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.domain.Role;
import com.progmasters.moovsmart.dto.AccountCreateCommand;
import com.progmasters.moovsmart.dto.ResendEmailCommand;
import com.progmasters.moovsmart.exception.GlobalExceptionHandler;
import com.progmasters.moovsmart.security.UserDetailsImpl;
import com.progmasters.moovsmart.service.AccountService;
import com.progmasters.moovsmart.validation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AccountControllerTest {

    private AccountCreateCommand accountCreateCommand;
    private ResendEmailCommand resendEmailCommand;
    private MockMvc mockMvc;
    private AccountController accountController;

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountUpdateValidator accountUpdateValidator;

    @Autowired
    private AccountNewPasswordValidator accountNewPasswordValidator;

    @Autowired
    private AccountResendEmailValidator accountResendEmailValidator;

    @Autowired
    private AccountSendNewPasswordEmailValidator accountSendNewPasswordEmailValidator;

    @Mock
    private AccountService accountServiceMock;

    @Mock
    private LocaleResolver localeResolverMock;

    @Mock
    private HttpServletRequest requestMock;

    @BeforeEach
    public void setUp() {
        this.accountCreateCommand = new AccountCreateCommand();
        this.accountCreateCommand.setFullName("Teszt Elek");
        this.accountCreateCommand.setEmail("tesztelek-teszt@gmail.com");
        this.accountCreateCommand.setPasswordOriginal("");
        this.accountCreateCommand.setPassword("tesztelek");
        this.accountCreateCommand.setPasswordConfirm("tesztelek");
        this.accountCreateCommand.setRole(Role.ROLE_OWNER.toString());

        this.resendEmailCommand = new ResendEmailCommand();
        this.resendEmailCommand.setEmailResend("test2@test.test");

        this.accountController = new AccountController(accountServiceMock, accountValidator,
                accountUpdateValidator, accountNewPasswordValidator, accountResendEmailValidator,
                accountSendNewPasswordEmailValidator);

        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new GlobalExceptionHandler(messageSource(), localeResolverMock, requestMock))
                .build();

        Account ownerAccount = new Account(this.accountCreateCommand);
        UserDetails ownerUserDetails = new UserDetailsImpl(ownerAccount);
        Authentication auth = new UsernamePasswordAuthenticationToken(ownerUserDetails, null);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void testCreateAccountValidFormData() throws Exception {
        when(accountServiceMock.createAccount(any(AccountCreateCommand.class))).thenReturn(1L);
        this.mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.accountCreateCommand)))
                .andExpect(status().isCreated());

        verify(accountServiceMock, times(1)).createAccount(any(AccountCreateCommand.class));
        verifyNoMoreInteractions(accountServiceMock);
    }

    @Test
    public void testCreateAccountInValidFormData() throws Exception {
        this.accountCreateCommand.setEmail("tesztelek-teszt-gmail.com");
        this.mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.accountCreateCommand)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].field", is("email")))
                .andExpect(jsonPath("$.fieldErrors[0].message", is("It is not en email address!")));

        verifyNoMoreInteractions(accountServiceMock);
    }

    @Test
    public void testGetAccountInfo() throws Exception {
        this.mockMvc.perform(get("/api/accounts/me"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.emailAddress", is("tesztelek-teszt@gmail.com")))
                .andExpect(jsonPath("$.role", is("ROLE_OWNER")));
    }

    @Test
    public void testResendActivationEmailValidAlreadyRegisteredEmailActivationKeyNotNull() throws Exception {
        this.mockMvc.perform(post("/api/accounts/send")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.resendEmailCommand)))
                .andExpect(status().isOk());

        verify(accountServiceMock, times(1)).resendActivationEmail(any(ResendEmailCommand.class));
        verifyNoMoreInteractions(accountServiceMock);
    }

    @Test
    public void testResendActivationEmailNotRegisteredEmail() throws Exception {
        this.resendEmailCommand.setEmailResend("tesztelek-teszt@gmail.com");
        this.mockMvc.perform(post("/api/accounts/send")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.resendEmailCommand)))
                .andExpect(status().isBadRequest());

        verifyNoMoreInteractions(accountServiceMock);
    }

    @Test
    public void testActivateAccount() throws Exception {
        this.mockMvc.perform(put("/api/accounts/activation/asdf"))
                .andExpect(status().isOk());

        verify(accountServiceMock, times(1)).activateAccount(any(String.class));
        verifyNoMoreInteractions(accountServiceMock);
    }

    private MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
