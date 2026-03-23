-- =====================================================
-- 1. DROP & CREATE DATABASE
-- =====================================================
IF DB_ID('OnlineCourseDB') IS NOT NULL
    DROP DATABASE OnlineCourseDB;
GO

CREATE DATABASE OnlineCourseDB;
GO

USE OnlineCourseDB;
GO


-- =====================================================
-- 2. TABLE: Accounts
-- =====================================================
CREATE TABLE Accounts (
    id INT IDENTITY(1,1) PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,

    fullname NVARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),

    role VARCHAR(20) NOT NULL
        CHECK (role IN ('admin','student','instructor')),

    status BIT DEFAULT 1, -- 1 active, 0 banned

    created_at DATETIME DEFAULT GETDATE()
);


-- =====================================================
-- 3. TABLE: Courses
-- =====================================================
CREATE TABLE Courses (
    id INT IDENTITY(1,1) PRIMARY KEY,

    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(500),

    fee DECIMAL(10,2) NOT NULL CHECK (fee >= 0),

    schedule NVARCHAR(100),
    start_date DATETIME NOT NULL,

    created_at DATETIME DEFAULT GETDATE()
);


-- =====================================================
-- 4. TABLE: Registrations (FIXED - VARCHAR)
-- =====================================================
CREATE TABLE Registrations (
    id INT IDENTITY(1,1) PRIMARY KEY,

    account_id INT NOT NULL,
    course_id INT NOT NULL,

    status VARCHAR(20) DEFAULT 'pending'
        CHECK (status IN ('pending','approved','rejected')),

    created_at DATETIME DEFAULT GETDATE(),

    CONSTRAINT UQ_Registration UNIQUE(account_id, course_id),

    FOREIGN KEY (account_id) REFERENCES Accounts(id),
    FOREIGN KEY (course_id) REFERENCES Courses(id)
);


-- =====================================================
-- 5. INSERT DATA: ACCOUNTS
-- =====================================================
INSERT INTO Accounts(username, password, fullname, email, phone, role, status)
VALUES 
('admin','123',N'Admin System','admin@gmail.com','0900000000','admin',1),

('student1','123',N'Nguyen Van A','a@gmail.com','0901111111','student',1),
('student2','123',N'Tran Thi B','b@gmail.com','0902222222','student',1),
('student3','123',N'Le Van C','c@gmail.com','0903333333','student',0), -- banned
('student4','123',N'Pham Thi D','d@gmail.com','0904444444','student',1),
('student5','123',N'Hoang Van E','e@gmail.com','0905555555','student',1),

('inst1','123',N'Teacher One','inst1@gmail.com','0906666666','instructor',1),
('inst2','123',N'Teacher Two','inst2@gmail.com','0907777777','instructor',1);


-- =====================================================
-- 6. INSERT DATA: COURSES
-- =====================================================
INSERT INTO Courses(name, description, fee, schedule, start_date)
VALUES
(N'Java Web Development', N'Servlet & JSP', 100, N'Tue-Thu (07:00-09:00)', '2026-04-01'),
(N'Spring Boot', N'Build REST API', 150, N'Mon-Wed (18:00-20:00)', '2026-04-05'),
(N'Frontend', N'HTML CSS JS', 80, N'Sat-Sun (08:00-10:00)', '2026-04-10'),
(N'Python', N'Python basic', 90, N'Mon-Fri (09:00-11:00)', '2026-04-03'),
(N'Database SQL', N'SQL Server', 120, N'Tue-Thu (13:00-15:00)', '2026-04-08'),
(N'UI/UX', N'Design', 110, N'Sat-Sun (15:00-17:00)', '2026-04-12');


-- =====================================================
-- 7. INSERT DATA: REGISTRATIONS
-- =====================================================
INSERT INTO Registrations(account_id, course_id, status)
VALUES
(2,1,'approved'),
(2,2,'pending'),
(2,3,'rejected'),

(3,1,'approved'),
(3,4,'approved'),
(3,5,'pending'),

(4,2,'pending'),
(4,3,'approved'),

(5,1,'approved'),
(5,6,'pending'),

(6,4,'approved'),
(6,5,'rejected');


-- =====================================================
-- 8. TEST QUERY (DEMO)
-- =====================================================
SELECT 
    a.username,
    a.status AS account_status,
    c.name,
    r.status AS registration_status
FROM Registrations r
JOIN Accounts a ON r.account_id = a.id
JOIN Courses c ON r.course_id = c.id;