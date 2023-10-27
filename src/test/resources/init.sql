insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, required_info_reg_flag, create_date) values (1L, 'gildong@naver.com', '홍길동', 'NAVER', 0, true, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, required_info_reg_flag, create_date) values (2L, 'test1@naver.com', '테스터1', 'NAVER', 0, true, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, required_info_reg_flag, create_date) values (3L, 'test2@naver.com', '테스터2', 'NAVER', 0, true, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, required_info_reg_flag, create_date) values (4L, 'test3@naver.com', '테스터3', 'NAVER', 0, true, current_timestamp());
insert into tbl_user(user_id, email, name, oauth_type, name_value_add_cnt, required_info_reg_flag, create_date) values (5L, 'test4@naver.com', '테스터4', 'NAVER', 0, true, current_timestamp());

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

insert into tbl_value(value_id, name, min, max) values (1L, '돌멩이',0, 5);
insert into tbl_value(value_id, name, min, max) values (2L, '과자부스러기',6,8);
insert into tbl_value(value_id, name, min, max) values (3L, '좁쌀한톨',9,10);
insert into tbl_value(value_id, name, min, max) values (4L, '찌그러진캔',11,12);
insert into tbl_value(value_id, name, min, max) values (5L, '폐건전지',13,14);
insert into tbl_value(value_id, name, min, max) values (6L, '양말 한켤레',15,16);
insert into tbl_value(value_id, name, min, max) values (7L, '메추리알',17,18);
insert into tbl_value(value_id, name, min, max) values (8L, '밥버거',19,20);
insert into tbl_value(value_id, name, min, max) values (9L, '치킨 한마리',21,23);
insert into tbl_value(value_id, name, min, max) values (10L, '스팸 선물세트',24,25);
insert into tbl_value(value_id, name, min, max) values (12L, '타이어 한짝',26,28);
insert into tbl_value(value_id, name, min, max) values (13L, '1++ 한우세트',29,30);
insert into tbl_value(value_id, name, min, max) values (14L, '명품지갑',31,35);
insert into tbl_value(value_id, name, min, max) values (15L, '금목걸이',36,40);
insert into tbl_value(value_id, name, min, max) values (16L, '스마트폰',41,45);
insert into tbl_value(value_id, name, min, max) values (17L, '명품백',46,50);
insert into tbl_value(value_id, name, min, max) values (18L, '안마의자',51,55);
insert into tbl_value(value_id, name, min, max) values (19L, '국산차',56,60);
insert into tbl_value(value_id, name, min, max) values (20L, '명품 시계',61,66);
insert into tbl_value(value_id, name, min, max) values (21L, '외제차',67,74);
insert into tbl_value(value_id, name, min, max) values (22L, '반짝이는 다이아몬드',75,78);
insert into tbl_value(value_id, name, min, max) values (23L, '백지수표',79,80);
insert into tbl_value(value_id, name, min, max) values (24L, '국가보물 1호',81,85);
insert into tbl_value(value_id, name, min, max) values (25L, '밤하늘의 빛나는 별',86,90);
insert into tbl_value(value_id, name, min, max) values (26L, '사막의 오아시스',91,95);
insert into tbl_value(value_id, name, min, max) values (27L, '금은보화',96,100);

insert into tbl_likeability(likeability_id, person_name, point) values (1L, '홍길동', 20);
insert into tbl_likeability(likeability_id, person_name, point) values (2L, '홍길동', 89);
insert into tbl_likeability(likeability_id, person_name, point) values (3L, '홍길동', 80);
insert into tbl_likeability(likeability_id, person_name, point) values (4L, '홍길동', 89);
insert into tbl_likeability(likeability_id, person_name, point) values (5L, '홍길동', 89);
insert into tbl_likeability(likeability_id, person_name, point) values (6L, '홍길동', 89);
insert into tbl_likeability(likeability_id, person_name, point) values (7L, '홍길동', 89);

insert into tbl_personality(personality_id, name) values (1L, '성격1');
insert into tbl_personality(personality_id, name) values (2L, '성격2');
insert into tbl_personality(personality_id, name) values (3L, '성격3');

insert into tbl_person_name_personality(name_personality_id, person_name, personality_id) values (1L, '홍길동', 1L);
insert into tbl_person_name_personality(name_personality_id, person_name, personality_id) values (2L, '홍길동', 2L);
insert into tbl_person_name_personality(name_personality_id, person_name, personality_id) values (3L, '홍길동', 2L);
insert into tbl_person_name_personality(name_personality_id, person_name, personality_id) values (4L, '홍길동', 2L);