package com.hello.springplayground.study.tr.service;

import com.hello.springplayground.study.tr.domain.UserTr;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTrService {

    private final UserTrFunction trFunction;

    @Transactional
    public void save_update(UserTr user, String changeName) throws Exception {
        trFunction.save(user);
        trFunction.update(user, changeName);
    }
}
