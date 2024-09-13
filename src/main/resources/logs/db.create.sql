CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    restaurant_id UUID NOT NULL,
    total_price DOUBLE,
    status VARCHAR(255),
    order_time TIMESTAMP,
    create_at TIMESTAMP,
    modify_at TIMESTAMP,
    modify_by VARCHAR(64),
    create_by VARCHAR(64)
);;

CREATE TABLE order_items (
    id UUID PRIMARY KEY,
    cart_id UUID,
    order_id UUID,
    restaurant_id UUID,
    item_id UUID,
    quantity INT,
    price DOUBLE PRECISION
);