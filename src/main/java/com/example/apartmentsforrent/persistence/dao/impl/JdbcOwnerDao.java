package com.example.apartmentsforrent.persistence.dao.impl;

import com.example.apartmentsforrent.persistence.dao.OwnerDao;
import com.example.apartmentsforrent.persistence.dao.SqlConstants;
import com.example.apartmentsforrent.persistence.dao.mapper.OwnerRowMapper;
import com.example.apartmentsforrent.persistence.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcOwnerDao implements OwnerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOwnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Owner create(Owner entity) {
        String name = entity.getName();
        String surname = entity.getSurname();
        String email = entity.getEmail();
        String phoneNumber = entity.getPhoneNumber();
        String passwordHash = entity.getPasswordHash();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_OWNER);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, passwordHash);
            return statement;
        }, keyHolder);

        entity.setId((Long) keyHolder.getKey());

        return entity;
    }

    @Override
    public Optional<Owner> read(Long id) {
        List<Owner> result = jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_OWNER_BY_ID);
            statement.setLong(1, id);
            return statement;
        }, new OwnerRowMapper());

        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    @Override
    public void update(Owner entity) {
        String name = entity.getName();
        String surname = entity.getSurname();
        String email = entity.getEmail();
        String phoneNumber = entity.getPhoneNumber();
        String passwordHash = entity.getPasswordHash();

        jdbcTemplate.update(SqlConstants.UPDATE_OWNER_BY_ID, name, surname, email, phoneNumber, passwordHash, entity.getId());
    }

    @Override
    public void delete(Owner entity) {
        jdbcTemplate.update(SqlConstants.DELETE_OWNER_BY_ID, entity.getId());
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        List<Owner> result = jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_OWNER_BY_EMAIL);
            statement.setString(1, email);
            return statement;
        }, new OwnerRowMapper());

        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }
}
