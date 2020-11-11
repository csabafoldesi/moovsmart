package com.progmasters.moovsmart.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class AuthenticatedAccountDetails {

    private String emailAddress;

    private String fullName;

    private String role;

    public AuthenticatedAccountDetails() {
    }

    public AuthenticatedAccountDetails(UserDetailsImpl user) {
        this.emailAddress = user.getUsername();
        this.fullName = user.getFullName();
        this.role = findRole(user);
    }

    private String findRole(UserDetails user) {
        String role = null;
        List<GrantedAuthority> roles = user.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .collect(Collectors.toList());
        if (!roles.isEmpty()) {
            role = roles.get(0).getAuthority();
        }
        return role;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }
}
