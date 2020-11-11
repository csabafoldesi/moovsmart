package com.progmasters.moovsmart.security;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Optional<Account> optAccount = accountRepository.findByEmail(emailAddress);
        if (optAccount.isEmpty()) {
            throw new UsernameNotFoundException("No account found with email address: " + emailAddress);
        }
        return new UserDetailsImpl(optAccount.get());
    }
}
