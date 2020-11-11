package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Role;

public class RoleDTO {

    private String role;
    private String displayName;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        this.role = role.toString();
        this.displayName = role.getDisplayName();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
