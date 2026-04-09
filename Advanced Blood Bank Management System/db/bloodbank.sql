CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(64),
    role VARCHAR(10)
);

-- Insert an admin user
INSERT INTO users (username, password, role)
VALUES ('admin', SHA2('admin123', 256), 'admin');
