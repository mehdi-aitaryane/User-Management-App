USE umdb;

DELETE FROM admin_account;

DELETE FROM user_account;

DELETE FROM account;

DELETE FROM token_storage;


INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (1, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\amine-msiouri.jpg") , 'Amine', 'MSIOURI', 'Amine', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (2, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\craig-mckay.jpg") , 'Craig', 'MCKAY', 'Craig', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (3, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\david-garrison.jpg") , 'David', 'GARRISON', 'David', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (4, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\kevin-bidwell.jpg") , 'Kevin', 'BIDWELL', 'Kevin', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (5, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\murat-esibatir.jpg") , 'Murat', 'ESIBATIR', 'Murat', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (6, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\renzy-atibagos.jpg") , 'Renzy', 'ATIBAGOS', 'Renzy', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (7, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\simon-robben.jpg") , 'Simon', 'ROBBEN', 'Simon', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO account (id, image, firstname, lastname, username, password) VALUES (8, LOAD_FILE("C:\\ProgramData\\MySQL\\MySQL Server 8.2\\Uploads\\umdb\\spencer-selover.jpg") , 'Spencer', 'SELOVER', 'Spencer', '$2a$12$yue88kbnK8u8e08RUjq1Iuq0xfifV2bLuz9Qz3DxOCHNwSYV4Z2dm');

INSERT INTO admin_account (id) VALUES (1);

INSERT INTO user_account (id) VALUES (2);

INSERT INTO user_account (id) VALUES (3);

INSERT INTO user_account (id) VALUES (4);

INSERT INTO user_account (id) VALUES (5);

INSERT INTO user_account (id) VALUES (6);

INSERT INTO user_account (id) VALUES (7);

INSERT INTO user_account (id) VALUES (8);


