-- noinspection SqlNoDataSourceInspectionForFile

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE user_group_association;
TRUNCATE TABLE user_group;
TRUNCATE TABLE group_permission;
TRUNCATE TABLE restaurant_payment_method;
TRUNCATE TABLE product;
TRUNCATE TABLE restaurant;
TRUNCATE TABLE city;
TRUNCATE TABLE state;
TRUNCATE TABLE kitchen;
TRUNCATE TABLE payment_method;
TRUNCATE TABLE permission;
TRUNCATE TABLE `user`;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO algafood.kitchen (name) VALUES("Brazilian"),("Chinese"),("French"),("Italian"),("Japanese"),("Mexican"),("Indian"),("Thai");

INSERT INTO algafood.state (name) VALUES ('Leinster'), ('Munster'), ('Ulster');

INSERT INTO algafood.city (name, state_id) VALUES ('Dublin', 1), ('Cork', 2), ('Galway', 3);

INSERT INTO algafood.permission (name, description) VALUES ('MANAGE_USERS', 'Permission to manage users'), ('MANAGE_ORDERS', 'Permission to manage orders');

INSERT INTO algafood.payment_method (description) VALUES ('Credit Card'), ('Cash'), ('Debit Card');

INSERT INTO algafood.restaurant (name, shipping_fee, kitchen_id, address_postcode, address_street, address_number, address_complement, address_neighborhood, address_city_id, created_at, update_at) VALUES ("Musashi", 10.0, 2, 'D01', '123 Dublin St', '10', 'Apt 1', 'Downtown', 1, utc_timestamp, utc_timestamp), ("Abraquebabra", 3.50, 1, 'D02', '456 Dublin Rd', '20', 'Suite 2', 'Suburb', 2, utc_timestamp, utc_timestamp),("La Maison", 5.0, 3, 'D03', '789 Cork St', '30', 'Apt 3', 'Center', 2, utc_timestamp, utc_timestamp), ("Pasta House", 7.0, 1, 'D04', '101 Galway Rd', '40', 'Suite 4', 'Westside', 3, utc_timestamp, utc_timestamp),("Sushi World", 12.0, 2, 'D05', '202 Dublin Ave', '50', 'Apt 5', 'Eastside', 1, utc_timestamp, utc_timestamp),("Burger King", 4.0, 1, 'D06', '303 Cork Blvd', '60', 'Suite 6', 'Northside', 2, utc_timestamp, utc_timestamp),("Pizza Palace", 8.0, 3, 'D07', '404 Galway St', '70', 'Apt 7', 'Southside', 3, utc_timestamp, utc_timestamp),("Taco Town", 6.0, 2, 'D08', '505 Dublin Rd', '80', 'Suite 8', 'Downtown', 1, utc_timestamp, utc_timestamp);

INSERT INTO algafood.restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2),(3, 1), (3, 2), (3, 3),(4, 1), (4, 2),(5, 1), (5, 2), (5, 3),(6, 1), (6, 2),(7, 1), (7, 2), (7, 3),(8, 1), (8, 2);

INSERT INTO algafood.product (name, description, price, is_active, restaurant_id) VALUES ("Sushi", "Made of rice and prawns", 6.0, true, 1), ("Hamburguer", "Halal meat", 12.0, true, 2), ("Iamen", "Directly from china", 15.0, false, 1);