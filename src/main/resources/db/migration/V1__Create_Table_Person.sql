CREATE TABLE IF NOT EXISTS person (
  id int NOT NULL,
  address varchar(255) DEFAULT NULL,
  first_name varchar(80) NOT NULL,
  gender varchar(1) DEFAULT NULL,
  last_name varchar(80) NOT NULL,
  PRIMARY KEY (id)
)