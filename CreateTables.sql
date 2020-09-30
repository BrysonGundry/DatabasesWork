SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE ORDERS (
    ID INT NOT NULL,
    CONSTRAINT PKEY_ORDERID_ORGIN PRIMARY KEY (ID),
    TOTALCOSTBUS FLOAT(2),
    TOTALPRICECUSTOMER FLOAT(2)
);

CREATE TABLE CUSTOMER (
    CUSTID INT NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (CUSTID),
    TYPE VARCHAR(255) NOT NULL,
    ORDERID INT,
    CONSTRAINT FKEY_ORDERID FOREIGN KEY (ORDERID) REFERENCES ORDERS (ID)
);

CREATE TABLE PIZZA (
    PID INT NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (PID),
    NUMBER_OF INT NOT NULL,
    PRICE FLOAT(2),
    COST_TO_COMPANY FLOAT(2),
    TIME_STAMP TIMESTAMP,
    ORDERID INT NOT NULL,
    CONSTRAINT FKEY_ORDERID_PIZZA FOREIGN KEY (ORDERID) REFERENCES ORDERS (ID),
    BPID INT NOT NULL,
    CONSTRAINT FKEY_BPID FOREIGN KEY (BPID) REFERENCES BASE_PRICES (ID)
);

CREATE TABLE TOPPINGS (
    ID INT NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (ID),
    PRICE FLOAT(2),
    COST_PER_UNIT FLOAT(2),
    INVENTORY INT(5),
    SMALL FLOAT(2),
    MEDIUM FLOAT(2),
    LARGE FLOAT(2),
    X_LARGE FLOAT(2)
);

CREATE TABLE TOPPINGS_ON_PIZZA (
    ID INT NOT NULL,
    TOPPING_NAME INT NOT NULL,
    EXTRA BOOLEAN NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (ID, TOPPING_NAME),
    CONSTRAINT FKEY_ID FOREIGN KEY (ID) REFERENCES PIZZA (PID),
    CONSTRAINT FKEY_TOPPING_NAME FOREIGN KEY (TOPPING_NAME) REFERENCES TOPPINGS (ID)
);

CREATE TABLE BASE_PRICES (
    ID INT NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (ID),
    SIZE VARCHAR(7),
    CRUST VARCHAR(11),
    PRICE FLOAT(2),
    COST FLOAT(2)
);

CREATE TABLE DISCOUNTS (
    ID INT NOT NULL,
    NAME VARCHAR(100),
    CONSTRAINT PKEY PRIMARY KEY (ID),
    PERCENT_OFF FLOAT(2),
    DOLLAR_OFF FLOAT(2)
);

CREATE TABLE DISCOUNT_TO_ORDER (
    DISCOUNT_ID INT NOT NULL,
    ORDER_ID INT NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (DISCOUNT_ID, ORDER_ID),
    CONSTRAINT FKEY_DISCOUNT_ID FOREIGN KEY (DISCOUNT_ID) REFERENCES DISCOUNTS(ID),
    CONSTRAINT FKEY_ORDER_ID FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID)
);


CREATE TABLE DELIVERY (
    ID INT NOT NULL,
    CONSTRAINT FKEY_ID_DELIVERY FOREIGN KEY (ID) REFERENCES CUSTOMER (CUSTID),
    CONSTRAINT PKEY PRIMARY KEY (ID),
    FNAME VARCHAR(255),
    LNAME VARCHAR(255),
    PHONENUM VARCHAR(15),
    ADDRESS VARCHAR(255)
);

CREATE TABLE DINE_IN (
    ID INT NOT NULL,
    CONSTRAINT FKEY_ID_DINEIN FOREIGN KEY(ID) REFERENCES CUSTOMER (CUSTID),
    CONSTRAINT PKEY PRIMARY KEY (ID),
    TABLENUM INT,
    SEATNUMS VARCHAR(255)
);

CREATE TABLE PICKUP (
    ID INT NOT NULL,
    CONSTRAINT FKEY_ID_PICKUP FOREIGN KEY (ID) REFERENCES CUSTOMER (CUSTID),
    CONSTRAINT PKEY PRIMARY KEY (ID),
    FNAME VARCHAR(255),
    LNAME VARCHAR(255),
    PHONENUM VARCHAR(15)
);

CREATE TABLE STATUS (
    ID INT NOT NULL,
    CONSTRAINT PKEY PRIMARY KEY (ID),
    COMPLETED BOOLEAN
);

SET FOREIGN_KEY_CHECKS=1;
