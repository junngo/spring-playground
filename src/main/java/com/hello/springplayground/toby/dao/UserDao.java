package com.hello.springplayground.toby.dao;

import com.hello.springplayground.toby.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;


public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
//        StatementStrategy st = new AddStatement(user);
//        this.jdbcContext.workWithStatementStrategy(st);
//        this.jdbcContext.workWithStatementStrategy(
//                new StatementStrategy() {
//                    @Override
//                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                        PreparedStatement ps = c.prepareStatement(
//                                "insert into users(id, name, password) values(?, ?, ?)"
//                        );
//                        ps.setString(1, user.getId());
//                        ps.setString(2, user.getName());
//                        ps.setString(3, user.getPassword());
//
//                        return ps;
//                    }
//                }
//        );
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }

    public User get(String id)  throws SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?"
        , new Object[] {id}
        , (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }

    public void deleteAll() throws SQLException {
//        StatementStrategy st = new DeleteAllStatement();    // 선정한 전략 클래스 오브젝트 생성
//        this.jdbcContext.workWithStatementStrategy(st);     // 오브젝트 주입
//        this.jdbcContext.executeSql("delete from users");
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() throws SQLException {
        Integer count =  this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
        return count.intValue();
    }
}
