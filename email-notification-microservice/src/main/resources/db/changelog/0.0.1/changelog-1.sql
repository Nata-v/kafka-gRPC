--liquibase formatted sql
--changeset natallia:1
--comment first migration


create TABLE IF NOT EXISTS ticket
(
    id
    SERIAL
    PRIMARY
    KEY,
    ticket_number
    VARCHAR
(
    50
) NOT NULL ,
    passenger_name VARCHAR
(
    128
) NOT NULL,
    seat_number VARCHAR
(
    10
) NOT NULL ,

price DECIMAL(10, 2)
    );




--rollback truncate table ticket;
