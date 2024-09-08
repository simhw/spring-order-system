-- depth1
insert into CATEGORY (category_id, name, depth, parent_id)
values (1, '의류', 1, null),
       (2, '가방', 1, null),
       (3, '신발', 1, null),
       (4, '리빙', 1, null),
       (5, '뷰티', 1, null);

-- depth2
insert into CATEGORY (category_id, name, depth, parent_id)
values (11, '상의', 2, 1),
       (12, '바지', 2, 1),
       (13, '원피스', 2, 1),
       (14, '아우터', 2, 1),
       (15, '이너웨어', 2, 1)
;

-- depth2
insert into CATEGORY (category_id, name, depth, parent_id)
values (21, '숄더백', 2, 1),
       (22, '크로스백', 2, 2),
       (23, '토트백', 2, 2),
       (24, '에코백', 2, 2),
       (25, '벡팩', 2, 2)
;

-- depth2
insert into CATEGORY (category_id, name, depth, parent_id)
values (31, '플랫', 2, 3),
       (32, '로퍼', 2, 3),
       (33, '부츠', 2, 3),
       (34, '샌들', 2, 3),
       (35, '펌프스', 2, 3)
;

-- depth3
insert into CATEGORY (category_id, name, depth, parent_id)
values (111, '반팔', 3, 11),
       (112, '긴팔', 3, 11),
       (113, '슬리브리스', 3, 11),
       (114, '셔츠', 3, 11),
       (115, '블라우스', 3, 11)
;
