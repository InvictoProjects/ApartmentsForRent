INSERT INTO owners (name, surname, email, phone_number, password_hash)
VALUES ('Сергій',
        'Бабкін',
        'sergii@gmail.com',
        '099 186 1234',
        'password1');

INSERT INTO owners (name, surname, email, phone_number, password_hash)
VALUES ('Олена',
        'Кузминська',
        'olenakuzminska@gmail.com',
        '099 124 1784',
        'password2');

INSERT INTO owners (name, surname, email, phone_number, password_hash)
VALUES ('Василь',
        'Петренко',
        'vasilpetrenko@gmail.com',
        '099 777 6537',
        'password3');

INSERT INTO details (address, year, price, floor, quantity_of_rooms)
VALUES ('Vinnytsia, Kosmonavtiv Ave, 43, apt. 83', '1965-01-01', 4000, 3, 2);

INSERT INTO details (address, year, price, floor, quantity_of_rooms)
VALUES ('Kyiv, Peremohy Street, 5, apt. 46', '1995-01-01', 4000, 3, 2);

INSERT INTO details (address, year, price, floor, quantity_of_rooms)
VALUES ('Zhytomyr, Chornoi Ave, 12, apt. 90', '1958-01-01', 1000, 5, 1);

INSERT INTO details (address, year, price, floor, quantity_of_rooms)
VALUES ('м. Київ, вул. Політехнічна, 41, кв. 316', '1945-01-01', 7000, 4, 1);

INSERT INTO details (address, year, price, floor, quantity_of_rooms)
VALUES ('м. Київ, вул. Банкова, 11, кв. 1', '1936-01-01', 1000000, 6, 10);

INSERT INTO descriptions (condition, type, additional_info)
VALUES ('Дуже хороші умови',
        'Brick',
        NULL);

INSERT INTO descriptions (condition, type, additional_info)
VALUES ('Скоро розвалиться',
        'Foam block',
        'Після того, як ми привернули увагу, погляньте на шедевральні умови в деталях до кімнати');

INSERT INTO descriptions (condition, type, additional_info)
VALUES ('Ремонт найвищого зразка',
        'Silicate brick',
        NULL);

INSERT INTO descriptions (condition, type, additional_info)
VALUES ('Чорновий ремонт',
        'Monolith',
        'Проведено чорновий ремонт, подальше можливо зробити за своїм смаком');

INSERT INTO descriptions (condition, type, additional_info)
VALUES ('Євроремонт',
        'Panel',
        'Квартира розроблена та побудована згідно всіх європейських стандартів');

INSERT INTO apartments (details_id, description_id, owner_id)
VALUES (5, 1, 1);
INSERT INTO apartments (details_id, description_id, owner_id)
VALUES (4, 2, 1);
INSERT INTO apartments (details_id, description_id, owner_id)
VALUES (3, 3, 2);
INSERT INTO apartments (details_id, description_id, owner_id)
VALUES (2, 4, 2);
INSERT INTO apartments (details_id, description_id, owner_id)
VALUES (1, 5, 3);
