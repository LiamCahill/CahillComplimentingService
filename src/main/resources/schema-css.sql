DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS COMPLIMENTS;

CREATE TABLE USERS (
                       U_ID SERIAL PRIMARY KEY,
                       U_USERNAME VARCHAR(255) NOT NULL,
                       U_EMAIL VARCHAR(255) NOT NULL,
                       U_PHONE_NUMBER VARCHAR(255),
                       U_PASSWORD VARCHAR(255) NOT NULL
);

CREATE TABLE COMPLIMENTS (
                             C_ID SERIAL PRIMARY KEY,
                             C_SENDER VARCHAR(255) NOT NULL,
                             C_RECEIVER VARCHAR(255) NOT NULL,
                             C_MESSAGE VARCHAR(255) NOT NULL
);
