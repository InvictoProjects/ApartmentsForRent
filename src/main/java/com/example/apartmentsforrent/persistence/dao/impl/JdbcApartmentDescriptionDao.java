package com.example.apartmentsforrent.persistence.dao.impl;

import com.example.apartmentsforrent.persistence.dao.ApartmentDescriptionDao;
import com.example.apartmentsforrent.persistence.dao.SqlConstants;
import com.example.apartmentsforrent.persistence.dao.mapper.ApartmentDescriptionRowMapper;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.BuildingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcApartmentDescriptionDao implements ApartmentDescriptionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcApartmentDescriptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ApartmentDescription create(ApartmentDescription entity) {
        String condition = entity.getCondition();
        BuildingType buildingType = entity.getBuildingType();
        String additionalInfo = entity.getAdditionalInfo();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.INSERT_DESCRIPTION);
            statement.setString(1, condition);
            statement.setString(2, buildingType.getDisplayValue());
            statement.setString(3, additionalInfo);
            return statement;
        }, keyHolder);

        entity.setId((Long) keyHolder.getKey());

        return entity;
    }

    @Override
    public Optional<ApartmentDescription> read(Long id) {
        List<ApartmentDescription> result = jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement(SqlConstants.SELECT_DESCRIPTION_BY_ID);
            statement.setLong(1, id);
            return statement;
        }, new ApartmentDescriptionRowMapper());

        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    @Override
    public void update(ApartmentDescription entity) {
        String condition = entity.getCondition();
        BuildingType buildingType = entity.getBuildingType();
        String additionalInfo = entity.getAdditionalInfo();

        jdbcTemplate.update(SqlConstants.UPDATE_DESCRIPTION_BY_ID, condition, buildingType, additionalInfo, entity.getId());
    }

    @Override
    public void delete(ApartmentDescription entity) {
        jdbcTemplate.update(SqlConstants.DELETE_DESCRIPTION_BY_ID, entity.getId());
    }
}
