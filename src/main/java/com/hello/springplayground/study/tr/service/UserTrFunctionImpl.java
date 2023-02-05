package com.hello.springplayground.study.tr.service;

import com.hello.springplayground.study.tr.domain.UserTr;
import com.hello.springplayground.study.tr.domain.UserTrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserTrFunctionImpl implements UserTrFunction {

    private final UserTrRepository userTrRepository;

    @Override
    @Transactional
    public void save(UserTr user) throws Exception {
        userTrRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserTr user, String changeName) throws Exception {
        UserTr findUser = userTrRepository.findById(user.getId()).orElse(null);
        findUser.setName(changeName);
    }

    @Override
    public void updateFailLog() throws Exception {}
}
