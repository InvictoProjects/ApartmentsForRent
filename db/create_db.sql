CREATE DATABASE apartments_db WITH ENCODING 'UTF8';
CREATE USER apartments_app with password 'apartments';
GRANT ALL PRIVILEGES ON DATABASE apartments_db to apartments_app;
