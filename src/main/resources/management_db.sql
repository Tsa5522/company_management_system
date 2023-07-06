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
    fullName VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE ,
    gender INT,
    departmentID INT,
    roleID INT DEFAULT 1,
    FOREIGN KEY (departmentID) REFERENCES department(departmentID),
    FOREIGN KEY (roleID) REFERENCES authorization(roleID)
);

CREATE TABLE IF NOT EXISTS OperationLog (
    id INT AUTO_INCREMENT,
    operationType VARCHAR(255),
    operationTimestamp TIMESTAMP,
    operationUser INT,
    operationDetails TEXT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Assignments (
    AssignmentID INT AUTO_INCREMENT PRIMARY KEY,
    AssignmentName VARCHAR(255),
    AssignmentDescription VARCHAR(255),
    AssignmentDeadline DATETIME
);

CREATE TABLE IF NOT EXISTS AssignmentUsers (
    AssignmentID INT,
    EmployeeID INT,
    FOREIGN KEY (AssignmentID) REFERENCES Assignments(AssignmentID),
    FOREIGN KEY (EmployeeID) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS messages (
    messageID INT AUTO_INCREMENT PRIMARY KEY,
    sendTime DATETIME,
    fromUser INT NOT NULL,
    toUser INT NOT NULL,
    title VARCHAR(20),
    content VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS OperationLog(
    id INT AUTO_INCREMENT PRIMARY KEY,
    operationType VARCHAR(255),
    operationTimestamp TIMESTAMP,
    operationUser INT,
    operationDetails text,
    FOREIGN KEY (operationUser) REFERENCES employee(id)
);


-- Inserting values into the authorization table
INSERT INTO authorization (roleID, role) VALUES (0, 'ROLE_ADMIN'), (1, 'ROLE_USER');

-- Inserting values into the department table
INSERT INTO department (departmentID, dName) VALUES (0, 'Management'), (1, 'Engineering'), (2, 'Product Management'), (3, 'Marketing');

-- Inserting values into the employee table
INSERT INTO employee (fullName, password, email, gender, departmentID, roleID)
VALUES ('John Doe', 'password123', 'john.doe@example.com', 1, 1, 1),
       ('Jane Smith', 'secret456', 'jane.smith@example.com', 0, 2, 1),
       ('Michael Johnson', 'pass789', 'michael.johnson@example.com', 1, 3, 1),
       ('admin', '123456','admin@example.com', 0, 0, 0)
;
