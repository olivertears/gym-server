CREATE TABLE IF NOT EXISTS user
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name  varchar(45) NOT NULL,
    surname varchar(45) NOT NULL,
    email varchar(45) NOT NULL,
    password varchar(45) NOT NULL,
    role varchar(10) DEFAULT 'CLIENT' NOT NULL,
    price double,
    CONSTRAINT email_UNIQUE
        UNIQUE (email),
    CONSTRAINT id_UNIQUE
        UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS subscription
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    userId int NOT NULL,
    type varchar(10) DEFAULT 'STANDARD' NOT NULL,
    price double NOT NULL,
    start date NOT NULL,
    end date NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT userId
        FOREIGN KEY (userId) REFERENCES user (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS workout
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    clientId int NOT NULL,
    coachId int NOT NULL,
    done tinyint DEFAULT '0' NOT NULL,
    price double NOT NULL,
    date date NOT NULL,
    time varchar(10) NOT NULL,
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT clientId
        FOREIGN KEY (clientId) REFERENCES user (id)
        ON DELETE CASCADE,
    CONSTRAINT coachId
        FOREIGN KEY (coachId) REFERENCES user (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS category
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type varchar(10) DEFAULT 'EXPENSE' NOT NULL,
    name varchar(45) NOT NULL,
    isDefaultWorkoutCategory tinyint DEFAULT '0',
    isDefaultSubscriptionCategory tinyint DEFAULT '0',
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
    description varchar(1024),
    CONSTRAINT id_UNIQUE
        UNIQUE (id),
    CONSTRAINT categoryId
        FOREIGN KEY (categoryId) REFERENCES category (id)
        ON DELETE CASCADE
);


# INSERT INTO category (type, name, isDefaultWorkoutCategory, isDefaultSubscriptionCategory)
#     VALUES ('INCOME', 'Доход', 1, 1);

# USER
# INSERT INTO user (role, name, surname, email, password)
#     VALUES ('ADMIN', 'vitalya', 'admin', 'admin@gmail.com', 'admin');
# INSERT INTO user (name, surname, email, password)
#     VALUES ('vitalya', 'client', 'client@gmail.com', 'client');
# INSERT INTO user (role, name, surname, email, password)
#     VALUES ('COACH', 'vitalya', 'coach', 'coach@gmail.com', 'coach');
#
# UPDATE user
# SET surname = 'client', role = 'CLIENT'
# WHERE surname = 'client';
#
# DELETE FROM user WHERE id = 4;
#
# SELECT * FROM user;

# WORKOUT
# INSERT INTO workout (coachId, done, price, start, end)
#     VALUES (3, 0, 19.99, '2022-11-16 17:00:00', '2022-11-16 18:00:00');
# INSERT INTO workout (coachId, done, price, start, end)
# VALUES (3, 0, 19.99, '2022-11-16 17:00:00', '2022-11-16 18:00:00');
#
# UPDATE workout
# SET start = '2022-11-16 18:00:00', end = '2022-11-16 19:00:00'
# WHERE id = 1;
#
# SELECT * FROM workout;
#
# DELETE FROM workout WHERE id = 1;
#
# DELETE FROM user WHERE role = 'COACH';
#
# SELECT * FROM workout;
# SUBSCRIPTION
# CATEGORY
# OPERATION