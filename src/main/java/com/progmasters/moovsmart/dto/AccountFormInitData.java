package com.progmasters.moovsmart.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountFormInitData {

    private List<RoleDTO> roles = new ArrayList<>();

    public AccountFormInitData() {
    }

    public AccountFormInitData(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
