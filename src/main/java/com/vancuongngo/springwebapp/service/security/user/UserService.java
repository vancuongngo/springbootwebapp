package com.vancuongngo.springwebapp.service.security.user;

import com.vancuongngo.springwebapp.repository.model.User;
import com.vancuongngo.springwebapp.service.CRUDService;

public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
}
