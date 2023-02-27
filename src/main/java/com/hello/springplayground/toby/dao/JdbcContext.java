package com.hello.springplayground.toby.dao;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
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

    /**
     * UserDao에 선언하고 사용 가능하지만, 다른 Dao에서도 사용할 수 있도록 JdbcContext에 선언
     * 다른 곳에서도 사용할 수 있도록 지금 처럼 기능 분리들을 고려하자
     * (람다를 사용하니 코드가 훨씬 깔끔해진다)
     *
     * @param query
     * @throws SQLException
     */
    public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(
                c -> c.prepareStatement(query)
        );
    }
}
