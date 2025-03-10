INSERT INTO algafood.kitchen (name) VALUES ("Brazilian"), ("Chinese");

INSERT INTO algafood.state (name) VALUES ('Leinster'), ('Munster');

INSERT INTO algafood.city (name, state_id) VALUES ('Dublin', 1), ('Cork', 2);

INSERT INTO algafood.permission (name, description) VALUES ('MANAGE_USERS', 'Permission to manage users'), ('MANAGE_ORDERS', 'Permission to manage orders');

INSERT INTO algafood.payment_method (description) VALUES ('Credit Card'), ('Cash'), ('Debit Card');

INSERT INTO algafood.restaurant (name, shipping_fee, kitchen_id, address_postcode, address_street, address_number, address_complement, address_neighborhood, address_city_id, created_at, update_at) VALUES ("Musashi", 10.0, 2, 'D01', '123 Dublin St', '10', 'Apt 1', 'Downtown', 1, utc_timestamp, utc_timestamp), ("Abraquebabra", 3.50, 1, 'D02', '456 Dublin Rd', '20', 'Suite 2', 'Suburb', 2, utc_timestamp, utc_timestamp);

INSERT INTO algafood.restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2);

INSERT INTO algafood.product (name, description, price, is_active, restaurant_id) VALUES ("Sushi", "Made of rice and prawns", 6.0, true, 1), ("Hamburguer", "Halal meat", 12.0, true, 2), ("Iamen", "Directly from china", 15.0, false, 1);