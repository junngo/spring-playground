package com.hello.springplayground.study.tr.service;


import com.hello.springplayground.study.tr.domain.UserTr;

public interface UserTrFunction {

    void save(UserTr user) throws Exception;

    void update(UserTr user, String changeName) throws Exception;

    void updateFailLog() throws Exception;
}
