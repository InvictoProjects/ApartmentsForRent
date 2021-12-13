package com.example.apartmentsforrent.persistence.dao;

public abstract class SqlConstants {

    public static final String INSERT_APARTMENT = "INSERT INTO apartments (details_id, description_id, owner_id) VALUES (?, ?, ?);";

    public static final String UPDATE_APARTMENT_BY_ID = "UPDATE apartments SET details_id = ?, description_id = ?, " +
            "owner_id = ? WHERE id = ?";

    public static final String DELETE_APARTMENT_BY_ID = "DELETE FROM apartments WHERE id = ?";

    public static final String SELECT_APARTMENT = "SELECT * FROM apartments " +
            "JOIN details d on apartments.details_id = d.id " +
            "JOIN descriptions d2 on d2.id = apartments.description_id " +
            "JOIN owners o on o.id = apartments.owner_id";

    public static final String SELECT_APARTMENT_BY_ID = "SELECT * FROM apartments " +
            "JOIN details d on apartments.details_id = d.id " +
            "JOIN descriptions d2 on d2.id = apartments.description_id " +
            "JOIN owners o on o.id = apartments.owner_id " +
            "WHERE apartments.id = ?;";

    public static final String SELECT_APARTMENT_WITH_LIMIT = "SELECT * FROM apartments " +
            "JOIN details d on apartments.details_id = d.id " +
            "JOIN descriptions d2 on d2.id = apartments.description_id " +
            "JOIN owners o on o.id = apartments.owner_id " +
            "LIMIT ? OFFSET ?;";

}
