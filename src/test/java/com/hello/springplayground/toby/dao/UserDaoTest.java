package com.hello.springplayground.toby.dao;

import com.hello.springplayground.SpringPlaygroundApplication;
import com.hello.springplayground.toby.dao.UserDao;
import com.hello.springplayground.toby.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringPlaygroundApplication.class})
class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        System.out.println(this.context);
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("kim", "김길동", "spring1");
        this.user2 = new User("lee", "이길동", "spring2");
        this.user3 = new User("park", "박길동", "spring3");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        // given
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

    @Test
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        // given
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        // when, then
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }
}