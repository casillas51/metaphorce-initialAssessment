CREATE TABLE IF NOT EXISTS roles (
	id_role INT AUTO_INCREMENT,
    role ENUM ('ADMIN','USER') NOT NULL,
    PRIMARY KEY (id_role),
    UNIQUE (role)
);

CREATE TABLE IF NOT EXISTS users (
	id_user INT AUTO_INCREMENT,
	username VARCHAR(25) NOT NULL,
    password VARCHAR(35) NOT NULL,
    id_role INT NOT NULL,
    PRIMARY KEY (id_user),
    UNIQUE (username),
    CONSTRAINT fk_user_role
		FOREIGN KEY (id_role)
        REFERENCES roles (id_role),
	INDEX idx_username (username)
);