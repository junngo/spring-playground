package com.hello.springplayground.toby.dao;

import com.hello.springplayground.toby.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy st = new AddStatement(user);
        jdbcContextWithStatementStrategy(st);
    }

    public User get(String id)  throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        if (user == null)
            throw new EmptyResultDataAccessException(1);

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public void deleteAll() throws SQLException {
        StatementStrategy st = new DeleteAllStatement();    // 선정한 전략 클래스 오브젝트 생성
        jdbcContextWithStatementStrategy(st);               // 오브젝트 주입
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            // 전략패턴 사용 (예외처리 로직은 템플릿 패턴으로 그대로 사용하며, 주입 받은 아래 1라인 전략만 변경)
            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e){}
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e){}
            }
        }
    }
}
