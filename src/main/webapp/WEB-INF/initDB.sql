DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS guest;

CREATE TABLE guest (
id  INTEGER AUTO_INCREMENT,
login  TEXT,
password  TEXT,
PRIMARY KEY (id)
);

CREATE TABLE book (
id  INTEGER AUTO_INCREMENT,
record  TEXT,
date TEXT,
guest_id  INTEGER,
PRIMARY KEY (id),
CONSTRAINT fk FOREIGN KEY (guest_id) REFERENCES guest (id) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO guest (login, password) VALUES ('логин1', '12345');
INSERT INTO guest (login, password) VALUES ('логин2', '23456');
INSERT INTO guest (login, password) VALUES ('логин3', '34567');
INSERT INTO guest (login, password) VALUES ('логин4', '45678');
INSERT INTO guest (login, password) VALUES ('логин5', '56789');
INSERT INTO guest (login, password) VALUES ('логин6', '67890');
INSERT INTO guest (login, password) VALUES ('логин7', '11111');

INSERT INTO book (record, date, guest_id) VALUES ('сообщение1', '03.07.2015 19:07', '1');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение2', '03.07.2015 19:08', '1');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение3', '03.07.2015 19:09', '2');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение4', '03.07.2015 19:10', '3');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение5', '03.07.2015 19:11', '4');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение6', '03.07.2015 19:12', '5');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение7', '03.07.2015 19:30', '5');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение8', '03.07.2015 19:31', '4');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение9', '03.07.2015 19:32', '3');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение10', '03.07.2015 19:33', '2');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение11', '03.07.2015 19:34', '6');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение12', '03.07.2015 19:35', '6');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение13', '03.07.2015 19:36', '7');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение14', '03.07.2015 19:37', '7');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение15', '03.07.2015 19:40', '2');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение16', '03.07.2015 19:41', '6');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение17', '03.07.2015 19:42', '6');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение18', '03.07.2015 19:43', '7');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение19', '03.07.2015 19:44', '7');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение20', '03.07.2015 19:45', '1');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение21', '03.07.2015 19:46', '2');
INSERT INTO book (record, date, guest_id) VALUES ('сообщение22', '03.07.2015 19:47', '3');