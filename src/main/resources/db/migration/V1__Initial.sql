CREATE TABLE customer
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birthdate  DATE         NOT NULL
);

CREATE TABLE vehicle
(
    id         BIGSERIAL PRIMARY KEY,
    brand      VARCHAR(255)   NOT NULL,
    model      VARCHAR(255)   NOT NULL,
    model_year INT            NOT NULL,
    vin        VARCHAR(17),
    price      NUMERIC(10, 2) NOT NULL
);

CREATE TABLE leasing_contract
(
    id              BIGSERIAL PRIMARY KEY,
    contract_number VARCHAR(255)   NOT NULL,
    monthly_rate    NUMERIC(10, 2) NOT NULL,
    customer_id     BIGINT,
    vehicle_id      BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);




