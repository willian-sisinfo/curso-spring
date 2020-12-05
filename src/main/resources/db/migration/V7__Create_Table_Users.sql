CREATE TABLE IF NOT EXISTS users (
  id integer NOT NULL,
  user_name varchar(255) DEFAULT NULL,
  full_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  account_non_expired smallint DEFAULT NULL,
  account_non_locked smallint DEFAULT NULL,
  credentials_non_expired smallint DEFAULT NULL,
  enabled smallint DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE(user_name)
)