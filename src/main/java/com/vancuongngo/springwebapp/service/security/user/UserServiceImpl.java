package com.vancuongngo.springwebapp.service.security.user;

import com.vancuongngo.springwebapp.repository.model.User;
import com.vancuongngo.springwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(new ShaPasswordEncoder(256).encodePassword(domainObject.getPassword(), null));
        }
        return userRepository.save(domainObject);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
