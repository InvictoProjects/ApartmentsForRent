package com.example.apartmentsforrent.persistence.dao.mapper;

import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.BuildingType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentDescriptionRowMapper implements RowMapper<ApartmentDescription> {
    @Override
    public ApartmentDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ApartmentDescription.Builder()
                .setId(rs.getLong("description_id"))
                .setCondition(rs.getString("condition"))
                .setBuildingType(BuildingType.valueOf(rs.getString("type")))
                .setAdditionalInfo(rs.getString("additional_info"))
                .build();
    }
}
