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
--
--INSERT INTO users (username, password, role, email, full_name) VALUES
--     ('student1', 'password123', 'STUDENT', 'student1@example.com', 'John Doe'),
--     ('student2', 'securepass', 'STUDENT', 'student2@example.com', 'Jane Smith'),
--     ('ta1', 'ta@123', 'TA', 'ta1@example.com', 'Michael Johnson'),
--     ('ta2', 'tasecure', 'TA', 'ta2@example.com', 'Emily Davis'),
--     ('prof1', 'prof123', 'PROFESSOR', 'prof1@example.com', 'Dr. Robert Parker'),
--     ('prof2', 'prof@secure', 'PROFESSOR', 'prof2@example.com', 'Dr. Sarah Anderson'),
--     ('admin1', 'admin123', 'ADMIN', 'admin1@example.com', 'Admin User');

INSERT INTO users (id, username, password, role, email, full_name) VALUES
     ('0447ed15-bb82-476c-bb35-471937ac4e9c', 'prof2', 'prof@secure', 'PROFESSOR', 'prof2@example.com', 'Dr. Sarah Anderson'),
     ('07849310-109f-4503-8ec3-90286ea894f5', 'ta1', 'ta@123', 'TA', 'ta1@example.com', 'Michael Johnson'),
     ('1d85d7c2-495b-4457-924c-c0d52189ca53', 'student2', 'securepass', 'STUDENT', 'student2@example.com', 'Jane Smith'),
     ('2c83ba72-4cab-4593-b114-18fe8b299546', 'admin1', 'admin123', 'ADMIN', 'admin1@example.com', 'Admin User'),
     ('627df4cb-d72a-4773-9848-d82e36e8661c', 'newuser123', 'password123', 'STUDENT', 'newuser@example.com', 'New User'),
     ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', 'student1', 'password123', 'STUDENT', 'student1@example.com', NULL),
     ('eb79d829-5921-45b4-b9ad-4e2b5d1aacbc', 'ta2', 'tasecure', 'TA', 'ta2@example.com', 'Emily Davis'),
     ('fc2c93d6-5998-467f-a618-b58718579cd9', 'prof1', 'prof123', 'PROFESSOR', 'prof1@example.com', 'Dr. Robert Parker');

--create course table
DROP TABLE IF EXISTS course;

CREATE TABLE COURSE (
    CourseID VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,
    CourseName VARCHAR(100) NOT NULL,
    ProfessorID VARCHAR(255),-- Add index to ProfessorID if needed
    FOREIGN KEY (ProfessorID) REFERENCES USERS(UserID)
);


-- create user-course table
DROP TABLE IF EXISTS user_course;

CREATE TABLE user_course (
    courseId VARCHAR(255),
    userId VARCHAR(255),
    PRIMARY KEY (courseId, userId),
    FOREIGN KEY (courseId) REFERENCES course(courseId),
    FOREIGN KEY (userId) REFERENCES users(id)
);

CREATE TABLE assignment (
    assignmentId VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,
    assignment_name VARCHAR(255) NOT NULL,
    description TEXT,
    deadline DATE,
    courseId VARCHAR(255),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);


INSERT INTO Course (id,course_name, professor_Id) VALUES
('1','Introduction to Information Security', 'fc2c93d6-5998-467f-a618-b58718579cd9'), -- Assuming professor ID 3 corresponds to prof_robert
('2','Software Engineering', '0447ed15-bb82-476c-bb35-471937ac4e9c'), -- Same professor as above for simplicity
('3','Database Organization', '0447ed15-bb82-476c-bb35-471937ac4e9c');

INSERT INTO Assignment (assignment_id, assignment_name, description, deadline, course_Id) VALUES
('1', 'Assignment 1', 'Description for Assignment 1', '2024-05-10', '1'), -- Assuming course ID 1 corresponds to 'Introduction to Information Security'
('2', 'Assignment 2', 'Description for Assignment 2', '2024-05-15', '2'), -- Assuming course ID 2 corresponds to 'Software Engineering'
('3', 'Assignment 3', 'Description for Assignment 3', '2024-05-20', '3'); -- Assuming course ID 3 corresponds to 'Database Organization'


INSERT INTO User_Course (id, student_Id, course_Id) VALUES
(1, '8f126c78-998c-4b17-84cf-c4c511cd2ca0', '1'), -- User ID 1 (john_doe) enrolled in course ID 1
(2, '1d85d7c2-495b-4457-924c-c0d52189ca53', '1'), -- User ID 2 (jane_smith) also enrolled in course ID 1
(3, '1d85d7c2-495b-4457-924c-c0d52189ca53', '2'); -- User ID 1 (john_doe) enrolled in course ID 2 as well

INSERT INTO User_Course (id, student_Id, course_Id, role) VALUES (1, '8f126c78-998c-4b17-84cf-c4c511cd2ca0', '1','STUDENT'),  (2, '1d85d7c2-495b-4457-924c-c0d52189ca53', '1','STUDENT'),  (3, '1d85d7c2-495b-4457-924c-c0d52189ca53', '2','TA');