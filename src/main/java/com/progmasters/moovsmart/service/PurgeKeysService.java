package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PurgeKeysService {

    private static final Logger logger = LoggerFactory.getLogger(PurgeKeysService.class);
    private AccountService accountService;

    @Autowired
    public PurgeKeysService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeNewPasswordKeys() {
        List<Account> accounts = this.accountService.getAccountListForScheduledKeyPurge();
        for (Account accountToPurge : accounts) {
            accountToPurge.setNewPasswordKey("");
        }
        logger.debug("New password keys have been purged at {}", LocalDateTime.now());
    }
}
