-- Find the docker container-Id
-- docker ps

-- Execute psql command from the running container
-- docker exec -it <CONTAINER-ID> psql -U postgres

-- Create the database and tables
CREATE DATABASE management;

\c management;

CREATE TABLE Department (
    Department_ID SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE Employee (
    Employee_ID SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender VARCHAR(50),
    Position VARCHAR(255),
    Salary INT,
    Department_ID INT,
    FOREIGN KEY (Department_ID)
    REFERENCES Department (Department_ID)
    ON DELETE SET NULL
);

-- Insert data into the tables
INSERT INTO Department (Name) VALUES
('HR'),
('IT'),
('Sales');

INSERT INTO Employee (Name, DateOfBirth, Gender, Position, Salary, Department_ID) VALUES
('John Doe', '1992-04-12', 'Male', 'Developer', 70000, 1),
('Jane Smith', '1985-05-19', 'Female', 'Analyst', 65000, 2),
('Alice Johnson', '1978-07-30', 'Female', 'Manager', 85000, 3),
('Bob Brown', '1988-09-17', 'Male', 'Support', 50000, 1),
('Carol White', '1992-10-23', 'Female', 'Designer', 60000, 2),
('Eve Davis', '1995-03-15', 'Female', 'Tester', 55000, 3);
