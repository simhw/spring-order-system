insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (1, '상의1', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (2, '상의2', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (3, '상의3', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (4, '상의4', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (5, '상의5', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (6, '상의6', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (7, '상의7', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (8, '상의8', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (9, '상의9', 50000, 100, '상세설명입니다.', 'SALE');

insert into PUBLIC.PRODUCT (product_id, name, price, stock, description, state)
values (10, '상의10', 50000, 100, '상세설명입니다.', 'SALE');

// image
insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (1, 'http://localhost:8080/images/product/1-1.jpg', '1-1.jpg',
        '/static/images/product/1-1.jpg', 0, 1),
       (2, 'http://localhost:8080/images/product/1-2.jpg', '1-2.jpg',
        '/static/images/product/1-2.jpg', 1, 1)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (3, 'http://localhost:8080/images/product/2-1.jpg', '2-1.jpg',
        '/static/images/product/2-1.jpg', 0, 2),
       (4, 'http://localhost:8080/images/product/2-2.jpg', '2-2.jpg',
        '/static/images/product/2-2.jpg', 1, 2)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (5, 'http://localhost:8080/images/product/3-1.jpg', '3-1.jpg',
        '/static/images/product/3-1.jpg', 0, 3),
       (6, 'http://localhost:8080/images/product/3-2.jpg', '3-2.jpg',
        '/static/images/product/3-2.jpg', 1, 3)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (7, 'http://localhost:8080/images/product/4-1.jpg', '4-1.jpg',
        '/static/images/product/4-1.jpg', 0, 4);

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (8, 'http://localhost:8080/images/product/5-1.jpg', '5-1.jpg',
        '/static/images/product/5-1.jpg', 0, 5);

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (9, 'http://localhost:8080/images/product/1-1.jpg', '1-1.jpg',
        '/static/images/product/1-1.jpg', 0, 6),
       (10, 'http://localhost:8080/images/product/1-2.jpg', '1-2.jpg',
        '/static/images/product/1-2.jpg', 1, 6)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (11, 'http://localhost:8080/images/product/2-1.jpg', '2-1.jpg',
        '/static/images/product2-1.jpg', 0, 7),
       (12, 'http://localhost:8080/images/product/2-2.jpg', '2-2.jpg',
        '/static/images/product/2-2.jpg', 1, 7)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (13, 'http://localhost:8080/images/product/3-1.jpg', '3-1.jpg',
        '/static/images/product/3-1.jpg', 0, 8),
       (14, 'http://localhost:8080/images/product/3-2.jpg', '3-2.jpg',
        '/static/images/product/3-2.jpg', 1, 8)
;

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (15, 'http://localhost:8080/images/product/4-1.jpg', '4-1.jpg',
        '/static/images/product/4-1.jpg', 0, 9);

insert into PUBLIC.PRODUCT_IMAGE (image_id, url, filename, path, no, product_id)
values (16, 'http://localhost:8080/images/product/5-1.jpg', '5-1.jpg',
        '/static/images/product/5-1.jpg', 0, 10);

-- PRODUCT_CATEGORY
insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (1, 1, 1),
       (2, 11, 1),
       (3, 111, 1);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (4, 1, 2),
       (5, 11, 2),
       (6, 111, 2);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (7, 1, 3),
       (8, 11, 3),
       (9, 111, 3);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (10, 1, 4),
       (11, 11, 4),
       (12, 111, 4);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (13, 1, 5),
       (14, 11, 5),
       (15, 111, 5);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (16, 1, 6),
       (17, 11, 6),
       (18, 112, 6);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (19, 1, 7),
       (20, 11, 7),
       (21, 112, 7);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (22, 1, 8),
       (23, 11, 8),
       (24, 112, 8);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (25, 1, 9),
       (26, 11, 9),
       (27, 112, 9);

insert into PRODUCT_CATEGORY (product_category_id, category_id, product_id)
values (28, 1, 10),
       (29, 11, 10),
       (30, 112, 10);