package com.example.apartmentsforrent.persistence.dao.mapper;

import com.example.apartmentsforrent.persistence.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentRowMapper implements RowMapper<Apartment> {

    @Override
    public Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Apartment.Builder()
                .setId(rs.getLong("id"))
                .setApartmentDetailsId(rs.getLong("details_id"))
                .setApartmentDescriptionId(rs.getLong("description_id"))
                .setOwnerId(rs.getLong("owner_id"))
                .build();
    }
}
