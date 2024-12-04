package com.rotiez.productservice.dao.mapper;

import com.rotiez.productservice.dao.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("firstName"))
            .lastName(rs.getString("lastName"))
            .middleName(rs.getString("middleName"))
        .build();
    }
}
