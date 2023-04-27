package com.hello.springplayground.toby.service;

import com.hello.springplayground.SpringPlaygroundApplication;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringPlaygroundApplication.class})
class UserServiceTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    UserService userService;
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
                new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
                new User("joytouch", "강명성", "p2", Level.BASIC, 50, 0),
                new User("erwins", "신승환", "p3", Level.SILVER, 50, 29),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void upgradeLevels() {
        dao.deleteAll();
        for (User user : users) {
            dao.add(user);
        }
        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
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
}