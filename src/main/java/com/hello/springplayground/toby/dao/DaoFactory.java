package com.hello.springplayground.toby.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        userDao.setJdbcContext(jdbcContext());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new GoogleConnectionMaker();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public JdbcContext jdbcContext() {
        JdbcContext jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource());
        return jdbcContext;
    }
}
