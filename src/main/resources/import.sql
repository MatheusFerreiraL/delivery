INSERT INTO kitchen (name) VALUES ("Brazilian"), ("Chinese");

INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ("Musashi", 10.0, 2), ("Abraquebabra", 3.50, 1);

INSERT INTO state (name) VALUES ('Leinster'), ('Munster');

INSERT INTO permission (name, description) VALUES ('MANAGE_USERS', 'Permission to manage users'), ('MANAGE_ORDERS', 'Permission to manage orders');

INSERT INTO payment_method (description) VALUES ('Credit Card'), ('Cash');

INSERT INTO city (name, state_id) VALUES ('Dublin', 1), ('Cork', 2);


INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (2, 1), (2, 2);