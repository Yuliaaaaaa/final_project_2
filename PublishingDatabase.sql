CREATE DATABASE Publishing;

USE Publishing;

CREATE TABLE Users(
user_id int AUTO_INCREMENT NOT NULL,
first_name nvarchar(30) NOT NULL,
last_name nvarchar(40) NOT NULL,
birth_date date,
sex char(1) NOT NULL,
phone_number bigint,
email nvarchar(50) NOT NULL,
`password` nvarchar(30) NOT NULL,

PRIMARY KEY(user_id),
CONSTRAINT unique_email UNIQUE KEY(email),
CONSTRAINT unique_phone_number  UNIQUE KEY(phone_number)
);

CREATE TABLE Editions(
edition_id int AUTO_INCREMENT NOT NULL,
edition_title nvarchar(40) NOT NULL,
category nvarchar(30) NOT NULL,
periodicity nvarchar(10) NOT NULL,
`description` nvarchar(100) NOT NULL,
price double NOT NULL, 

PRIMARY KEY(edition_id),
INDEX(edition_title),
INDEX(category),
INDEX(price)
);

CREATE TABLE `Deleted Editions`(
edition_id int NOT NULL,
edition_title nvarchar(40) NOT NULL,
category nvarchar(30) NOT NULL,
periodicity nvarchar(10) NOT NULL,
`description` nvarchar(100) NOT NULL,
price double NOT NULL, 

PRIMARY KEY(edition_id)
);

CREATE TRIGGER DeleteEdition 
AFTER DELETE ON Editions 
FOR EACH ROW
INSERT INTO `Deleted Editions`(edition_id, edition_title, category, periodicity, `description`, price)
VALUES (old.edition_id, old.edition_title, old.category, old.periodicity, old.`description`, old.price);

CREATE TABLE Subscriptions(
subscription_id int AUTO_INCREMENT NOT NULL,
user_id int NOT NULL,
edition_id int NOT NULL,
issues_quantity int NOT NULL,
order_date datetime,
is_paid bit NOT NULL,

PRIMARY KEY(subscription_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id)
ON DELETE CASCADE
ON UPDATE CASCADE,
INDEX(user_id),
INDEX(edition_id)
);

CREATE TABLE Payments(
payment_id int AUTO_INCREMENT NOT NULL,
user_id int,
payment_sum double NOT NULL,
payment_date datetime NOT NULL,
PRIMARY KEY(payment_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id),
INDEX(user_id)
);

CREATE TABLE `Payments Details`(
details_id int AUTO_INCREMENT NOT NULL,
payment_id int NOT NULL, 
subscription_id int NOT NULL,
PRIMARY KEY(details_id),
FOREIGN KEY(payment_id) REFERENCES Payments(payment_id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY(subscription_id) REFERENCES Subscriptions(subscription_id)
ON DELETE CASCADE
ON UPDATE CASCADE,
INDEX(payment_id),
INDEX(subscription_id)
);

INSERT INTO Users (first_name, last_name, birth_date, sex, phone_number,email,`password`)
VALUES ('Polina', 'Tikhonova',Date("2003-07-05"),'f', 0984367364, 'polya@gmail.com', 'zhora' ),
('Yulia', 'Shcherbakova',Date("2000-09-03"),'f', 0765432325, 'yulia@gmail.com', '111111' ),
('Коля', 'Сахарок',Date("2000-12-03"),'m', 0456765432, 'kolya@gmail.com', '000000' );

INSERT INTO Editions(edition_title, category, periodicity, `description`, price)
VALUES ('Сканворды', 'mind-breaker', 'weekly', '100 сканвордов', 5.00),
('Сад мечты', 'gardening', 'monthly', 'Журнал со сборкой рекомендаций по садоводству', 10.50),
('Vogue', 'fashion', 'monthly', 'Popular magazine about fashion', 30.00),
('Zhytomyr daily', 'newspaper', 'daily', 'Newspaper about life in Zhytomyr', 3.00);

INSERT INTO Subscriptions(user_id, edition_id, issues_quantity,order_date, is_paid)
VALUES (1, 1, 5, Timestamp("2019-07-09", "07:33:53"), 1),
(2, 2, 3, Timestamp("2019-07-13", "13:40:07"), 1),
(3, 3, 5, Timestamp("2019-07-17", "23:01:30"), 1),
(2, 1, 7, NULL, 0),
(2, 4, 10, NULL, 0);

INSERT INTO Payments(user_id, payment_sum, payment_date)
VALUES (1, 25.00, Timestamp("2019-07-09", "07:33:53")),
(2, 31.50, Timestamp("2019-07-13", "13:40:07")),
(3, 150.00, Timestamp("2019-07-17", "23:01:30"));

INSERT INTO `Payments Details`(payment_id, subscription_id)
VALUES (1, 1),
(2, 2),
(3, 3);