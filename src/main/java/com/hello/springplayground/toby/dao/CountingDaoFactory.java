package com.hello.springplayground.toby.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

//    @Bean
//    public UserDao userDao() {
//        return new UserDao(connectionMaker());
//    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new GoogleConnectionMaker();
    }
}
