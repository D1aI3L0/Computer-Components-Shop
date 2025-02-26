DROP SCHEMA IF EXISTS shop CASCADE;

CREATE SCHEMA IF NOT EXISTS shop AUTHORIZATION postgres;

CREATE TABLE shop.Client
(
	id SERIAL PRIMARY KEY,
	phone_number VARCHAR(64),
	name VARCHAR(32),
	surname VARCHAR(32),
	patronymic VARCHAR(32),
	email_address VARCHAR(64) UNIQUE
);

CREATE TABLE shop.Processor
(
	id SERIAL PRIMARY KEY,
	threads_count INTEGER,
	clock_frequency REAL,
	max_frequency REAL,
	cpu_count INTEGER
);

CREATE TABLE shop.Power_Supply
(
	id SERIAL PRIMARY KEY,
	power INTEGER,
	PLUS80_sertificate VARCHAR(32),
	form_factor VARCHAR(32)
);

CREATE TABLE shop.Motherboard
(
	id SERIAL PRIMARY KEY,
	memory_slots INTEGER,
	chipset VARCHAR(32),
	form_factor VARCHAR(32)
);

CREATE TABLE shop.Graphic_Card
(
	id SERIAL PRIMARY KEY,
	gpu_count INTEGER,
	gpu_frequency REAL,
	memory_count INTEGER,
	memory_frequency REAL
);

CREATE TABLE shop.Product
(
	id SERIAL PRIMARY KEY,
	price MONEY,
	name VARCHAR(64),
	manufacturer VARCHAR(64),
	type VARCHAR(32),
	processor_id INTEGER REFERENCES shop.Processor (id),
	motherboard_id INTEGER REFERENCES shop.Motherboard (id),
	graphic_card_id INTEGER REFERENCES shop.Graphic_Card (id),
	power_supply_id INTEGER REFERENCES shop.Power_Supply (id)
);

CREATE TABLE shop.Order
(
	order_number SERIAL PRIMARY KEY,
	total_price MONEY,
	order_date DATE,
	status VARCHAR(64),
	payment_method VARCHAR(32),
	client_id INTEGER REFERENCES shop.Client (id)
);

CREATE TABLE shop.Product_Order
(
	order_number SERIAL PRIMARY KEY,
	product_id INTEGER REFERENCES shop.Product (id),
	order_id INTEGER REFERENCES shop.Order (order_number)
);

CREATE TABLE shop.Review
(
	id SERIAL PRIMARY KEY,
	client_id INTEGER REFERENCES shop.Client (id),
	product_id INTEGER REFERENCES shop.Product (id),
	Review VARCHAR(512)
);