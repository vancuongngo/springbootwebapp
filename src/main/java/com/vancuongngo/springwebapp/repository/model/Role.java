package com.vancuongngo.springwebapp.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role extends AbstractDomainClass {

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }

        if (!user.getRoles().contains(this)) {
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
