CREATE TABLE person(
                       id BIGINT,
                       name VARCHAR (100) PRIMARY KEY,
                       age INTEGER,
                       lows BOOLEAN,
                       car_id BIGINT REFERENCES cars (id)

);

CREATE TABLE cars(
                     id BIGINT primary key ,
                     brand VARCHAR (100),
                     model VARCHAR (100),
                     price numeric

);