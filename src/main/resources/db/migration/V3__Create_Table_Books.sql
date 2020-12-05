CREATE TABLE IF NOT EXISTS books (
  id INT NOT NULL,
  author text,
  launch_date timestamp NOT NULL,
  price decimal(65,2) NOT NULL,
  title text,
   PRIMARY KEY (id)
)