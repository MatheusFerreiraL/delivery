create table `order` (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    shipping_fee decimal(10,2) not null,
    total decimal (10,2) not null,
    created_at datetime not null,
    confirmed_at datetime,
    cancelled_at datetime,
    completed_at datetime,
    customer_id bigint not null,
    restaurant_id bigint not null,
    payment_method_id bigint not null,
    address_city_id bigint not null,
    address_postcode varchar(20),
    address_street varchar(100),
    address_number varchar(20),
    address_complement varchar(100),
    address_neighborhood varchar(100),
    
    constraint pk_order primary key (id),
    constraint fk_order_customer foreign key (customer_id) references user (id),
    constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_order_payment_method foreign key (payment_method_id) references payment_method (id),
    constraint fk_order_address_city_id foreign key (address_city_id) references city (id)
) engine=InnoDB default charset=utf8mb4;

create table order_item (
    id bigint not null auto_increment,
    quantity smallint not null,
    unit_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    note varchar(100),
    order_id bigint,
    
    constraint pk_order_item primary key (id),
    constraint fk_order_item_order_id foreign key (order_id) references `order` (id)

) engine=InnoDB default charset=utf8mb4;