CREATE TYPE building_type AS ENUM ('BRICK', 'FRAME', 'SILICATE_BRICK', 'PANEL', 'FOAM_BLOCK', 'MONOLITH');

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
    address           VARCHAR(50)    NOT NULL UNIQUE ,
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
    details_id     SERIAL NOT NULL REFERENCES details (id) ON DELETE CASCADE,
    description_id SERIAL NOT NULL REFERENCES descriptions (id) ON DELETE CASCADE,
    owner_id       SERIAL NOT NULL REFERENCES owners (id) ON DELETE CASCADE
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO apartments_app;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO apartments_app;
