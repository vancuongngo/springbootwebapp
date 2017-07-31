package com.vancuongngo.springwebapp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
* This configuration is used for integration testing purpose
* */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.vancuongngo.springwebapp.repository.model"})
@EnableJpaRepositories(basePackages = {"com.vancuongngo.springwebapp.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
