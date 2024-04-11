DROP TABLE brands;
CREATE TABLE IF NOT EXISTS brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL
);
DROP TABLE vehicles;

CREATE TABLE IF NOT EXISTS vehicles (
    id BIGINT AUTO_INCREMENT NOT NULL,
    vehicle VARCHAR(40) NOT NULL,
    brand_id BIGINT NOT NULL,
    `year` INTEGER NOT NULL,
    color VARCHAR(40) NOT NULL,
    description VARCHAR(255) NOT NULL,
    was_sold BIT NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);


INSERT INTO brands (name) VALUES ('Aston Martin');
INSERT INTO brands (name) VALUES ('Audi');
INSERT INTO brands (name) VALUES ('Bentley');
INSERT INTO brands (name) VALUES ('BMW');
INSERT INTO brands (name) VALUES ('BMW Motorrad');
INSERT INTO brands (name) VALUES ('BYD');
INSERT INTO brands (name) VALUES ('Caoa Chery');
INSERT INTO brands (name) VALUES ('Chevrolet');