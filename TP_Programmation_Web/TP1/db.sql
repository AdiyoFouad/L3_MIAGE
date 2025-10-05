
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);
INSERT INTO users (login, password) VALUES
('miage', 'miage123'),
('alice', 'wonderland');
