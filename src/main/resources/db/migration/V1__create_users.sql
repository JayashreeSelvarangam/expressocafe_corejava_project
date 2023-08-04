create database newone1;
use newone1;
CREATE TABLE IF NOT EXISTS products(
product_id INT PRIMARY KEY NOT NULL,
productname VARCHAR(50)NOT NULL,
category_id INT NOT NULL,
FOREIGN KEY (category_id) REFERENCES category(category_id) 
);

CREATE TABLE IF NOT EXISTS sizes (
size_id INT PRIMARY KEY NOT NULL,
size_name VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS category (
category_id INT PRIMARY KEY NOT NULL,
createdDate DATE NOT NULL,
endDate DATE
);


CREATE TABLE IF NOT EXISTS prices (
price_id INT PRIMARY KEY NOT NULL,
category_id INT NOT NULL,
size_id INT NOT NULL ,
product_id INT NOT NULL,
FOREIGN KEY (category_id) REFERENCES category (category_id),
FOREIGN KEY (product_id) REFERENCES products(product_id)
);
 
ALTER TABLE prices
ADD COLUMN createdDate DATE NOT NULL;
ALTER TABLE prices
ADD COLUMN endDate DATE;


ALTER TABLE prices
ADD CONSTRAINT fk_constraint_name -- Provide a name for the foreign key constraint (optional, but recommended)
FOREIGN KEY (size_id)    -- The column in table1 that will reference the foreign key
REFERENCES sizes(size_id); -- The referenced table and column

