package com.hello.springplayground.toby.dao;

public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new GoogleConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
