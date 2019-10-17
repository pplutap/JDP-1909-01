insert into shopping_order(id, buyer_id, seller_id, purchase_date, status, delivery_date)
values (31, 21, 22, '2019-09-01', 'DELIVERED', '2019-09-06'),
       (32, 21, 22, '2019-09-01', 'DELIVERED', '2019-09-07'),
       (33, 21, 23, '2019-09-03', 'REGISTERED', '2019-09-05'),
       (34, 21, 23, '2019-09-01', 'CANCELED', '2019-09-05'),
       (35, 22, 23, '2019-09-04', 'RETURNED', '2019-09-10');

insert into join_order_product(order_id, product_id)
values (31, 11),
       (31, 13),
       (32, 12),
       (32, 14),
       (32, 15),
       (33, 13),
       (34, 16),
       (34, 17),
       (35, 11),
       (35, 14),
       (35, 13),
       (35, 17);