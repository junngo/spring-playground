package com.hello.springplayground.study.tr.service;

import com.hello.springplayground.SpringPlaygroundApplication;
import com.hello.springplayground.study.tr.domain.UserTr;
import com.hello.springplayground.study.tr.domain.UserTrRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class UserTrServiceTest {

    @Autowired
    private UserTrService userTrService;

    @Autowired
    private UserTrRepository userTrRepository;

    @Test
    public void save_update_default_success() throws Exception {

        // given
        UserTr user = new UserTr();
        String updateName = "myeong jun";
        user.setId(1L);
        user.setName("jun");

        // when
       userTrService.save_update(user, updateName);

        // then
        UserTr resultUser = userTrRepository.findById(user.getId()).orElse(null);
        Assertions.assertEquals(updateName, resultUser.getName());
    }

//    /**
//     * update 중 예외가 발생하면 save에서 저장 중인 트랜잭션도 롤백
//     */
//    @Test
//    public void save_update_default_rollback() {
//        // given
//        UserTr user = new UserTr();
//        user.setId(2L);
//        user.setName("jun");
//
//        String updateName = "myeong jun";
//
//        // when
//        ApplicationContext ac = new
//                AnnotationConfigApplicationContext(SpringPlaygroundApplication.class, UserTrService1.class);
//        UserTrService userTrService = ac.getBean(UserTrService1.class);
//        Assertions.assertThrows(RuntimeException.class, () -> userTrService.save_update(user, updateName));
//
//        // then
//        UserTr resultUser = userTrRepository.findById(2L).orElse(null);
//        Assertions.assertEquals(null, resultUser);
//    }
//
//    public static class UserTrService1 extends UserTrService {
//
////        @Autowired
////        TrFuncRollback trFuncRollback;
//
////        @Autowired
//        public UserTrService1(TrFuncRollback trFuncRollback) {
//            super(trFuncRollback);
//        }
//    }
//
//    @Component
//    public static class TrFuncRollback extends UserTrFunctionImpl {
//
//        @Autowired
//        private UserTrRepository userTrRepository;
//
//        public TrFuncRollback(UserTrRepository userTrRepository) {
//            super(userTrRepository);
//        }
//
//        @Override
//        @Transactional
//        public void update(UserTr user, String changeName) throws Exception {
//            UserTr findUser = userTrRepository.findById(user.getId()).orElse(null);
//            findUser.setName(changeName);
//            throw new RuntimeException("good");
//        }
//    }

//    /**
//     * update 중 예외가 발생하면 save에서 저장 중인 유저는 저장이 되고
//     * update 중인 작업은 롤백
//     */
//    @Test
//    public void save_success_update_rollback() throws Exception {
//        // given
//        UserTr user = new UserTr();
//        user.setId(2L);
//        user.setName("jun");
//
//        String updateName = "myeong jun";
//
//        // when
////        ApplicationContext ac = new
////                AnnotationConfigApplicationContext(SpringPlaygroundApplication.class, TestUserTrService2.class);
////        UserTrService userTrService = ac.getBean(TestUserTrService2.class);
//        Assertions.assertThrows(RuntimeException.class, () -> userTrService.save_update(user, updateName));
//
//        // then
//        UserTr resultUser = userTrRepository.findById(user.getId()).orElse(null);
//        Assertions.assertEquals("jun", resultUser.getName());
//    }
}