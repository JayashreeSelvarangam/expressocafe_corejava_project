CREATE DATABASE IF NOT EXISTS jayashree_selvarangam_corejava_project;

use jayashree_selvarangam_corejava_project;
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone_number BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    age INT NOT NULL
);
CREATE TABLE category (
    cate_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE size (
    size_id INT PRIMARY KEY AUTO_INCREMENT,
    size_name VARCHAR(50) NOT NULL
);

CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    category_id INT,
    created_date DATETIME NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (cate_id)
);

CREATE TABLE price (
    price_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    size_id INT NOT NULL,
    price DOUBLE NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME,
    FOREIGN KEY (product_id) REFERENCES product (product_id),
    FOREIGN KEY (size_id) REFERENCES size (size_id)
);
ALTER TABLE users
DROP COLUMN age;
ALTER TABLE users
DROP COLUMN role;
ALTER TABLE users
ADD COLUMN status boolean Not null;
ALTER TABLE users
DROP COLUMN status;
ALTER TABLE users
ADD COLUMN status boolean default true Not null;
ALTER TABLE product
DROP COLUMN created_date;
ALTER TABLE product
ADD COLUMN created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE price
DROP COLUMN start_date;
ALTER TABLE price
ADD COLUMN start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;