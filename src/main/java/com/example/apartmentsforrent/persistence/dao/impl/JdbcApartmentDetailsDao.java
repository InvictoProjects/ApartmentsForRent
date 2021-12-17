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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JdbcApartmentDetailsDao implements ApartmentDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcApartmentDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ApartmentDetails> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo,
                                                      Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo, Float areaFrom,
                                                      Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                                                      Year yearOfBuildTo) {
        int offset = (page - 1) * size;
        String statementString = createSearchStatement(size, offset, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);

        return jdbcTemplate.query(statementString, new ApartmentDetailsRowMapper());
    }

    @Override
    public List<ApartmentDetails> findAll() {
        return jdbcTemplate.query(SqlConstants.SELECT_ALL_DETAILS, new ApartmentDetailsRowMapper());
    }

    @Override
    public List<ApartmentDetails> findAll(int page, int size) {
        int toIgnore = (page - 1) * size;
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_DETAILS_WITH_LIMIT);
            statement.setInt(1, size);
            statement.setInt(2, toIgnore);
            return statement;
        }, new ApartmentDetailsRowMapper());
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
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_DETAILS, new String[]{"id"});
            statement.setString(1, address);
            statement.setFloat(2, area);
            statement.setDate(3, Date.valueOf(buildYear.atDay(1)));
            statement.setBigDecimal(4, price);
            statement.setInt(5, floor);
            statement.setInt(6, quantityOfRooms);
            return statement;
        }, keyHolder);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

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

        jdbcTemplate.update(SqlConstants.UPDATE_DETAILS_BY_ID, address, area, buildYear.atDay(1), price, floor, quantityOfRooms, entity.getId());
    }

    @Override
    public void delete(ApartmentDetails entity) {
        jdbcTemplate.update(SqlConstants.DELETE_DETAILS_BY_ID, entity.getId());
    }

    @Override
    public Optional<ApartmentDetails> findByAddress(String address) {
        List<ApartmentDetails> result = jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_DETAILS_BY_ADDRESS);
            statement.setString(1, address);
            return statement;
        }, new ApartmentDetailsRowMapper());

        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    private String createSearchStatement(int limit, int offset, BigDecimal priceFrom, BigDecimal priceTo,
                                         Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo, Float areaFrom,
                                         Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                                         Year yearOfBuildTo) {
        String[] whereSubQueries = new String[]{
                createWhereSubQuery("price", priceFrom != null ? priceFrom.toString() : null, priceTo != null ? priceTo.toString() : null),
                createWhereSubQuery("floor", floorFrom != null ? floorFrom.toString() : null, floorTo != null ? floorTo.toString() : null),
                createWhereSubQuery("area", areaFrom != null ? areaFrom.toString() : null, areaTo != null ? areaTo.toString() : null),
                createWhereSubQuery("year", yearOfBuildFrom != null ? yearOfBuildFrom.toString() : null, yearOfBuildTo != null ? yearOfBuildTo.toString() : null),
                createWhereSubQuery("quantity_of_rooms", quantityOfRoomsFrom != null ? quantityOfRoomsFrom.toString() : null, quantityOfRoomsTo != null ? quantityOfRoomsTo.toString() : null)
        };
        StringBuilder whereQuery = new StringBuilder();
        for (int i = 0; i < whereSubQueries.length; i++) {
            if (!whereSubQueries[i].equals("")) {
                if (i != 0 && !whereSubQueries[i - 1].equals("")) whereQuery.append(" AND ");
                whereQuery.append(whereSubQueries[i]);
            }
        }
        if (whereQuery.length() != 0) whereQuery.insert(0, "WHERE ");
        return "SELECT * FROM details " +
                whereQuery +
                " LIMIT " +
                limit +
                " OFFSET " +
                offset +
                ";";
    }

    private String createWhereSubQuery(String columnName, String valueFrom, String valueTo) {
        StringBuilder subStatement = new StringBuilder();
        if (valueFrom != null && valueTo == null) {
            subStatement.append(columnName)
                    .append(" >= ")
                    .append(valueFrom);
        }
        if (valueFrom == null && valueTo != null) {
            subStatement.append(columnName)
                    .append(" <= ")
                    .append(valueTo);
        }
        if (valueFrom != null && valueTo != null) {
            subStatement.append(columnName)
                    .append(" >= ")
                    .append(valueFrom)
                    .append(" AND ")
                    .append(columnName)
                    .append(" <= ")
                    .append(valueTo);
        }
        return subStatement.toString();
    }
}
