package com.example.apartmentsforrent.persistence.dao.impl;

import com.example.apartmentsforrent.persistence.dao.ApartmentDetailsDao;
import com.example.apartmentsforrent.persistence.dao.SqlConstants;
import com.example.apartmentsforrent.persistence.dao.mapper.ApartmentDetailsRowMapper;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcApartmentDetailsDao implements ApartmentDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcApartmentDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ApartmentDetails create(ApartmentDetails entity) {
        String address = entity.getAddress();
        float area = entity.getArea();
        Year buildYear = entity.getBuildYear();
        BigDecimal price = entity.getPrice();
        int floor = entity.getFloor();
        int quantityOfRooms = entity.getQuantityOfRooms();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_DETAILS, new String[] {"id"});
            statement.setString(1, address);
            statement.setFloat(2, area);
            statement.setDate(3, Date.valueOf(buildYear.atDay(1)));
            statement.setBigDecimal(4, price);
            statement.setInt(5, floor);
            statement.setInt(6, quantityOfRooms);
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());

        return entity;
    }

    @Override
    public Optional<ApartmentDetails> read(Long id) {
        List<ApartmentDetails> result = jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_DETAILS_BY_ID);
            statement.setLong(1, id);
            return statement;
        }, new ApartmentDetailsRowMapper());

        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    @Override
    public void update(ApartmentDetails entity) {
        String address = entity.getAddress();
        float area = entity.getArea();
        Year buildYear = entity.getBuildYear();
        BigDecimal price = entity.getPrice();
        int floor = entity.getFloor();
        int quantityOfRooms = entity.getQuantityOfRooms();

        jdbcTemplate.update(SqlConstants.UPDATE_DETAILS_BY_ID, address, area, buildYear, price, floor, quantityOfRooms, entity.getId());
    }

    @Override
    public void delete(ApartmentDetails entity) {
        jdbcTemplate.update(SqlConstants.DELETE_DETAILS_BY_ID, entity.getId());
    }
}
