#CREATE DATABASE CSS_DEV;
#SHOW DATABASES;


USE CSS_DEV;

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


-- ADDING FAMILY MEMBER USERS BELOW:
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES  ('TCAHILL', '7CAHILLS@ATT.NET', '8602058933', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES  ('WCAHILL', 'CAHILL.WILLIAM@GMAIL.COM', '8602129889', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES ('MCAHILL', 'CAHILL.MKAT@GMAIL.COM', '8602270008', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES ('ECAHILL', 'E.T.CAHILL724', '8608744648', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES ('KCAHILL', 'KM16CAHI@SIENA.EDU', '8609378112', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES ('DCAHILL', 'DECLANCAHILL388@GMAIL.COM', '8608744244', 'TESTPW');
INSERT INTO USERS (U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES ('LCAHILL', 'CAHILL.LIAM220@GMAIL.COM', '8608744575', 'TESTPW');


-- ADDING SOME BASIC TEST COMPLIMENTS BELOW:
INSERT INTO COMPLIMENTS (C_SENDER, C_RECEIVER, C_MESSAGE) VALUES ('LCAHILL', 'DCAHILL', 'KEEP UP THE GREAT WORK AT THE NYISO - YOU''RE DOING AWESOME!' );
INSERT INTO COMPLIMENTS (C_SENDER, C_RECEIVER, C_MESSAGE) VALUES ('LCAHILL', 'DCAHILL', 'HOPE YOU ENJOY YOUR NEW APARTMENT!' );


SELECT * FROM COMPLIMENTS;
SELECT * FROM USERS;
