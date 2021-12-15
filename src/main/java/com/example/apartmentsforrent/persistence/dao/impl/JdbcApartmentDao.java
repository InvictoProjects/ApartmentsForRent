package com.example.apartmentsforrent.persistence.dao.impl;

import com.example.apartmentsforrent.persistence.dao.*;
import com.example.apartmentsforrent.persistence.dao.mapper.ApartmentRowMapper;
import com.example.apartmentsforrent.persistence.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcApartmentDao implements ApartmentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcApartmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Apartment create(Apartment entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_APARTMENT);
            statement.setLong(1, entity.getApartmentDetailsId());
            statement.setLong(2, entity.getApartmentDescriptionId());
            statement.setLong(3, entity.getOwnerId());
            return statement;
        }, keyHolder);

        entity.setId((Long) keyHolder.getKey());

        return entity;
    }

    @Override
    public Optional<Apartment> read(Long id) {
        List<Apartment> result =  jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_APARTMENT_BY_ID);
            statement.setLong(1, id);
            return statement;
        }, new ApartmentRowMapper());

        if (result.size() < 1) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    @Override
    public void update(Apartment entity) {
        jdbcTemplate.update(SqlConstants.UPDATE_APARTMENT_BY_ID, entity.getApartmentDetailsId(), entity.getApartmentDescriptionId(),
                entity.getOwnerId(), entity.getId());
    }

    @Override
    public void delete(Apartment entity) {
        jdbcTemplate.update(SqlConstants.DELETE_APARTMENT_BY_ID, entity.getId());
    }

    @Override
    public Optional<Apartment> findByDetailsId(Long id) {
        List<Apartment> result =  jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_APARTMENT_BY_DETAIL_ID);
            statement.setLong(1, id);
            return statement;
        }, new ApartmentRowMapper());

        if (result.size() < 1) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }
}
