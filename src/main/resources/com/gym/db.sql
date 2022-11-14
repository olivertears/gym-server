CREATE TABLE IF NOT EXISTS user
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name  varchar(45) NOT NULL,
    surname varchar(45) NOT NULL,
    email varchar(45) NOT NULL,
    password varchar(45) NOT NULL,
    role varchar(10) DEFAULT 'CLIENT' NOT NULL,
    CONSTRAINT email_UNIQUE
        UNIQUE (email),
    CONSTRAINT id_UNIQUE
        UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS subscription
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    clientId int NOT NULL,
    type varchar(10) DEFAULT 'STANDARD' NOT NULL,
    price double NOT NULL,
    start date NOT NULL,
    end date NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT clientId
        FOREIGN KEY (clientId) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS workout
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    coachId int NOT NULL,
    done tinyint DEFAULT '0' NOT NULL,
    type varchar(10) DEFAULT 'STANDARD' NOT NULL,
    price double NOT NULL,
    start datetime NOT NULL,
    end datetime NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT coachId
        FOREIGN KEY (coachId) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS category
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type varchar(10) DEFAULT 'STANDARD' NOT NULL,
    name varchar(45) NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT name_UNIQUE
        UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS operation
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    categoryId int NOT NULL,
    price double NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT categoryId
        FOREIGN KEY (categoryId) REFERENCES category (id)
);


INSERT INTO user (role, name, surname, email, password)
    VALUES ('ADMIN', 'vitalya', 'admin', 'admin@gmail.com', 'admin');
INSERT INTO user (name, surname, email, password)
    VALUES ('vitalya', 'ne admin', 'ne_admin@gmail.com', 'ne_admin')
