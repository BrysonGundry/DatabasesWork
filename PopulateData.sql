insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (1, "SMALL", "THIN", 3, 0.5);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (2, "SMALL", "ORIGINAL", 3, 0.75);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (3, "SMALL", "PAN", 3.5, 1);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (4, "SMALL", "GLUTEN-FREE", 4, 2);

insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (5, "MEDIUM", "THIN", 5, 1);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (6, "MEDIUM", "ORIGINAL", 5, 1.5);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (7, "MEDIUM", "PAN", 6, 2.25);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (8, "MEDIUM", "GLUTEN-FREE", 6.25, 3);

insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (9, "LARGE", "THIN", 8, 1.25);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (10, "LARGE", "ORIGINAL", 8, 2);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (11, "LARGE", "PAN", 9, 3);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (12, "LARGE", "GLUTEN-FREE", 9.5, 4);

insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (13, "X-LARGE", "THIN", 10, 2);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (14, "X-LARGE", "ORIGINAL", 10, 3);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (15, "X-LARGE", "PAN", 11.5, 4.5);
insert into BASE_PRICES (ID, SIZE, CRUST, PRICE, COST) values (16, "X-LARGE", "GLUTEN-FREE", 12.5, 6);


insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (1, "Pepperoni", 1.25, 0.2, 100, 2, 2.75, 3.5, 4.5);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (2, "Sausage", 1.25, 0.15, 100, 2.5, 3, 3.5, 4.25);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (3, "Ham", 1.5, 0.15, 78, 2, 2.5, 3.25, 4);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (4, "Chicken", 1.75, 0.25, 56, 1.5, 2, 2.25, 3);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (5, "Green Pepper", 0.5, 0.02, 79, 1, 1.5, 2, 2.5);

insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (6, "Onion", 0.5, 0.02, 85, 1, 1.5, 2, 2.75);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (7, "Roma tomato", 0.75, 0.03, 86, 2, 3, 3.5, 4.5);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (8, "Mushrooms", 0.75, 0.1, 52, 1.5, 2, 2.5, 3);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (9, "Black Olives", 0.6, 0.1, 39, 0.75, 1, 1.5, 2);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (10, "Pineapple", 1, 0.25, 15, 1, 1.25, 1.75, 2);

insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (11, "Jalapenos", 0.5, 0.05, 64, 0.5, 0.75, 1.25, 1.75);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (12, "Banana Peppers", 0.5, 0.05, 36, 0.6, 1, 1.3, 1.75);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (13, "Regular Cheese", 1.5, 0.12, 250, 2, 3.5, 5, 7);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (14, "Four Cheese Blend", 2, 0.15, 150, 2, 3.5, 5, 7);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (15, "Feta Cheese", 2, 0.18, 75, 1.75, 3, 4, 5.5);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (16, "Goat Cheese", 2, 0.2, 54, 1.6, 2.75, 4, 5.5);
insert into TOPPINGS (ID, NAME, PRICE, COST_PER_UNIT, INVENTORY, SMALL, MEDIUM, LARGE, X_LARGE) values (17, "Bacon", 1.5, 0.25, 89, 1, 1.5, 2, 3);


insert into DISCOUNTS (ID, NAME, PERCENT_OFF, DOLLAR_OFF) values (1, "employee", 15, 0);
insert into DISCOUNTS (ID, NAME, PERCENT_OFF, DOLLAR_OFF) values (2, "Lunch Special Medium", 0, 1);
insert into DISCOUNTS (ID, NAME, PERCENT_OFF, DOLLAR_OFF) values (3, "Lunch Special Large", 0, 2);
insert into DISCOUNTS (ID, NAME, PERCENT_OFF, DOLLAR_OFF) values (4, "Specialty Pizza", 0, 1.5);
insert into DISCOUNTS (ID, NAME, PERCENT_OFF, DOLLAR_OFF) values (5, "Gameday special", 20, 0);

SET FOREIGN_KEY_CHECKS=0;

/* First Order */ 
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (1, 1, 13.50, 3.68, '2020-03-05 12:03:00', 1, 9);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (1, 13, TRUE), (1, 1, FALSE), (1, 2, FALSE);
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (3, 1);
insert into DINE_IN (ID, TABLENUM, SEATNUMS) values (1, 14, "1,2,3");
insert into STATUS (ID, COMPLETED) values (1, TRUE);

/* Second Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (2, 1, 10.60, 3.23, '2020-03-03 12:05:00', 2, 7);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (2, 15, FALSE), (2, 9, FALSE), (2, 7, FALSE), (2, 8, FALSE), (2, 12, FALSE);
insert into DINE_IN (ID, TABLENUM, SEATNUMS) values (2, 4, "1");
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (2, 2);
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (4, 2);
insert into STATUS (ID, COMPLETED) values (2, TRUE);
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (3, 1, 6.75, 1.40, '2020-03-03 12:05:00', 3, 2);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (3, 13, FALSE), (3, 4, FALSE), (3, 12, FALSE);
insert into DINE_IN (ID, TABLENUM, SEATNUMS) values (3, 4, "2");
insert into STATUS (ID, COMPLETED) values (3, TRUE);

/* Third Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (4, 6, 10.75, 3.30, '2020-03-03 9:30:00', 4, 10);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (4, 13, FALSE), (4, 1, FALSE);
insert into PICKUP (ID, FNAME, LNAME, PHONENUM) values (1, 'Andrew', 'Wilkes-Krier', '864-254-5861');
insert into STATUS (ID, COMPLETED) values (4, TRUE);

/* Fourth Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (5, 1, 14.50, 5.59, '2020-03-05 19:11:00', 5, 14);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (5,1, FALSE), (5,2, FALSE), (5,14,FALSE);
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (6, 1, 17.00, 5.59, '2020-03-05 19:11:00', 5, 14);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (6,10, TRUE), (6,3, TRUE), (6,14,FALSE);
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (7, 1, 14.00, 5.68, '2020-03-05 19:11:00', 5, 14);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (7,11, FALSE), (7,17, FALSE), (7,14,FALSE);
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (5, 5);
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (4, 5);
insert into DELIVERY (ID, FNAME, LNAME, PHONENUM, ADDRESS) values (1, 'Andrew', 'Wilkes-Krier', '864-254-5861', '115 Party Blvd, Anderson, SC, 29621');
insert into STATUS (ID, COMPLETED) values (5, TRUE);

/* Fifth Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (8, 1, 16.85, 7.85, '2020-03-02 17:30:00', 6, 16);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (8,5, FALSE), (8,16, FALSE), (8,6, FALSE), (8,7, FALSE), (8, 8, FALSE), (8,9, FALSE);
insert into DISCOUNT_TO_ORDER (DISCOUNT_ID, ORDER_ID) values (4, 6);
insert into PICKUP (ID, FNAME, LNAME, PHONENUM) values (3, 'Matt', 'Engers', '864-474-9953');
insert into STATUS (ID, COMPLETED) values (6, TRUE);

/* Sixth Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (9, 1, 13.25, 3.20, '2020-03-02 18:17:00', 7, 9);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (9,4, FALSE), (9,5, FALSE), (9,6,FALSE), (9,8,FALSE), (9,14,FALSE);
insert into DELIVERY (ID, FNAME, LNAME, PHONENUM, ADDRESS) values (4, 'Frank', 'Turner', '864-232-8944', '6745 Wessex St, Anderson, SC, 29621');
insert into STATUS (ID, COMPLETED) values (7, TRUE);

/* Seventh Order */
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (10, 1, 12.00, 3.75, '2020-03-06 20:32:00', 8, 9);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (10,14, TRUE);
insert into PIZZA (PID, NUMBER_OF, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID) values (11, 1, 12.00, 2.55, '2020-03-06 20:32:00', 8, 9);
insert into TOPPINGS_ON_PIZZA (ID, TOPPING_NAME, EXTRA) values (11,1, TRUE), (11, 13, FALSE);
insert into DELIVERY (ID, FNAME, LNAME, PHONENUM, ADDRESS) values (5, 'Milo', 'Auckerman', '864-878-5679', '8879 Suburban Home, Anderson, SC, 29621');
insert into STATUS (ID, COMPLETED) values (8, TRUE);

SET FOREIGN_KEY_CHECKS=1; 
