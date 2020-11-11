package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.domain.AccountStatus;
import com.progmasters.moovsmart.domain.Role;
import com.progmasters.moovsmart.dto.AccountCreateCommand;
import com.progmasters.moovsmart.repository.AccountRepository;
import com.progmasters.moovsmart.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private AccountCreateCommand accountCreateCommand;
    private AccountService accountService;
    private Account accountToCheckAfterActivation;

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private EmailService emailServiceMock;

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImplMock;

    @Mock
    private AuthenticationManager authenticationManagerMock;

    @BeforeEach
    public void setUp() {
        this.accountCreateCommand = new AccountCreateCommand();
        this.accountCreateCommand.setFullName("Teszt Elek");
        this.accountCreateCommand.setEmail("tesztelek-teszt@gmail.com");
        this.accountCreateCommand.setPassword("tesztelek");
        this.accountCreateCommand.setRole(Role.ROLE_OWNER.toString());

        accountToCheckAfterActivation = new Account();

        this.accountService = new AccountService(accountRepositoryMock, emailServiceMock,
                userDetailsServiceImplMock, authenticationManagerMock);
    }

    @Test
    public void testCreateAccount() {
        ReflectionTestUtils.setField(this.accountService, "passwordEncoder", new BCryptPasswordEncoder());
        when(accountRepositoryMock.save(any(Account.class))).thenAnswer(i -> {
            Account account = (Account) i.getArguments()[0];
            account.setId(1L);
            return account;
        });
        Long savedAccountId = accountService.createAccount(this.accountCreateCommand);
        assertEquals(1, savedAccountId);
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
        verifyNoMoreInteractions(accountRepositoryMock);
    }

    @Test
    public void testActivateAccount() {
        when(accountRepositoryMock.findByActivationKey(any(String.class))).
                thenReturn(Optional.ofNullable(accountToCheckAfterActivation));
        accountToCheckAfterActivation.setAccountStatus(AccountStatus.INACTIVATED);
        accountToCheckAfterActivation.setActivationKey("asdf");

        accountService.activateAccount("asdf");
        AccountStatus accountStatus = accountToCheckAfterActivation.getAccountStatus();
        String activationKey = accountToCheckAfterActivation.getActivationKey();
        assertEquals(AccountStatus.ACTIVATED, accountStatus);
        assertEquals("", activationKey);

        verify(accountRepositoryMock, times(1)).findByActivationKey(any(String.class));
        verifyNoMoreInteractions(accountRepositoryMock);
    }

}
