package com.hello.springplayground.toby.service;

import com.hello.springplayground.SpringPlaygroundApplication;
import com.hello.springplayground.toby.dao.UserDao;
import com.hello.springplayground.toby.dao.UserDaoJdbc;
import com.hello.springplayground.toby.domain.Level;
import com.hello.springplayground.toby.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static com.hello.springplayground.toby.service.UserService.MIN_LOGIN_COUNT_FOR_SILVER;
import static com.hello.springplayground.toby.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringPlaygroundApplication.class})
class UserServiceTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    UserService userService;
    @Autowired
    DataSource dataSource;
    @Autowired
    PlatformTransactionManager transactionManager;
    private UserDaoJdbc dao;
    List<User> users;

    @Test
    public void bean() {
        assertNotNull(this.userService);
    }

    @BeforeEach
    public void setUp() {
        this.dao = context.getBean("userDao", UserDaoJdbc.class);
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER-1, 0),
                new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER, 0),
                new User("erwins", "신승환", "p3", Level.SILVER, MIN_RECOMMEND_FOR_GOLD-1, 29),
                new User("madnite1", "이상호", "p4", Level.SILVER, MIN_RECOMMEND_FOR_GOLD, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
        );
    }

    @Test
    public void upgradeLevels() throws Exception{
        dao.deleteAll();
        for (User user : users) {
            dao.add(user);
        }
        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = dao.get(user.getId());
        if (upgraded) {
            assertEquals(user.getLevel().nextLevel(), userUpdate.getLevel());
        } else {
            assertEquals(user.getLevel(), userUpdate.getLevel());
        }
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = dao.get(user.getId());
        assertEquals(userUpdate.getLevel(), expectedLevel);
    }

    @Test
    public void add() {
        dao.deleteAll();
        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User getUserWithLevel = dao.get(userWithLevel.getId());
        User getUserWithoutLevel = dao.get(userWithoutLevel.getId());

        assertEquals(getUserWithLevel.getLevel(), userWithLevel.getLevel());
        assertEquals(getUserWithoutLevel.getLevel(), Level.BASIC);
    }

    static class TestUserServiceException extends RuntimeException {}
    static class TestUserService extends UserService {
        private String id;

        private TestUserService(String id, UserDao userDao, PlatformTransactionManager transactionManager) {
            super(userDao, transactionManager);
            this.id = id;
        }

        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeLevel(user);
        }
    }

    @Test
    public void upgradeAllOrNothing() throws Exception{
        UserService testUserService = new TestUserService(users.get(3).getId(), dao, transactionManager);
        dao.deleteAll();
        for (User user : users) dao.add(user);

        try {
            testUserService.upgradeLevels();
        } catch (TestUserServiceException e) {}
        checkLevelUpgraded(users.get(1), false);
    }
}