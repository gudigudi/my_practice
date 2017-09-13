package com.gudigudigudi.spring.dao;

import com.gudigudigudi.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // Spring注解：DAO
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private final static     String MATCH_COUNT_SQL="SELECT count(*) FROM t_user WHERE user_name = ? and password = ? ";
    private final static     String UPDATE_LOGIN_INFO_SQL="UPDATE t_user SET last_visit= ?, last_ip=?, credits=? WHERE user_id=? ";


//    @Autowired // 自动注入jdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String password){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,Integer.class,userName,password);
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,user);
    }

    public User findUserByUserName(final String userName){
        final User user=new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(rs.getInt("credits"));
            }
        });

        return user;
    }

}
