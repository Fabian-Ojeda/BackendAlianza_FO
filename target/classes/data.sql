CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        shared_key VARCHAR(255),
                        business_id VARCHAR(255),
                        email VARCHAR(255),
                        phone VARCHAR(50),
                        data_start DATE,
                        data_finish DATE
);

INSERT INTO Client (shared_key, business_id, email, phone, data_start, data_finish)
VALUES
('jgutierres', 'Juliana Gutierrez', 'jgutierrez@email.com', '3219876543', '2019-05-20', '2025-04-27' ),
('mmartinez', 'Manuel Martinez', 'mmartinez@gmail.com', '3219876543', '2019-05-20', '2025-04-27'),
('aruiz', 'Ana Ruiz', 'aruiz@gmail.com', '3219876543', '2019-05-20', '2025-04-27'),
('ogarcia', 'Orcar Garcia', 'ogarcia@gmail.com', '3219876543', '2019-05-20', '2025-04-27');
