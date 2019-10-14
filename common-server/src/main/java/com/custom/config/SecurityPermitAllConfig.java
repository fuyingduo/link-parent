//package com.custom.config;
//
//import com.custom.config.condition.SecurityPermitAllCondition;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * created by fuyd on 2019-10-10
// */
//@Configuration
//@Conditional(SecurityPermitAllCondition.class)
//public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().csrf().disable();
//    }
//}
