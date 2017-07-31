package com.vancuongngo.springwebapp.repository;

import com.vancuongngo.springwebapp.repository.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
