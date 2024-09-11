insert into COUPON (coupon_id, title, coupon_code, description, discount, discount_type, max_discount_amount,
                    min_payment_amount, start_at, end_at, created_at, updated_at)
values (1, '회원 10%', null, '회원 10% 할인', 10, 'FIXED_RATE', 50000, 0, '2024-09-01', '2024-09-30 23:59:59', '2024-09-01',
        '2024-09-01');

insert into COUPON (coupon_id, title, coupon_code, description, discount, discount_type, max_discount_amount,
                    min_payment_amount, start_at, end_at, created_at, updated_at)
values (2, '가을 맞이 14%', null, '가을 맞이 14% 할인', 14, 'FIXED_RATE', 50000, 30000, '2024-08-20', '2024-09-30 23:59:59',
        '2024-08-20', '2024-08-20');

insert into COUPON (coupon_id, title, coupon_code, description, discount, discount_type, max_discount_amount,
                    min_payment_amount, start_at, end_at, created_at, updated_at)
values (3, '추석 18%', null, '추석 18% 할인', 18, 'FIXED_RATE', 30000, 50000, '2024-09-03 00:00:00', '2024-09-15 23:59:59',
        '2024-09-03', '2024-09-03');

insert into COUPON (coupon_id, title, coupon_code, description, discount, discount_type, max_discount_amount,
                    min_payment_amount, start_at, end_at, created_at, updated_at)
values (4, '추석 10,000원 쿠폰', null, '추석 10,000원 할인', 10000, 'FIXED_AMOUNT', 10000, 50000, '2024-09-03 00:00:00', '2024-09-15 23:59:59',
        '2024-09-03', '2024-09-03');