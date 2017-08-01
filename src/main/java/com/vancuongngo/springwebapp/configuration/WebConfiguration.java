package com.vancuongngo.springwebapp.configuration;

import com.vancuongngo.springwebapp.service.mapper.CustomWebArgumentResolver;
import com.vancuongngo.springwebapp.service.security.filter.AdminToolFilter;
import com.vancuongngo.springwebapp.service.security.filter.AnotherToolFilter;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /*
    * This registration is made for accessing H2 database console at http://localhost:8080/console
    * */
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.addBasenames("classpath:messages");
        return bean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ServletWebArgumentResolverAdapter(new CustomWebArgumentResolver()));
    }

    @Bean
    public FilterRegistrationBean adminToolFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("adminToolFilter");
        AdminToolFilter adminToolFilter = new AdminToolFilter();
        registrationBean.setFilter(adminToolFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean anotherToolFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("anotherToolFilter");
        AnotherToolFilter anotherToolFilter = new AnotherToolFilter();
        registrationBean.setFilter(anotherToolFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}