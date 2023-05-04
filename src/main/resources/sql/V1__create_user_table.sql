CREATE SEQUENCE clients_sequence
    INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999999999 START WITH 1;

CREATE TABLE IF NOT EXISTS clients (
  id BIGINT NOT NULL DEFAULT NEXTVAL ('clients_sequence') PRIMARY KEY,
  name varchar(100) NOT NULL,
  age int NOT NULL,
  group_id bigint NOT NULL,
  phone varchar(15),
  date date
);