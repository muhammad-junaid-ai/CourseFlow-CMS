-- Database Schema for Course Management System (CMS)

CREATE DATABASE IF NOT EXISTS project;
USE project;

-- Users table (Students and Faculty)
CREATE TABLE IF NOT EXISTS data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    age VARCHAR(10),
    email VARCHAR(150) NOT NULL UNIQUE,
    degree VARCHAR(100),
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Active Course Enrollments
CREATE TABLE IF NOT EXISTS add_course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    signup_id VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    courses VARCHAR(100) NOT NULL,
    teachers VARCHAR(100) NOT NULL,
    time VARCHAR(50) NOT NULL,
    day VARCHAR(50) NOT NULL
);

-- Student Evaluation & Marks
CREATE TABLE IF NOT EXISTS marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    signup_id VARCHAR(50) NOT NULL,
    oquiz VARCHAR(10),
    oassignment VARCHAR(10),
    oproject VARCHAR(10),
    omid VARCHAR(10),
    ofinal VARCHAR(10),
    courses VARCHAR(100) NOT NULL,
    teachers VARCHAR(100) NOT NULL
);

-- Enrollment History Log
CREATE TABLE IF NOT EXISTS history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    signup_id VARCHAR(50) NOT NULL,
    courses VARCHAR(100) NOT NULL,
    teachers VARCHAR(100) NOT NULL,
    time VARCHAR(50) NOT NULL,
    day VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Demo / Trial Accounts (Pre-seeded for instant testing)
INSERT INTO data (id, firstname, lastname, username, age, email, degree, password, status)
VALUES 
(1, 'Student', 'Demo', 'student', '21', 'student@example.com', 'BS Computer Science', '123456', 'student'),
(2, 'Hassan Khan', 'Hassan Khan', 'teacher', '38', 'teacher@example.com', 'PhD Computer Science', '123456', 'teacher')
ON DUPLICATE KEY UPDATE password = VALUES(password);

-- Sample Course Enrollment & Grades for Demo Student
INSERT INTO add_course (signup_id, name, courses, teachers, time, day)
VALUES ('1', 'student', 'DBA', 'Hassan Khan', '09:00-10:25', 'Monday');

INSERT INTO history (signup_id, courses, teachers, time, day, status)
VALUES ('1', 'DBA', 'Hassan Khan', '09:00-10:25', 'Monday', 'ENROLLED');

INSERT INTO marks (signup_id, oquiz, oassignment, oproject, omid, ofinal, courses, teachers)
VALUES ('1', '5', '9', '10', '32', '38', 'DBA', 'Hassan Khan');
