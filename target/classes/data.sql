-- Create 'dish' table
CREATE TABLE IF NOT EXISTS dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    order_count INT DEFAULT 0
);

-- Insert sample data into the 'dish' table
INSERT INTO dish (name, order_count) VALUES ('Butter Chicken', 10);
INSERT INTO dish (name, order_count) VALUES ('Paneer Tikka', 8);
INSERT INTO dish (name, order_count) VALUES ('Chole Bhature', 15);
INSERT INTO dish (name, order_count) VALUES ('Rajma Chawal', 5);
INSERT INTO dish (name, order_count) VALUES ('Biryani', 20);
INSERT INTO dish (name, order_count) VALUES ('Samosa', 12);
INSERT INTO dish (name, order_count) VALUES ('Aloo Paratha', 7);
INSERT INTO dish (name, order_count) VALUES ('Pav Bhaji', 18);
INSERT INTO dish (name, order_count) VALUES ('Momos', 9);
INSERT INTO dish (name, order_count) VALUES ('Chaat', 14);
