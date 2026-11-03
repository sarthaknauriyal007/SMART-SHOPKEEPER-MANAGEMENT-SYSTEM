CREATE DATABASE IF NOT EXISTS smart_shopkeeper_db;

USE smart_shopkeeper_db;

CREATE TABLE IF NOT EXISTS products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    expiry_date DATE
);

SHOW TABLES;

DESCRIBE products;