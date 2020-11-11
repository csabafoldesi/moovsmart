package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.domain.AccountStatus;
import com.progmasters.moovsmart.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a from Account a WHERE a.email = :email")
    Optional<Account> findByEmail(@Param("email") String email);

    @Query("SELECT a from Account a WHERE a.activationKey = :activationKey")
    Optional<Account> findByActivationKey(@Param("activationKey") String activationKey);

    @Query("SELECT a from Account a WHERE a.newPasswordKey = :newPasswordKey")
    Optional<Account> findByNewPasswordKey(@Param("newPasswordKey") String newPasswordKey);

    @Query("SELECT a from Account a WHERE a.role = :role ORDER BY a.id DESC")
    List<Account> findAllAdmin(@Param("role") Role role);

    @Query("SELECT count (a.id) from Account a WHERE a.role = :role " +
            "AND a.accountStatus = :accountStatus")
    Long findActiveAccountNumber(@Param("role") Role role,
                                 @Param("accountStatus") AccountStatus accountStatus);

    @Query("SELECT count (a.id) from Account a WHERE a.accountStatus = :accountStatus")
    Long findInactiveAccountNumber(@Param("accountStatus") AccountStatus accountStatus);
}
