USE actualretro;
SHOW TABLES;
CREATE TABLE shopping_cart (
	ID_SHOPPING_CART bigint,
	TOTAL_PRICE float(100, 2),
    TOTAL_PRODUCTS bigint,
    PRIMARY KEY (ID_SHOPPING_CART));
CREATE TABLE vdconsole (
	ID bigint,
    DESCRIPTION varchar(16000),
    MAXCONTROLLERS int,
    NAME varchar(40),
    PRICE float(10, 2),
    STOCK int,
    PRIMARY KEY (ID));
CREATE TABLE videogame (
	ID bigint,
    DESCRIPTION varchar(16000),
    GENRE int,
    NAME varchar(40),
    PEGI int,
    PRICE float(10, 2),
    STOCK int,
    PRIMARY KEY (ID));
CREATE TABLE users (
	ID bigint,
	MAIL varchar(100),
    NAME varchar(20),
    PASSWORD varchar(100),
    PHONE varchar(9),
    SURNAME varchar(20),
    SHOPPING_CART_ID_SHOPPING_CART bigint,
    PRIMARY KEY (ID),
    FOREIGN KEY (SHOPPING_CART_ID_SHOPPING_CART) REFERENCES shopping_cart(ID_SHOPPING_CART) ON DELETE SET NULL);
CREATE TABLE shopping_cart_videogame_list (
	SHOPPING_CART_ID_SHOPPING_CART bigint,
    VIDEOGAME_LIST_ID bigint,
    FOREIGN KEY (SHOPPING_CART_ID_SHOPPING_CART) REFERENCES shopping_cart(ID_SHOPPING_CART) ON DELETE CASCADE,
    FOREIGN KEY (VIDEOGAME_LIST_ID) REFERENCES videogame(ID) ON DELETE CASCADE,
    CONSTRAINT PK_SHOPPING_CART_VIDEOGAME_LIST PRIMARY KEY (SHOPPING_CART_ID_SHOPPING_CART, VIDEOGAME_LIST_ID));
CREATE TABLE shopping_cart_console_list (
	SHOPPING_CART_ID_SHOPPING_CART bigint,
    CONSOLE_LIST_ID bigint,
    FOREIGN KEY (SHOPPING_CART_ID_SHOPPING_CART) REFERENCES shopping_cart(ID_SHOPPING_CART) ON DELETE CASCADE,
    FOREIGN KEY (CONSOLE_LIST_ID) REFERENCES vdconsole(ID) ON DELETE CASCADE,
    CONSTRAINT PK_SHOPPING_CART_CONSOLE_LIST PRIMARY KEY (SHOPPING_CART_ID_SHOPPING_CART, CONSOLE_LIST_ID));
CREATE TABLE users_consoles_history (
	USERS_ID bigint,
    CONSOLES_HISTORY_ID bigint,
    FOREIGN KEY (USERS_ID) REFERENCES users(ID) ON DELETE CASCADE,
    FOREIGN KEY (CONSOLES_HISTORY_ID) REFERENCES vdconsole(ID) ON DELETE CASCADE,
    CONSTRAINT PK_USERS_CONSOLES_HISTORY PRIMARY KEY (USERS_ID, CONSOLES_HISTORY_ID));
CREATE TABLE users_videogames_history (
	USERS_ID bigint,
    VIDEOGAMES_HISTORY_ID bigint,
    FOREIGN KEY (USERS_ID) REFERENCES users(ID) ON DELETE CASCADE,
    FOREIGN KEY (VIDEOGAMES_HISTORY_ID) REFERENCES videogame(ID) ON DELETE CASCADE,
    CONSTRAINT PK_USERS_VIDEOGAMES_HISTORY PRIMARY KEY (USERS_ID, VIDEOGAMES_HISTORY_ID));

SELECT * FROM shopping_cart;
SELECT * FROM videogame;
SELECT * FROM vdconsole;
SELECT * FROM users;
SELECT * FROM shopping_cart_videogame_list;
SELECT * FROM users_videogames_history;
TRUNCATE TABLE users_consoles_history;
TRUNCATE TABLE users_videogames_history;
TRUNCATE TABLE shopping_cart_console_list;
TRUNCATE TABLE shopping_cart_videogame_list;
TRUNCATE TABLE users;
TRUNCATE TABLE shopping_cart;
TRUNCATE TABLE vdconsole;
TRUNCATE TABLE videogame;
DROP TABLE users_consoles_history;
DROP TABLE users_videogames_history;
DROP TABLE shopping_cart_console_list;
DROP TABLE shopping_cart_videogame_list;
DROP TABLE users;
DROP TABLE shopping_cart;
DROP TABLE vdconsole;
DROP TABLE videogame;
