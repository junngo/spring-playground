package com.hello.springplayground.toby.dao;

import com.hello.springplayground.SpringPlaygroundApplication;
import com.hello.springplayground.toby.domain.Level;
import com.hello.springplayground.toby.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringPlaygroundApplication.class})
class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    private UserDaoJdbc dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        System.out.println(this.context);
        this.dao = context.getBean("userDao", UserDaoJdbc.class);
        System.out.println(this.dao);

        this.user1 = new User("kim", "김길동", "spring1", Level.BASIC, 1, 0);
        this.user2 = new User("lee", "이길동", "spring2", Level.SILVER, 55, 10);
        this.user3 = new User("park", "박길동", "spring3", Level.GOLD, 100, 40);
    }

    @Test
    public void addAndGet() {
        // given
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        // when
        dao.add(user1);
        User getUser1 = dao.get(user1.getId());

        // then
        assertEquals(dao.getCount(), 1);
        checkSameUser(getUser1, user1);
    }

    @Test
    public void count() {
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
    public void getUserFailure() {
        // given
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        // when, then
        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }

    @Test
    public void getAll() {
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertEquals(users1.size(), 1);
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertEquals(users2.size(), 2);
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));
    }

    @Test
    public void update() {
        dao.deleteAll();
        dao.add(user1);

        user1.setName("홍길동");
        user1.setPassword("springboot");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User user1Update = dao.get(user1.getId());
        checkSameUser(user1, user1Update);
    }

    private void checkSameUser(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getLevel(), user2.getLevel());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getRecommend(), user2.getRecommend());
    }
}