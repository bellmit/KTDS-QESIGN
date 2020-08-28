INSERT INTO example_company
	(cmpn_id, created_dt, updated_dt, cmpn_name, diplay_order)
VALUES
	('C123456789', now() , now(), 'KTDS', '1');

INSERT INTO example_user
	(user_id, email, emp_name, emp_no, phone, cmpn_id, dept_id,  created_dt, updated_dt)
VALUES
	('U123456789', 'trump-us@gmail.com', '도람푸', 'E123456789', '010-9999-8888', 'C123456789', 'D123456789', now(), now());