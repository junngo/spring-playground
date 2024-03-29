package com.hello.springplayground.toby;

import com.hello.springplayground.toby.dao.DaoFactory;
import com.hello.springplayground.toby.dao.UserDaoJdbc;
import com.hello.springplayground.toby.domain.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class UserApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDao dao = new DaoFactory().userDao();
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDaoJdbc dao = context.getBean("userDao", UserDaoJdbc.class);

        User user = new User();
        user.setId("jun");
        user.setName("name");
        user.setPassword("password");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
