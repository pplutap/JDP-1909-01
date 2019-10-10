insert into shopping_order(id, buyer_id, seller_id, purchase_date, status, delivery_date)
values (31, 21, 22, '2019-09-01T10:30:00', 'DELIVERED', '2019-09-06T10:30:00'),
       (32, 21, 22, '2019-09-02T10:30:00', 'DELIVERED', '2019-09-07T10:30:00'),
       (33, 21, 23, '2019-09-03T10:30:00', 'REGISTERED', '2019-09-05T10:30:00'),
       (34, 21, 23, '2019-09-01T10:30:00', 'CANCELED', '2019-09-05T10:30:00'),
       (35, 22, 23, '2019-09-04T10:30:00', 'RETURNED', '2019-09-10T10:30:00');

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