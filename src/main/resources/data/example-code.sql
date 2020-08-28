INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('ROLE_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '권한 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('USER_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '사용자 구분 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('NOTI_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '알림 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('NOTI_DIRECT_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '알림 방향 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('FORM_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '양식 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('CONTENTS_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '컨텐츠 유형');
INSERT INTO tb_group_code(group_code_id, created_dt, updated_dt, group_desc) VALUES ('PROGS_STTUS_TYPE', '2020-08-28 16:26:47.875722', '2020-08-28 16:26:47.875722', '진행 상태 유형');


INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('ROLE_USER', 'ROLE_TYPE', '일반 사용자', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('ROLE_FORM', 'ROLE_TYPE', '양식 담당자', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('ROLE_PLEDGE', 'ROLE_TYPE', '서약 업무 담당자', '3', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('ROLE_SYSTEM', 'ROLE_TYPE', '시스템 관리자', '4', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('EMAIL', 'NOTI_TYPE', 'Email', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('PUSH', 'NOTI_TYPE', 'APP Push', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('SMS', 'NOTI_TYPE', 'SMS 수신', '3', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('SEND', 'NOTI_DIRECT_TYPE', '송신 메시지 설정', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('RECEIVE', 'NOTI_DIRECT_TYPE', '수신 메시지 설정', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('STAFF', 'USER_TYPE', '임직원', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('PARTNER', 'USER_TYPE', '협력사', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('FIXED', 'FORM_TYPE', '고정 양식', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('COMMON', 'FORM_TYPE', '공통 양식', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('PERSONAL', 'FORM_TYPE', '개인 양식', '3', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('HTML', 'CONTENTS_TYPE', '일반', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('VIDEO', 'CONTENTS_TYPE', '동영상', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('STANDBY', 'PROGS_STTUS_TYPE', '대기', '1', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('ONGOING', 'PROGS_STTUS_TYPE', '진행 중', '2', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('COMPLETE', 'PROGS_STTUS_TYPE', '완료', '3', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');
INSERT INTO tb_code(code_id, group_code_id, code_desc, display_order, created_dt, updated_dt) VALUES ('CANCEL', 'PROGS_STTUS_TYPE', '회수', '4', '2020-08-28 16:25:35.22007', '2020-08-28 16:25:35.22007');




