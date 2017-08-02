package com.vancuongngo.springwebapp.bootstrap;

import com.vancuongngo.springwebapp.repository.model.Product;
import com.vancuongngo.springwebapp.repository.model.Role;
import com.vancuongngo.springwebapp.repository.model.User;
import com.vancuongngo.springwebapp.repository.ProductRepository;
import com.vancuongngo.springwebapp.service.security.role.RoleService;
import com.vancuongngo.springwebapp.service.security.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/*
* How to run code on start up with Spring Boot: https://springframework.guru/running-code-on-spring-boot-startup/
* */
@Component
@Slf4j
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role " + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role " + adminRole.getRole());
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("1");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("2");
        userService.saveOrUpdate(user2);
    }

    private void loadProducts() {
        Product book = new Product();
        book.setDescription("Toi cua mua he nam ay");
        book.setPrice(new BigDecimal("79"));
        book.setImageUrl("https://tiki.vn/toi-cua-mua-he-nam-ay-p720085.html");
        book.setProductId("1");
        productRepository.save(book);

        log.info("Saved Book - id: " + book.getId());

        Product book2 = new Product();
        book2.setDescription("Thoi gian troi mai");
        book2.setPrice(new BigDecimal("72"));
        book2.setImageUrl("https://tiki.vn/thoi-gian-troi-mai-p343327.html");
        book2.setProductId("2");
        productRepository.save(book2);

        log.info("Saved Book - id:" + book2.getId());
    }
}