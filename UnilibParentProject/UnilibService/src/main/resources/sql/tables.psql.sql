

-- Schema Name: unilibdb
-- Username: unilib
-- Password: unilib@2018 


\c postgres

-- Then execute the following:
DROP DATABASE IF EXISTS unilibdb; 
CREATE DATABASE unilibdb;

GRANT ALL PRIVILEGES ON DATABASE  unilibdb to  unilib;
ALTER DATABASE unilibdb OWNER to unilib;

-- Connect with the database on the username
\c unilibdb unilib



-- =========================
-- 1. Student Management
-- =========================

CREATE TABLE  Student (
    uuid VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL, 
    pin VARCHAR(10) NOT NULL,
    reg_date TIMESTAMP with time zone DEFAULT now(),
    PRIMARY KEY (uuid)   

);
\COPY Student(uuid,full_name,pin) FROM '/tmp/Student.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Student OWNER TO unilib;


ALTER TABLE Student ADD COLUMN student_id VARCHAR;


-- =========================
-- 1. Book Management
-- =========================


--------------------
-- Table Book
-- -------------------
CREATE TABLE  Book (
    uuid VARCHAR(255) UNIQUE NOT NULL,
    id BIGSERIAL NOT NULL, 
    title VARCHAR(255) NOT NULL, 
    authors TEXT NOT NULL, 
    isbn VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL, 
    published_date VARCHAR(50) NOT NULL, 
    status VARCHAR(100) NOT NULL CHECK (status IN ('available','on_loan','back_order')),   
    last_updated TIMESTAMP WITH TIME ZONE NOT NULL,  
    log_date TIMESTAMP with time zone DEFAULT now(),
    PRIMARY KEY (uuid)   

);
\COPY Book(uuid,id,title,authors,isbn,publisher,published_date,status,last_updated) FROM '/tmp/Book.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Book OWNER TO unilib;



-- -------------------
-- Table Borrowing
-- -------------------

CREATE TABLE  Borrowing (
    uuid VARCHAR(255) UNIQUE NOT NULL,
    category VARCHAR(255) NOT NULL CHECK (category IN ('is_borrowing','success_requested','cleared')),   
    book_id VARCHAR(255) REFERENCES Book(uuid), 
    student_id VARCHAR(255) REFERENCES Student(uuid), 
    add_date timestamp with time zone DEFAULT now(), 
    PRIMARY KEY (uuid)   
);
\COPY Borrowing(uuid,category,book_id,student_id) FROM '/tmp/Borrowing.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Borrowing OWNER TO unilib;




