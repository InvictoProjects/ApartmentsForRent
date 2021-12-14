package com.example.apartmentsforrent.persistence.dao.mapper;

import com.example.apartmentsforrent.persistence.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class ApartmentDetailsRowMapper implements RowMapper<ApartmentDetails> {

    @Override
    public ApartmentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ApartmentDetails.Builder()
                .setId(rs.getLong("details_id"))
                .setAddress(rs.getString("address"))
                .setBuildYear(Year.of(rs.getDate("year").toLocalDate().getYear()))
                .setPrice(rs.getBigDecimal("price"))
                .setFloor(rs.getInt("floor"))
                .setArea(rs.getFloat("area"))
                .setQuantityOfRooms(rs.getInt("quantity_of_rooms"))
                .build();
    }
}
