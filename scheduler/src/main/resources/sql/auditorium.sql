DROP TABLE IF EXISTS auditorium CASCADE;
CREATE TABLE auditorium (
    auditorium_id  SERIAL PRIMARY KEY,
    auditorium_name varchar,
    capacity integer
);
