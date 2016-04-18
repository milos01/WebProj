INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Admin');
INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Regular user');
INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Cook');
INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Barman');
INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Manager');
INSERT INTO `questforchef`.`Roles` (`roleName`) VALUES ('Bidder');
INSERT INTO `questforchef`.`Users` (`email`, `enabled`, `firstName`, `lastName`, `password`, `tokenExpired`, `role_id`) VALUES ('milosa942@gmail.com', 1, 'Milos', 'Andric', 'pass', 1, '2');
INSERT INTO `questforchef`.`Users` (`email`, `enabled`, `firstName`, `lastName`, `password`, `tokenExpired`, `role_id`) VALUES ('miloso@gmail.com', 1, 'Milos', 'Obradovic', 'pass', 1, '1');
INSERT INTO `questforchef`.`Restaurants` (`address`, `desription`, `email`, `name`, `open_hours`, `phone`, `rate`) VALUES ('test', 'desc', 'rest01', 'name', 'sedasd', 'sasd', '3');
INSERT INTO `questforchef`.`Staff` (`birth_date`, `con_num`, `email`, `firstName`, `lastName`, `password`, `shoe_num`, `restaurant_id`, `role_id`) VALUES ('', 'xx', 'sda', 'Miki', 'mikonja', 'pass', '34', '1', '3');
INSERT INTO `questforchef`.`Staff` (`email`, `firstName`, `lastName`, `password`, `shoe_num`, `restaurant_id`, `role_id`) VALUES ('asd', 'Niki', 'nikonja', 'pass', '34', '1', '4');
UPDATE `questforchef`.`Staff` SET `con_num`='cc' WHERE `id`='2';
UPDATE `questforchef`.`Restaurants` SET `address`='16 Buena Vista Parkway', `desription`='Oth arthrotomy-wrist', `email`='apayne0@com.com', `name`='Wikibox', `phone`='86-(176)172-8172' WHERE `id`='1';
INSERT INTO `questforchef`.`Restaurants` (`address`, `desription`, `email`, `name`, `phone`) VALUES ('06427 Waxwing Place', 'Ovarian biopsy NEC', 'lday1@japanpost.jp', 'Meevee', '355-(119)203-3363');
INSERT INTO `questforchef`.`Restaurants` (`address`, `desription`, `email`, `name`, `phone`, `rate`) VALUES ('99140 Doe Crossing Lane', 'resacral sympathectomy', 'wwells2@phpbb.com', 'Meembee', '351-(108)619-6647', '2');
INSERT INTO `questforchef`.`Restaurants` (`address`, `desription`, `email`, `name`, `phone`, `rate`) VALUES ('9025 Sutteridge Street', 'Cl reduct malar/zygo fx', 'hgarrett3@ed.gov', 'Twitternation', '380-(257)526-4752', '4');
UPDATE `questforchef`.`Restaurants` SET `rate`='5' WHERE `id`='2';
INSERT INTO `questforchef`.`Restaurants` (`address`, `desription`, `email`, `name`, `open_hours`, `phone`, `rate`) VALUES ('1 Clarendon Crossing', 'Local excis rectal les', 'mwoods4@mozilla.com', 'Brightbean', '', '348-(881)503-7377', '5');




