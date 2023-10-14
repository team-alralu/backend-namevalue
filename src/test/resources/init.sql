insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (1L, 'gildong@naver.com', '홍길동', 'NAVER', 0, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (2L, 'test1@naver.com', '테스터1', 'NAVER', 0, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (3L, 'test2@naver.com', '테스터2', 'NAVER', 0, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (4L, 'test3@naver.com', '테스터3', 'NAVER', 0, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (5L, 'test4@naver.com', '테스터4', 'NAVER', 0, current_timestamp());

insert into tbl_person_name(person_name, create_date) values ('홍길동',current_timestamp());

insert into tbl_review (review_id, person_name, content, create_date) values (1L, '홍길동','1번째 리뷰입니다.', '2023-01-09 09:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (2L, '홍길동','2번째 리뷰입니다.', '2023-01-09 10:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (3L, '홍길동','3번째 리뷰입니다.', '2023-01-09 11:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (4L, '홍길동','4번째 리뷰입니다.', '2023-01-09 12:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (5L, '홍길동','5번째 리뷰입니다.', '2023-01-09 13:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (6L, '홍길동','6번째 리뷰입니다.', '2023-01-09 14:00:00');
insert into tbl_review (review_id, person_name, content, create_date) values (7L, '홍길동','7번째 리뷰입니다.', '2023-01-09 15:00:00');

insert into tbl_like(like_id, review_id, user_id, create_date) values (1L, 1L, 1L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (2L, 2L, 1L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (3L, 2L, 2L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (4L, 2L, 3L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (5L, 2L, 4L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (6L, 2L, 1L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (7L, 5L, 1L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (8L, 6L, 1L, current_timestamp());
