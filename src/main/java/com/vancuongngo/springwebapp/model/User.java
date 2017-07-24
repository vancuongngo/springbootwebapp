package com.vancuongngo.springwebapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User extends AbstractDomainClass {

    private String username;

    @Transient
    private String password;

    private String encryptedPassword;

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles = new ArrayList<>();

    private int failedLoginAttempts = 0;

    public void addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }

        if (!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
