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

INSERT INTO ticket (ticket_number, passenger_name, seat_number, price)
VALUES ('TN12345', 'Nata Volkova', 'A1', 123.45),
    ('TN12346', 'Jack Smith', 'B2', 150.00),
    ('TN12347', 'Alice Johnson', 'C3', 99.99);




--rollback truncate table ticket;
