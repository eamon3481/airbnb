package com.team09.airbnb.domain.login;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        String insertUser = "INSERT IGNORE INTO `user`(login, `name`) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertUser);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            return ps;
        });
    }
}
