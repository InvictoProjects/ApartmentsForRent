package com.example.apartmentsforrent.persistence.dao.impl;

import com.example.apartmentsforrent.persistence.dao.ApartmentDao;
import com.example.apartmentsforrent.persistence.dao.SqlConstants;
import com.example.apartmentsforrent.persistence.dao.mapper.ApartmentRowMapper;
import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcApartmentDao implements ApartmentDao {

    private final JdbcTemplate jdbcTemplate;
    private final ApartmentDescriptionDao apartmentDescriptionDao;
    private final ApartmentDetailsDao apartmentDetailsDao;
    private final OwnerDao ownerDao;

    @Autowired
    public JdbcApartmentDao(JdbcTemplate jdbcTemplate, ApartmentDescriptionDao apartmentDescriptionDao,
                            ApartmentDetailsDao apartmentDetailsDao, OwnerDao ownerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.apartmentDescriptionDao = apartmentDescriptionDao;
        this.apartmentDetailsDao = apartmentDetailsDao;
        this.ownerDao = ownerDao;
    }

    @Override
    public List<Apartment> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo,
                                               Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo, Float areaFrom,
                                               Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                                               Year yearOfBuildTo) {
        int offset = (page-1)*size;
        String statementString = createSearchStatement(size, offset, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);

        return jdbcTemplate.query(statementString, new ApartmentRowMapper());
    }

    @Override
    public List<Apartment> findAll() {
        return jdbcTemplate.query(SqlConstants.SELECT_APARTMENT, new ApartmentRowMapper());
    }

    @Override
    public List<Apartment> findAll(int page, int size) {
        int toIgnore = (page-1)*size;
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_APARTMENT_WITH_LIMIT);
            statement.setInt(1, size);
            statement.setInt(2, toIgnore);
            return statement;
        }, new ApartmentRowMapper());
    }

    @Override
    public Apartment create(Apartment entity) {
        Long detailsId;
        Long descriptionId;
        Long ownerId;
        if (entity.getApartmentDetails().getId() == null) {
            ApartmentDetails details = apartmentDetailsDao.create(entity.getApartmentDetails());
            detailsId = details.getId();
        } else {
            detailsId = entity.getApartmentDetails().getId();
        }
        if (entity.getApartmentDescription().getId() == null) {
            ApartmentDescription description = apartmentDescriptionDao.create(entity.getApartmentDescription());
            descriptionId = description.getId();
        } else {
            descriptionId = entity.getApartmentDescription().getId();
        }
        if (entity.getOwner().getId() == null) {
            ApartmentDetails details = apartmentDetailsDao.create(entity.getOwner());
            ownerId = details.getId();
        } else {
            ownerId = entity.getOwner().getId();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_APARTMENT);
            statement.setLong(1, detailsId);
            statement.setLong(2, descriptionId);
            statement.setLong(3, ownerId);
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
        Long detailsId;
        Long descriptionId;
        Long ownerId;
        if (entity.getApartmentDetails().getId() == null) {
            ApartmentDetails details = apartmentDetailsDao.create(entity.getApartmentDetails());
            detailsId = details.getId();
        } else {
            apartmentDetailsDao.update(entity.getApartmentDetails());
            detailsId = entity.getApartmentDetails().getId();
        }
        if (entity.getApartmentDescription().getId() == null) {
            ApartmentDescription description = apartmentDescriptionDao.create(entity.getApartmentDescription());
            descriptionId = description.getId();
        } else {
            apartmentDescriptionDao.update(entity.getApartmentDescription());
            descriptionId = entity.getApartmentDescription().getId();
        }
        if (entity.getOwner().getId() == null) {
            ApartmentDetails details = apartmentDetailsDao.create(entity.getOwner());
            ownerId = details.getId();
        } else {
            ownerDao.update(entity.getOwner());
            ownerId = entity.getOwner().getId();
        }

        jdbcTemplate.update(SqlConstants.UPDATE_APARTMENT_BY_ID, detailsId, descriptionId, ownerId, entity.getId());
    }

    @Override
    public void delete(Apartment entity) {
        jdbcTemplate.update(SqlConstants.DELETE_APARTMENT_BY_ID, entity.getId());
    }

    private String createSearchStatement(int limit, int offset, BigDecimal priceFrom, BigDecimal priceTo,
                                         Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo, Float areaFrom,
                                         Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                                         Year yearOfBuildTo) {
        return "SELECT * FROM apartments " +
                "JOIN details d on apartments.details_id = d.id " +
                "JOIN descriptions d2 on d2.id = apartments.description_id " +
                "JOIN owners o on o.id = apartments.owner_id " + createWhenSubQuery("price", priceFrom.toString(), priceTo.toString()) +
                createWhenSubQuery("floor", floorFrom != null ? floorFrom.toString() : null, floorTo != null ? floorTo.toString() : null) +
                createWhenSubQuery("area", areaFrom != null ? areaFrom.toString() : null, areaTo != null ? areaTo.toString() : null) +
                createWhenSubQuery("year", yearOfBuildFrom != null ? yearOfBuildFrom.toString() : null, yearOfBuildTo != null ? yearOfBuildTo.toString() : null) +
                createWhenSubQuery("quantity_of_rooms", quantityOfRoomsFrom != null ? quantityOfRoomsFrom.toString() : null, quantityOfRoomsTo != null ? quantityOfRoomsTo.toString() : null) +
                "LIMIT " +
                limit +
                " OFFSET " +
                offset +
                ";";
    }

    private String createWhenSubQuery(String columnName, String valueFrom, String valueTo) {
        StringBuilder subStatement = new StringBuilder();
        if (valueFrom != null && valueTo == null) {
            subStatement.append("WHERE ")
                    .append(columnName)
                    .append(" >= ")
                    .append(valueFrom)
                    .append(" ");
        }
        if (valueFrom == null && valueTo != null) {
            subStatement.append("WHERE ")
                    .append(columnName)
                    .append(" <= ")
                    .append(valueTo)
                    .append(" ");
        }
        if (valueFrom != null && valueTo != null) {
            subStatement.append("WHERE ")
                    .append(columnName)
                    .append(" >= ")
                    .append(valueFrom)
                    .append(" AND ")
                    .append(columnName)
                    .append(" <= ")
                    .append(valueTo)
                    .append(" ");
        }
        return subStatement.toString();
    }
}
