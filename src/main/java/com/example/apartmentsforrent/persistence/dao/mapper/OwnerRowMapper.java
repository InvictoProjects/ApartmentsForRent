package com.example.apartmentsforrent.persistence.dao.mapper;

import com.example.apartmentsforrent.persistence.model.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRowMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Owner.Builder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setEmail(rs.getString("email"))
                .setPhoneNumber(rs.getString("phone_number"))
                .setPasswordHash(rs.getString("password_hash"))
                .build();
    }
}
