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
    id int,
    courseId VARCHAR(255),
    userId VARCHAR(255),
    PRIMARY KEY (courseId, userId),
    FOREIGN KEY (courseId) REFERENCES course(courseId),
    FOREIGN KEY (userId) REFERENCES users(id)
);

-- create assignment table
DROP TABLE IF EXISTS assignment;

CREATE TABLE assignment (
    assignmentId VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,
    assignment_name VARCHAR(255) NOT NULL,
    description TEXT,
    deadline DATE,
    courseId VARCHAR(255),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);

-- create assignment_submission table
DROP TABLE IF EXISTS assignment_submission;

CREATE TABLE assignment_submission (
    submissionId VARCHAR(255) PRIMARY KEY,
    assignmentId VARCHAR(255),
    userId VARCHAR(255),
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    submission_text TEXT,
    submission_file VARCHAR(255),
    grade DECIMAL(5, 2),
    comments TEXT,
    FOREIGN KEY (assignmentId) REFERENCES assignment(assignmentId),
    FOREIGN KEY (userId) REFERENCES users(id)
);


CREATE DATABASE IF NOT EXISTS student_assist;

USE student_assist;

INSERT INTO users (id, username, password, role, email, full_name) VALUES
     ('0447ed15-bb82-476c-bb35-471937ac4e9c', 'prof2', 'prof@secure', 'PROFESSOR', 'prof2@example.com', 'Dr. Sarah Anderson'),
     ('1d85d7c2-495b-4457-924c-c0d52189ca53', 'student2', 'securepass', 'STUDENT', 'student2@example.com', 'Jane Smith'),
     ('2c83ba72-4cab-4593-b114-18fe8b299546', 'admin1', 'admin123', 'ADMIN', 'admin1@example.com', 'Admin User'),
     ('627df4cb-d72a-4773-9848-d82e36e8661c', 'newuser123', 'password123', 'STUDENT', 'newuser@example.com', 'New User'),
     ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', 'student1', 'password123', 'STUDENT', 'student1@example.com', NULL),
     ('fc2c93d6-5998-467f-a618-b58718579cd9', 'prof1', 'prof123', 'PROFESSOR', 'prof1@example.com', 'Dr. Robert Parker'),
    ('8f126c78-998c-4b17-84cf-c4c511cd2ca1', 'john_doe', 'password123', 'STUDENT', 'john_doe@example.com', 'John Doe'),
    ('1d85d7c2-495b-4457-924c-c0d52189ca54', 'jane_smith', 'securepass', 'STUDENT', 'jane_smith@example.com', 'Jane Smith'),
    ('2c83ba72-4cab-4593-b114-18fe8b299547', 'michael_j', 'mjpass', 'STUDENT', 'michael_j@example.com', 'Michael Johnson'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', 'emily_brown', 'ebpass', 'STUDENT', 'emily_brown@example.com', 'Emily Brown'),
    ('w8f126c7-998c-4b17-84cf-c4c511cd2ca0', 'william_c', 'wcpass', 'STUDENT', 'william_c@example.com', 'William Clark'),
    ('s1d85d7c-495b-4457-924c-c0d52189ca53', 'sophia_m', 'smpass', 'STUDENT', 'sophia_m@example.com', 'Sophia Martinez'),
    ('d2c83ba7-4cab-4593-b114-18fe8b299546', 'david_white', 'dwpass', 'STUDENT', 'david_white@example.com', 'David White'),
    ('o1b2c3d4-e5f6-789a-bcde-f0123456789b', 'olivia_j', 'ojpass', 'STUDENT', 'olivia_j@example.com', 'Olivia Johnson'),
    ('e2c83ba7-4cab-4593-b114-18fe8b299546', 'ethan_t', 'etpass', 'STUDENT', 'ethan_t@example.com', 'Ethan Thompson'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789c', 'emma_h', 'ehpass', 'STUDENT', 'emma_h@example.com', 'Emma Harris'),
    ('b8f126c7-998c-4b17-84cf-c4c511cd2ca0', 'benjamin_w', 'bwpass', 'STUDENT', 'benjamin_w@example.com', 'Benjamin Wilson'),
    ('c1d85d7c-495b-4457-924c-c0d52189ca53', 'chloe_a', 'capass', 'STUDENT', 'chloe_a@example.com', 'Chloe Anderson'),
    ('j2c83ba7-4cab-4593-b114-18fe8b299546', 'james_l', 'jlpass', 'STUDENT', 'james_l@example.com', 'James Lee'),
    ('l1b2c3d4-e5f6-789a-bcde-f0123456789c', 'lily_m', 'lmpass', 'STUDENT', 'lily_m@example.com', 'Lily Martinez'),
    ('s2c83ba7-4cab-4593-b114-18fe8b299546', 'samuel_r', 'srpass', 'STUDENT', 'samuel_r@example.com', 'Samuel Robinson'),
    ('a1b2c3d4-e5f6-789a-bcde-f0123456789a', 'prof3', 'prof456', 'PROFESSOR', 'prof3@example.com', 'Dr. John Smith'),
    ('98765432-10ab-cdef-0123-456789abcdef', 'prof4', 'prof789', 'PROFESSOR', 'prof4@example.com', 'Dr. Emily Davis'),
    ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', 'ta1', 'tasecure', 'TA', 'ta1@example.com', 'TA1'),
    ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', 'ta2', 'tasecure', 'TA', 'ta2@example.com', 'TA2'),
    ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', 'ta3', 'tasecure', 'TA', 'ta3@example.com', 'TA3'),
    ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', 'ta4', 'tasecure', 'TA', 'ta4@example.com', 'TA4'),
    ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', 'ta5', 'tasecure', 'TA', 'ta5@example.com', 'TA5'),
    ('f350f662-b63d-4643-afd1-0b30b68b7d67', 'ta6', 'tasecure', 'TA', 'ta6@example.com', 'TA6');



INSERT INTO Course (id,course_name, professor_Id) VALUES
    ('1','Introduction to Computer Science', 'fc2c93d6-5998-467f-a618-b58718579cd9'),
    ('2','Data Structures and Algorithms', '0447ed15-bb82-476c-bb35-471937ac4e9c'),
    ('3','Web Development', 'a1b2c3d4-e5f6-789a-bcde-f0123456789a'),
    ('4','Database Management', '98765432-10ab-cdef-0123-456789abcdef'),
    ('5','Software Engineering', 'fc2c93d6-5998-467f-a618-b58718579cd9'),
    ('6','Artificial Intelligence', '0447ed15-bb82-476c-bb35-471937ac4e9c');

-- Insert assignments with updated descriptions for each course
INSERT INTO assignment (assignment_id, assignment_name, description, deadline, course_id) VALUES
    ('5b69ff3c-76bc-48b8-8bf1-97685e63f96f', 'Assignment 1', 'Introduction to Computer Science - Assignment 1', '2024-05-10', '1'),
    ('60b71d6f-d7b9-46b0-96d7-df716abf176f', 'Assignment 2', 'Introduction to Computer Science - Assignment 2', '2024-05-15', '1'),
    ('f115b920-07b2-4e3a-9f15-3b5560be4b60', 'Assignment 1', 'Data Structures and Algorithms - Assignment 1', '2024-05-10', '2'),
    ('4f842c68-5b77-47d8-bb26-7b51dc3a35c7', 'Assignment 2', 'Data Structures and Algorithms - Assignment 2', '2024-05-15', '2'),
    ('23167d53-fd1a-4a8a-bc4c-5d5149722a8f', 'Assignment 1', 'Web Development - Assignment 1', '2024-05-10', '3'),
    ('9bb345c7-7e04-4a9a-84bc-2749b05e94bb', 'Assignment 2', 'Web Development - Assignment 2', '2024-05-15', '3'),
    ('e4bcf1c1-542f-4c8d-89d0-b66fa6c0ab26', 'Assignment 1', 'Database Management - Assignment 1', '2024-05-10', '4'),
    ('7d3d6c50-441f-4e2f-8fe1-59152c7224fa', 'Assignment 2', 'Database Management - Assignment 2', '2024-05-15', '4'),
    ('d58258ee-22f3-4e93-ae03-cbdd0fc2651c', 'Assignment 1', 'Software Engineering - Assignment 1', '2024-05-10', '5'),
    ('8648614e-3a36-4b05-98d0-cbf3c8496b6b', 'Assignment 2', 'Software Engineering - Assignment 2', '2024-05-15', '5'),
    ('b19f894d-8638-4f17-8158-ef3d091b2ecb', 'Assignment 1', 'Artificial Intelligence - Assignment 1', '2024-05-10', '6'),
    ('4b373aa4-9ed8-46eb-bc1a-dbbf058e5e5e', 'Assignment 2', 'Artificial Intelligence - Assignment 2', '2024-05-15', '6'),
    ('0973b17d-b916-496f-8136-b46d349f0d85', 'Assignment 3', 'Database Management - Assignment 3', '2024-05-20', '4'),
    ('cbb748e8-2fa2-42c4-b890-f20407d9d445', 'Assignment 3', 'Web Development - Assignment 3', '2024-05-20', '3'),
    ('9e9a85e3-870e-4a6a-9c04-30975f0bb17f', 'Assignment 3', 'Introduction to Computer Science - Assignment 3', '2024-05-20', '1');


INSERT INTO user_course (student_id, course_id, role) VALUES
    ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
    ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
    ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '3', 'STUDENT'),
    ('1d85d7c2-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
    ('1d85d7c2-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
    ('1d85d7c2-495b-4457-924c-c0d52189ca53', '3', 'STUDENT'),
    ('2c83ba72-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
    ('2c83ba72-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
    ('w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
    ('w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
    ('s1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
    ('s1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
    ('d2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
    ('d2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
    ('o1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
    ('o1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
    ('e2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
    ('e2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789c', '1', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789c', '2', 'STUDENT'),
    ('b8f126c7-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
    ('b8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
    ('c1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
    ('c1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
    ('j2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
    ('j2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
    ('l1b2c3d4-e5f6-789a-bcde-f0123456789c', '1', 'STUDENT'),
    ('l1b2c3d4-e5f6-789a-bcde-f0123456789c', '2', 'STUDENT'),
    ('s2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
    ('s2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
    ('s1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
    ('s1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
    ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
    ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '1', 'TA'),
    ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '2', 'TA'),
    ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '3', 'TA'),
    ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '4', 'TA'),
    ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '5', 'TA'),
    ('f350f662-b63d-4643-afd1-0b30b68b7d67', '6', 'TA'),
    ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '3', 'STUDENT'),
    ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '4', 'STUDENT'),
    ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '1', 'STUDENT'),
    ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '6', 'STUDENT'),
    ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '2', 'STUDENT'),
    ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '5', 'STUDENT'),
    ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '1', 'STUDENT'),
    ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '6', 'STUDENT'),
    ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '2', 'STUDENT'),
    ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '4', 'STUDENT'),
    ('f350f662-b63d-4643-afd1-0b30b68b7d67', '3', 'STUDENT'),
    ('f350f662-b63d-4643-afd1-0b30b68b7d67', '5', 'STUDENT');


INSERT INTO assignment_submission (submission_id, assignment_id, user_id, submission_date, submission_text, submission_file, grade, comments) VALUES
    ('b2678d65-7c28-4a3d-aa0b-37bb8e72ef9f', '5b69ff3c-76bc-48b8-8bf1-97685e63f96f', '8f126c78-998c-4b17-84cf-c4c511cd2ca0', '2024-05-09 10:00:00', 'Here is my submission for Assignment 1.', '/path/to/file1.pdf', 85.5, 'Good work!'),
    ('a5e8c345-f191-4cf2-a63c-11f7a3583010', '60b71d6f-d7b9-46b0-96d7-df716abf176f', '8f126c78-998c-4b17-84cf-c4c511cd2ca0', '2024-05-14 09:30:00', 'My submission for Assignment 2 is attached.', '/path/to/file2.docx', 90.0, 'Excellent job!'),
    ('0d51b0f8-fd17-407e-840d-d119f3db7b9d', 'f115b920-07b2-4e3a-9f15-3b5560be4b60', '1d85d7c2-495b-4457-924c-c0d52189ca53', '2024-05-09 11:45:00', 'Submission for Assignment 1.', '/path/to/submission.docx', 78.0, 'Well done.'),
    ('1c021269-4e20-4e83-b7ec-fec34d37fe19', '4f842c68-5b77-47d8-bb26-7b51dc3a35c7', '1d85d7c2-495b-4457-924c-c0d52189ca53', '2024-05-14 15:00:00', 'Here is my Assignment 2 submission.', '/path/to/assignment2.pdf', 88.5, 'Good effort!'),
    ('627fa963-5a0b-4f25-bf20-f9b715b20f04', '23167d53-fd1a-4a8a-bc4c-5d5149722a8f', '2c83ba72-4cab-4593-b114-18fe8b299546', '2024-05-09 14:20:00', 'Assignment 1 submission.', '/path/to/submission.pdf', 92.0, 'Well done!'),
    ('e6f844d9-4d52-43b8-a3e2-99a070fc70dc', '9bb345c7-7e04-4a9a-84bc-2749b05e94bb', '2c83ba72-4cab-4593-b114-18fe8b299546', '2024-05-14 16:45:00', 'Assignment 2 submission attached.', '/path/to/assignment2.docx', 85.0, 'Great work!'),
    ('7a035793-dfc3-4604-8a30-06b8ac65c26c', 'e4bcf1c1-542f-4c8d-89d0-b66fa6c0ab26', 'e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2024-05-09 12:10:00', 'Submission for Assignment 1.', '/path/to/assignment1.pdf', 75.5, 'Good effort.'),
    ('d8b3f8c1-b3eb-4a42-9c77-2efef6dc6d3d', '7d3d6c50-441f-4e2f-8fe1-59152c7224fa', 'e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2024-05-14 14:30:00', 'Attached is my Assignment 2 submission.', '/path/to/assignment2.pdf', 83.0, 'Nice work!'),
    ('f1b712b1-93dc-438d-8709-cf80f76e4a50', 'd58258ee-22f3-4e93-ae03-cbdd0fc2651c', 'w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2024-05-09 11:00:00', 'Assignment 1 submission.', '/path/to/assignment1.docx', 80.0, 'Keep it up.'),
    ('63241f13-9e07-42e4-ba9f-9c60c460b6c4', '8648614e-3a36-4b05-98d0-cbf3c8496b6b', 'w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2024-05-14 13:45:00', 'My submission for Assignment 2.', '/path/to/assignment2.pdf', 87.5, 'Excellent work!'),
    ('0bb6df15-54f5-49ec-9439-7e8a1f4e3663', 'b19f894d-8638-4f17-8158-ef3d091b2ecb', 's1d85d7c-495b-4457-924c-c0d52189ca53', '2024-05-09 13:30:00', 'Here is my submission for Assignment 1.', '/path/to/submission.pdf', 88.0, 'Well done!'),
    ('726c127a-28c7-4b5a-aa7c-41664f7632b8', '4b373aa4-9ed8-46eb-bc1a-dbbf058e5e5e', 's1d85d7c-495b-4457-924c-c0d52189ca53', '2024-05-14 12:15:00', 'Submission for Assignment 2.', '/path/to/assignment2.docx', 92.5, 'Great job!'),
    ('2b4575a5-eb2d-4e78-9500-18cb0915e101', '0973b17d-b916-496f-8136-b46d349f0d85', 'd2c83ba7-4cab-4593-b114-18fe8b299546', '2024-05-20 10:00:00', 'My submission for Assignment 3.', '/path/to/submission.pdf', NULL, NULL),
    ('fc2a390e-739a-4df5-81d3-7f9ae7ee9f2f', 'cbb748e8-2fa2-42c4-b890-f20407d9d445', 'c1d85d7c-495b-4457-924c-c0d52189ca53', '2024-05-20 09:30:00', 'Assignment 3 submission attached.', '/path/to/assignment3.pdf', NULL, NULL),
    ('7f0e2e16-16a2-4a19-bb99-46e84b7153bf', '9e9a85e3-870e-4a6a-9c04-30975f0bb17f', 'j2c83ba7-4cab-4593-b114-18fe8b299546', '2024-05-20 11:45:00', 'Submission for Assignment 3.', '/path/to/submission.pdf', NULL, NULL),
    ('9e0f01c4-35a9-4ae3-a730-39eef1ad17e1', '4f842c68-5b77-47d8-bb26-7b51dc3a35c7', 'j2c83ba7-4cab-4593-b114-18fe8b299546', '2024-05-20 15:00:00', 'Here is my Assignment 2 submission.', '/path/to/assignment2.pdf', NULL, NULL),
    ('6797cb28-9ee4-4564-924e-2d0342288584', '23167d53-fd1a-4a8a-bc4c-5d5149722a8f', 'l1b2c3d4-e5f6-789a-bcde-f0123456789c', '2024-05-20 14:20:00', 'Assignment 1 submission.', '/path/to/submission.docx', NULL, NULL),
    ('db348f33-78fc-43d9-af42-75f618fcd8f2', 'e4bcf1c1-542f-4c8d-89d0-b66fa6c0ab26', 'l1b2c3d4-e5f6-789a-bcde-f0123456789c', '2024-05-20 16:45:00', 'Attached is my Assignment 2 submission.', '/path/to/assignment2.pdf', NULL, NULL),
    ('b2678d65-7c28-4a3d-aa0b-37bb8e72ef9g', '5b69ff3c-76bc-48b8-8bf1-97685e63f96f', 's2c83ba7-4cab-4593-b114-18fe8b299546', '2024-05-19 10:00:00', 'Here is my submission for Assignment 1.', '/path/to/file1.pdf', 85.5, 'Good work!'),
    ('a5e8c345-f191-4cf2-a63c-11f7a3583011', '60b71d6f-d7b9-46b0-96d7-df716abf176f', 's2c83ba7-4cab-4593-b114-18fe8b299546', '2024-05-24 09:30:00', 'My submission for Assignment 2 is attached.', '/path/to/assignment2.pdf', 90.0, 'Excellent job!');


INSERT INTO groups (name, creator_id, created_date, course_id, isgrop) VALUES
    ('Group1 for Introduction to Computer Science', 'fc2c93d6-5998-467f-a618-b58718579cd9', '2024-04-24', 1, 1),
    ('Group1 for Data Structures and Algorithms', '0447ed15-bb82-476c-bb35-471937ac4e9c', '2024-04-24', 2, 1),
    ('Group1 for Web Development', 'a1b2c3d4-e5f6-789a-bcde-f0123456789a', '2024-04-24', 3, 1),
    ('Group1 for Database Management', '98765432-10ab-cdef-0123-456789abcdef', '2024-04-24', 4, 1),
    ('Group1 for Software Engineering', 'fc2c93d6-5998-467f-a618-b58718579cd9', '2024-04-24', 5, 1),
    ('Group1 for Artificial Intelligence', '0447ed15-bb82-476c-bb35-471937ac4e9c', '2024-04-24', 6, 1);



    INSERT INTO user_group (student_id, group_id, role) VALUES
        ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
        ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
        ('8f126c78-998c-4b17-84cf-c4c511cd2ca0', '3', 'STUDENT'),
        ('1d85d7c2-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
        ('1d85d7c2-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
        ('1d85d7c2-495b-4457-924c-c0d52189ca53', '3', 'STUDENT'),
        ('2c83ba72-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
        ('2c83ba72-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
        ('w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
        ('w8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
        ('s1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
        ('s1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
        ('d2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
        ('d2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
        ('o1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
        ('o1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
        ('e2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
        ('e2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789c', '1', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789c', '2', 'STUDENT'),
        ('b8f126c7-998c-4b17-84cf-c4c511cd2ca0', '1', 'STUDENT'),
        ('b8f126c7-998c-4b17-84cf-c4c511cd2ca0', '2', 'STUDENT'),
        ('c1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
        ('c1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
        ('j2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
        ('j2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
        ('l1b2c3d4-e5f6-789a-bcde-f0123456789c', '1', 'STUDENT'),
        ('l1b2c3d4-e5f6-789a-bcde-f0123456789c', '2', 'STUDENT'),
        ('s2c83ba7-4cab-4593-b114-18fe8b299546', '1', 'STUDENT'),
        ('s2c83ba7-4cab-4593-b114-18fe8b299546', '2', 'STUDENT'),
        ('s1d85d7c-495b-4457-924c-c0d52189ca53', '1', 'STUDENT'),
        ('s1d85d7c-495b-4457-924c-c0d52189ca53', '2', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '1', 'STUDENT'),
        ('e1b2c3d4-e5f6-789a-bcde-f0123456789b', '2', 'STUDENT'),
        ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '1', 'TA'),
        ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '2', 'TA'),
        ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '3', 'TA'),
        ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '4', 'TA'),
        ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '5', 'TA'),
        ('f350f662-b63d-4643-afd1-0b30b68b7d67', '6', 'TA'),
        ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '3', 'STUDENT'),
        ('88a1d9b4-fdde-4e12-b4fa-7d3f51f4b1d6', '4', 'STUDENT'),
        ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '1', 'STUDENT'),
        ('3f4dcf09-36c0-4ff3-b92d-6b39c206f77e', '6', 'STUDENT'),
        ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '2', 'STUDENT'),
        ('c7a32a5e-1187-4f33-ae61-9e3a4d74e0ac', '5', 'STUDENT'),
        ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '1', 'STUDENT'),
        ('a2736e46-3f1e-4dc4-ae9c-6f1a60cb45b1', '6', 'STUDENT'),
        ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '2', 'STUDENT'),
        ('e8b0b1f9-1a6e-4828-bd12-b0e7e9fd07b0', '4', 'STUDENT'),
        ('f350f662-b63d-4643-afd1-0b30b68b7d67', '3', 'STUDENT'),
        ('f350f662-b63d-4643-afd1-0b30b68b7d67', '5', 'STUDENT');

