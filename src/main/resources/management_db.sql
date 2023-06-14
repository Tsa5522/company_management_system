CREATE DATABASE management_db;
USE management_db;

CREATE TABLE IF NOT EXISTS authorization (
    roleID INT PRIMARY KEY,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS department (
    departmentID INT PRIMARY KEY,
    dName VARCHAR(50) NOT NULL

);

CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    gender INT NOT NULL,
    departmentID INT,
    roleID INT,
    FOREIGN KEY (departmentID) REFERENCES department(departmentID),
    FOREIGN KEY (roleID) REFERENCES authorization(roleID)
);

-- Inserting values into the authorization table
INSERT INTO authorization (roleID, role) VALUES (0, 'ROLE_ADMIN'), (1, 'ROLE_USER');

-- Inserting values into the department table
INSERT INTO department (departmentID, dName) VALUES (0, 'Management'), (1, 'Engineering'), (2, 'Product Management'), (3, 'Marketing');

-- Inserting values into the employee table
INSERT INTO employee (firstName, lastName, password, email, gender, departmentID, roleID)
VALUES ('John', 'Doe', 'password123', 'john.doe@example.com', 1, 1, 1),
       ('Jane', 'Smith', 'secret456', 'jane.smith@example.com', 0, 2, 1),
       ('Michael', 'Johnson', 'pass789', 'michael.johnson@example.com', 1, 3, 1),
       ('admin', 'admin', '123456','admin@example.com', 0, 0, 0)
;
