CREATE TYPE building_type AS ENUM ('Brick', 'Frame', 'Silicate brick', 'Panel', 'Foam block', 'Monolith');

CREATE TABLE descriptions
(
    id              SERIAL PRIMARY KEY,
    condition       VARCHAR(50)   NOT NULL,
    type            building_type NOT NULL,
    additional_info TEXT
);

CREATE TABLE details
(
    id                SERIAL PRIMARY KEY,
    address           VARCHAR(50)    NOT NULL,
    area              REAL           NOT NULL,
    year              DATE           NOT NULL,
    price             decimal(20, 2) NOT NULL,
    floor             INTEGER        NOT NULL,
    quantity_of_rooms INTEGER        NOT NULL
);

CREATE TABLE owners
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(30) NOT NULL,
    surname       VARCHAR(30) NOT NULL,
    email         VARCHAR(30) NOT NULL UNIQUE,
    phone_number  VARCHAR(30) NOT NULL,
    password_hash VARCHAR(30) NOT NULL
);

CREATE TABLE apartments
(
    id             SERIAL PRIMARY KEY,
    details_id     SERIAL NOT NULL REFERENCES details (id),
    description_id SERIAL NOT NULL REFERENCES descriptions (id),
    owner_id       SERIAL NOT NULL REFERENCES owners (id)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO apartments_app;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO apartments_app;