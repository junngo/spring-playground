package com.hello.springplayground.toby.dao;

import com.hello.springplayground.toby.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        // given
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        User user = new User();
        user.setId("junngo");
        user.setName("myeong-jun");
        user.setPassword("spring");

        // when
        dao.add(user);
        User user2 = dao.get(user.getId());

        // then
        assertEquals(dao.getCount(), 1);
        assertEquals(user2.getName(), user.getName());
        assertEquals(user2.getPassword(), user.getPassword());
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        // given
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("kim", "김길동", "spring1");
        User user2 = new User("lee", "이길동", "spring2");
        User user3 = new User("park", "박길동", "spring3");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        // when
        dao.add(user1);
        assertEquals(dao.getCount(), 1);

        dao.add(user2);
        dao.add(user3);

        // then
        assertEquals(dao.getCount(), 3);
    }
}