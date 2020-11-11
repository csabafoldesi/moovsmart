package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.domain.Role;
import com.progmasters.moovsmart.dto.AccountCreateCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

    private AccountCreateCommand accountCreateCommand;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        this.accountCreateCommand = new AccountCreateCommand();
        this.accountCreateCommand.setFullName("Teszt Elek");
        this.accountCreateCommand.setEmail("tesztelek-teszt@gmail.com");
        this.accountCreateCommand.setPassword("tesztelek");
        this.accountCreateCommand.setRole(Role.ROLE_OWNER.toString());
    }

    @Test
    public void testSave() {
        Account account = new Account(this.accountCreateCommand);
        Account savedAccount = accountRepository.save(account);
        assertEquals(4, savedAccount.getId());
    }

    @Test
    public void testFindByEmail() {
        Optional<Account> optAccountByEmail = accountRepository.findByEmail("moovsmartproject2020.1@gmail.com");
        if (optAccountByEmail.isPresent()) {
            assertEquals("moovsmartproject2020.1@gmail.com", optAccountByEmail.get().getEmail());
            assertEquals("moovsmart", optAccountByEmail.get().getFullName());
        } else {
            throw new EntityNotFoundException("Account not found in test method testFindByEmail().");
        }
    }

    @Test
    public void testFindByEmailNotFound() {
        Optional<Account> optAccountByEmail = accountRepository.findByEmail("notfoundindatabase@gmail.com");
        assertTrue(optAccountByEmail.isEmpty());
    }

    @Test
    public void testFindByActivationKey() {
        Optional<Account> optAccountByActivationKey = accountRepository.findByActivationKey("asdfghjk");
        if (optAccountByActivationKey.isPresent()) {
            assertEquals("test2@test.test", optAccountByActivationKey.get().getEmail());
            assertEquals("Test2 user", optAccountByActivationKey.get().getFullName());
        } else {
            throw new EntityNotFoundException("Account not found in test method testFindByActivationKey().");
        }
    }

    @Test
    public void testFindByActivationKeyNotFound() {
        Optional<Account> optAccountByActivationKey = accountRepository.findByActivationKey("activationkeynotfound");
        assertTrue(optAccountByActivationKey.isEmpty());
    }
}
