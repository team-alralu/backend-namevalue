insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, create_date) values (1, 'valid@naver.com', '홍길동', 'NAVER', 0, current_timestamp());

insert into tbl_person_name(person_name, create_date) values ('홍길동',current_timestamp());

insert into tbl_review (review_id, person_name, content, create_date) values (1L, '홍길동','리뷰입니다. 리뷰입니다.', current_timestamp());
insert into tbl_review (review_id, person_name, content, create_date) values (2L, '홍길동','리뷰입니다2. 리뷰입니다2.', current_timestamp());

insert into tbl_like(like_id, review_id, user_id, create_date) values (1L, 1L, 1L, current_timestamp());
insert into tbl_like(like_id, review_id, user_id, create_date) values (1L, 2L, 1L, current_timestamp());
