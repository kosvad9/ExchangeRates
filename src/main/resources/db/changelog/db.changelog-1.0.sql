--liquibase formatted sql

--changeset kosvad9:1
CREATE TABLE rate(
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL ,
    cur_abbreviation VARCHAR(3) NOT NULL,
    cur_scale NUMERIC NOT NULL CHECK ( cur_scale > 0 ),
    cur_name VARCHAR,
    cur_official_rate NUMERIC(3,4) NOT NULL CHECK ( cur_scale > 0 ),
    CONSTRAINT date_cur UNIQUE (date, cur_abbreviation)
);