CREATE DATABASE IF NOT EXISTS student_assist;

USE student_assist;

--create users table
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id VARCHAR(100) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('STUDENT', 'TA', 'PROFESSOR', 'ADMIN') DEFAULT 'STUDENT',
    email VARCHAR(100),
    full_name VARCHAR(100)
);

INSERT INTO users (username, password, role, email, full_name) VALUES
     ('student1', 'password123', 'STUDENT', 'student1@example.com', 'John Doe'),
     ('student2', 'securepass', 'STUDENT', 'student2@example.com', 'Jane Smith'),
     ('ta1', 'ta@123', 'TA', 'ta1@example.com', 'Michael Johnson'),
     ('ta2', 'tasecure', 'TA', 'ta2@example.com', 'Emily Davis'),
     ('prof1', 'prof123', 'PROFESSOR', 'prof1@example.com', 'Dr. Robert Parker'),
     ('prof2', 'prof@secure', 'PROFESSOR', 'prof2@example.com', 'Dr. Sarah Anderson'),
     ('admin1', 'admin123', 'ADMIN', 'admin1@example.com', 'Admin User');

--create course table
DROP TABLE IF EXISTS course;

CREATE TABLE course (
    courseId VARCHAR(100) PRIMARY KEY,
    courseName VARCHAR(100) NOT NULL,
    professorId VARCHAR(100),
    FOREIGN KEY (professorId) REFERENCES users(id)
);

CREATE TABLE COURSE (
    CourseID INT PRIMARY KEY AUTO_INCREMENT,
    CourseName VARCHAR(100) NOT NULL,
    ProfessorID INT,
    INDEX fk_Course_ProfessorID (ProfessorID),  -- Add index to ProfessorID if needed
    FOREIGN KEY (ProfessorID) REFERENCES USERS(UserID)
);


INSERT INTO course (courseName, professorId) VALUES
   ('Introduction to Information Security', 1),
   ('Software Engineering', 2),
   ('Database Organization', 3);

-- create user-course table
DROP TABLE IF EXISTS user_course;

CREATE TABLE user_course (
    courseId INT,
    userId INT,
    PRIMARY KEY (courseId, userId),
    FOREIGN KEY (courseId) REFERENCES course(courseId),
    FOREIGN KEY (userId) REFERENCES users(id)
);

INSERT INTO user_course (courseId, userId) VALUES
    (1, 1), -- User 1 enrolled in Course 1
    (1, 2), -- User 2 enrolled in Course 1
    (2, 1), -- User 1 enrolled in Course 2
    (3, 3); -- User 3 enrolled in Course 3

CREATE TABLE assignment (
    assignmentId INT PRIMARY KEY AUTO_INCREMENT,
    assignment_name VARCHAR(255) NOT NULL,
    description TEXT,
    deadline DATE,
    courseId INT,
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);


INSERT INTO assignment (assignment_name, description, deadline, courseId) VALUES
   ('Lab 1: Introduction to Encryption', 'Complete exercises on basic encryption techniques.', '2024-04-10', 1),
   ('Project 1: Software Development Lifecycle', 'Develop a software project using Agile methodology.', '2024-04-15', 2),
   ('Assignment 1: Relational Database Design', 'Design a normalized relational database schema.', '2024-04-20', 3),
   ('Lab 2: Cryptography Algorithms', 'Implement and analyze various cryptographic algorithms.', '2024-04-12', 1),
   ('Assignment 2: User Interface Design', 'Create mockups and wireframes for a user interface.', '2024-04-18', 2),
   ('Project 2: SQL Database Implementation', 'Build and populate a SQL database based on a given schema.', '2024-04-25', 3);
