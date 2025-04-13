CREATE TABLE payment_method (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(80) NOT NULL,
    
    CONSTRAINT pk_payment_method PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE restaurant (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    shipping_fee DECIMAL(10,2) NOT NULL,
    kitchen_id BIGINT NOT NULL,
    address_city_id BIGINT,
    address_postcode VARCHAR(20),
    address_street VARCHAR(100),
    address_number VARCHAR(20),
    address_complement VARCHAR(100),
    address_neighborhood VARCHAR(100),
    created_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    
    CONSTRAINT pk_restaurant PRIMARY KEY (id),
    CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_id) REFERENCES kitchen (id),
    CONSTRAINT fk_restaurant_city FOREIGN KEY (address_city_id) REFERENCES city (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_active BIT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    
    CONSTRAINT pk_product PRIMARY KEY (id),
    CONSTRAINT fk_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    
    CONSTRAINT pk_user PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE permission (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    description VARCHAR(255) NOT NULL,
    
    CONSTRAINT pk_permission PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_group (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    
    CONSTRAINT pk_user_group PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE restaurant_payment_method (
    restaurant_id BIGINT NOT NULL,
    payment_method_id BIGINT NOT NULL,
    
    CONSTRAINT pk_restaurant_payment_method PRIMARY KEY (restaurant_id, payment_method_id),
    CONSTRAINT fk_rpm_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    CONSTRAINT fk_rpm_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE group_permission (
    group_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    
    CONSTRAINT pk_group_permission PRIMARY KEY (group_id, permission_id),
    CONSTRAINT fk_gp_group FOREIGN KEY (group_id) REFERENCES user_group (id),
    CONSTRAINT fk_gp_permission FOREIGN KEY (permission_id) REFERENCES permission (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_group_association (
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    
    CONSTRAINT pk_user_group_assoc PRIMARY KEY (user_id, group_id),
    CONSTRAINT fk_uga_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_uga_group FOREIGN KEY (group_id) REFERENCES user_group (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;