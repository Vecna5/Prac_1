-- V1__Create_BusSchedule_Table.sql
CREATE TABLE bus_schedule (
    id SERIAL PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    bus_number VARCHAR(50) NOT NULL,
    departure_date TIMESTAMP NOT NULL,
    carrier VARCHAR(255),
    trip_duration INTERVAL,
    license_plate VARCHAR(50)
);
