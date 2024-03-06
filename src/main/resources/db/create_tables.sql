-- Create table person if not exists
CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Create table address if not exists
CREATE TABLE IF NOT EXISTS address (
    id SERIAL PRIMARY KEY,
    state VARCHAR(2) NOT NULL,
    city VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    number INT NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    person_id BIGINT REFERENCES person(id) NOT NULL
);

