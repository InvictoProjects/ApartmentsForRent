package com.example.apartmentsforrent.persistence.dao;

public abstract class SqlConstants {

    public static final String INSERT_APARTMENT = "INSERT INTO apartments (details_id, description_id, owner_id) VALUES (?, ?, ?);";

    public static final String UPDATE_APARTMENT_BY_ID = "UPDATE apartments SET details_id = ?, description_id = ?, " +
            "owner_id = ? WHERE id = ?";

    public static final String DELETE_APARTMENT_BY_ID = "DELETE FROM apartments WHERE id = ?";

    public static final String SELECT_APARTMENT_BY_ID = "SELECT * FROM apartments WHERE apartments.id = ?;";

    public static final String SELECT_APARTMENT_BY_DETAIL_ID = "SELECT * FROM apartments WHERE details_id = ?;";

    public static final String SELECT_ALL_DETAILS = "SELECT * FROM details";

    public static final String SELECT_DETAILS_WITH_LIMIT = "SELECT * FROM details LIMIT ? OFFSET ?;";

    public static final String SELECT_DETAILS_BY_ID = "SELECT * FROM details WHERE id = ?;";

    public static final String INSERT_DETAILS = "INSERT INTO details (address, area, year, price, floor, quantity_of_rooms) " +
            "VALUES (?, ?, ?, ?, ?, ?);";

    public static final String UPDATE_DETAILS_BY_ID = "UPDATE details SET address = ?, area = ?, " +
            "year = ?, price = ?, floor = ?, quantity_of_rooms = ? WHERE id = ?";

    public static final String DELETE_DETAILS_BY_ID = "DELETE FROM details WHERE id = ?";

    public static final String SELECT_DESCRIPTION_BY_ID = "SELECT * FROM descriptions WHERE id = ?;";

    public static final String INSERT_DESCRIPTION = "INSERT INTO descriptions (condition, type, additional_info)" +
            " VALUES (?, ?, ?);";

    public static final String UPDATE_DESCRIPTION_BY_ID = "UPDATE descriptions SET condition = ?, type = ?, " +
            "additional_info = ? WHERE id = ?";

    public static final String DELETE_DESCRIPTION_BY_ID = "DELETE FROM descriptions WHERE id = ?";

    public static final String SELECT_DESCRIPTION_BY_TYPE = "SELECT * FROM descriptions" +
            "WHERE type = ?;";

    public static final String INSERT_OWNER = "INSERT INTO owners (name, surname, email, phone_number, password_hash) " +
            "VALUES (?, ?, ?, ?, ?);";

    public static final String SELECT_OWNER_BY_ID = "SELECT * FROM owners WHERE id = ?;";

    public static final String UPDATE_OWNER_BY_ID = "UPDATE owners SET name = ?, surname = ?, " +
            "email = ?, phone_number = ?, password_hash = ? WHERE id = ?";

    public static final String DELETE_OWNER_BY_ID = "DELETE FROM owners WHERE id = ?";

    public static final String SELECT_OWNER_BY_EMAIL = "SELECT * FROM owners" +
            "WHERE email = ?;";
}
