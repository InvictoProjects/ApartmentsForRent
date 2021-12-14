package com.example.apartmentsforrent.persistence.dao.mapper;

import com.example.apartmentsforrent.persistence.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Date;

public class ApartmentRowMapper implements RowMapper<Apartment> {

    @Override
    public Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApartmentDetails details = new ApartmentDetails.Builder()
                .setId(rs.getLong("details_id"))
                .setAddress(rs.getString("address"))
                .setBuildYear(Year.of(rs.getDate("year").toLocalDate().getYear()))
                .setPrice(rs.getBigDecimal("price"))
                .setFloor(rs.getInt("floor"))
                .setArea(rs.getFloat("area"))
                .setQuantityOfRooms(rs.getInt("quantity_of_rooms"))
                .build();

        ApartmentDescription description = new ApartmentDescription.Builder()
                .setId(rs.getLong("description_id"))
                .setCondition(rs.getString("condition"))
                .setBuildingType(BuildingType.valueOf(rs.getString("type")))
                .setAdditionalInfo(rs.getString("additional_info"))
                .build();

        Owner owner = new Owner.Builder()
                .setId(rs.getLong("owner_id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setEmail(rs.getString("email"))
                .setPhoneNumber(rs.getString("phone_number"))
                .setPasswordHash(rs.getString("password_hash"))
                .build();

        return new Apartment.Builder()
                .setId(rs.getLong("id"))
                .setApartmentDetails(details)
                .setApartmentDescription(description)
                .setOwner(owner)
                .build();
    }
}
