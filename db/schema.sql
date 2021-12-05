CREATE TYPE building_type AS ENUM ('Brick', 'Frame', 'Silicate brick', 'Panel', 'Foam block', 'Monolith');

CREATE TABLE descriptions
(
    id              SERIAL PRIMARY KEY,
    condition       VARCHAR(50)   NOT NULL,
    type            building_type NOT NULL,
    additional_info TEXT
);

GRANT ALL PRIVILEGES ON TABLE descriptions to apartments_app;

CREATE TABLE details
(
    id                SERIAL PRIMARY KEY,
    address           VARCHAR(50)    NOT NULL,
    year              DATE           NOT NULL,
    price             decimal(20, 2) NOT NULL,
    floor             INTEGER        NOT NULL,
    quantity_of_rooms INTEGER        NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE details to apartments_app;
