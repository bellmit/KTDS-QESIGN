CREATE TABLE tb_code
  (
     code_id       VARCHAR(20) NOT NULL,
	 group_code_id VARCHAR(20),
     code_desc     VARCHAR(50) NOT NULL,
     display_order VARCHAR(10),
	 created_dt    TIMESTAMP,
     updated_dt    TIMESTAMP,
     PRIMARY KEY (code_id)
  );

CREATE TABLE tb_company
  (
     company_id    VARCHAR(75) NOT NULL,
     company_name  VARCHAR(75) NOT NULL,
     display_order VARCHAR(10),
     created_dt    TIMESTAMP,
     updated_dt    TIMESTAMP,
     PRIMARY KEY (company_id)
  );

CREATE TABLE tb_form
  (
     form_id         BIGSERIAL NOT NULL,
     contents_type   VARCHAR(20) DEFAULT 'HTML' NOT NULL,
     delete_yn       BOOLEAN DEFAULT FALSE NOT NULL,
     form_content    TEXT,
     form_name       VARCHAR(100) NOT NULL,
     form_no         VARCHAR(4) NOT NULL,
     form_type       VARCHAR(20) DEFAULT 'PERSONAL' NOT NULL,
     use_yn          BOOLEAN DEFAULT FALSE NOT NULL,
     video_file_name VARCHAR(255),
     video_file_path VARCHAR(255),
     company_id      VARCHAR(75),
     user_id         VARCHAR(75),
	 created_dt      TIMESTAMP,
     updated_dt      TIMESTAMP,
     PRIMARY KEY (form_id)
  );

CREATE TABLE tb_form_user
  (
     form_user_id BIGSERIAL NOT NULL,
     form_id      INT8,
     company_id   VARCHAR(75),
     user_id      VARCHAR(75),
     created_dt   TIMESTAMP,
     updated_dt   TIMESTAMP,
     PRIMARY KEY (form_user_id)
  );

CREATE TABLE tb_group_code
  (
     group_code_id VARCHAR(20) NOT NULL,
     group_desc    VARCHAR(50) NOT NULL,
	 created_dt    TIMESTAMP,
     updated_dt    TIMESTAMP,
     PRIMARY KEY (group_code_id)
  );

CREATE TABLE tb_pledge
  (
     pledge_id        BIGSERIAL NOT NULL,
	 pledge_name      VARCHAR(60) NOT NULL,
     pledge_contents  TEXT NOT NULL,
     pledge_no        VARCHAR(4) NOT NULL,
     progs_sttus      VARCHAR(20) DEFAULT 'STANDBY' NOT NULL,
     end_dt           TIMESTAMP NOT NULL,
	 start_dt         TIMESTAMP NOT NULL,
     video_play_sec   INTEGER DEFAULT 0 NOT NULL,
	 delete_yn        BOOLEAN DEFAULT FALSE NOT NULL,
     form_id          INT8,
     company_id       VARCHAR(75),
     user_id          VARCHAR(75),
	 created_dt       TIMESTAMP,
     updated_dt       TIMESTAMP,
     PRIMARY KEY (pledge_id)
  );

CREATE TABLE tb_pledge_item
  (
     pledge_item_id BIGSERIAL NOT NULL,
     display_order  VARCHAR(10) NOT NULL,
     item_content   TEXT NOT NULL,
     item_name      VARCHAR(50) NOT NULL,
     pledge_id      INT8,
     company_id     VARCHAR(75),
     user_id        VARCHAR(75),
	 created_dt       TIMESTAMP,
     updated_dt       TIMESTAMP,
     PRIMARY KEY (pledge_item_id)
  );

CREATE TABLE tb_pledge_user
  (
     pledge_user_id   BIGSERIAL NOT NULL,
     progs_sttus_type VARCHAR(20) DEFAULT 'STANDBY' NOT NULL,
     video_play_yn    BOOLEAN DEFAULT FALSE NOT NULL,
	 pledge_dt        TIMESTAMP,
     pledge_id        INT8,
     company_id       VARCHAR(75),
     user_id          VARCHAR(75),
	 created_dt       TIMESTAMP,
     updated_dt       TIMESTAMP,
     PRIMARY KEY (pledge_user_id)
  );

CREATE TABLE tb_role
  (
     role_id    BIGSERIAL NOT NULL,
     role_type  VARCHAR(20),
	 created_dt TIMESTAMP,
     updated_dt TIMESTAMP,
     PRIMARY KEY (role_id)
  );

CREATE TABLE tb_user
  (
     company_id     VARCHAR(75) NOT NULL,
     user_id        VARCHAR(75) NOT NULL,
	 username       VARCHAR(75) NOT NULL,
	 email          VARCHAR(75) NOT NULL,
     emp_no         VARCHAR(75),
     dept_id        VARCHAR(75),
     dept_name      VARCHAR(75),
     phone          VARCHAR(75),
     sign_file_name VARCHAR(255),
     sign_file_path VARCHAR(255),
     user_type      VARCHAR(20) DEFAULT 'STAFF' NOT NULL,
     delete_yn      BOOLEAN DEFAULT FALSE NOT NULL,
	 created_dt     TIMESTAMP,
     updated_dt     TIMESTAMP,
     PRIMARY KEY (company_id, user_id)
  );

CREATE TABLE tb_user_noti
  (
     user_noti_id     BIGSERIAL NOT NULL,
     noti_direct_type VARCHAR(20) NOT NULL,
     noti_type        VARCHAR(20) NOT NULL,
     company_id       VARCHAR(75),
     user_id          VARCHAR(75),
	 created_dt       TIMESTAMP,
     updated_dt       TIMESTAMP,
     PRIMARY KEY (user_noti_id)
  );

CREATE TABLE tb_user_role
  (
     company_id VARCHAR(75) NOT NULL,
     user_id    VARCHAR(75) NOT NULL,
     role_id    INT8 NOT NULL,
     PRIMARY KEY (company_id, user_id, role_id)
  );

ALTER TABLE tb_form
  ADD CONSTRAINT uk_tb_form_unique UNIQUE (form_no);

ALTER TABLE tb_user
  ADD CONSTRAINT uk_tb_user_unique UNIQUE (emp_no, email);

ALTER TABLE tb_code
  ADD CONSTRAINT fk_tb_code_group_code_id FOREIGN KEY (group_code_id) REFERENCES
  tb_group_code;

ALTER TABLE tb_form
  ADD CONSTRAINT fk_tb_form_user_pk FOREIGN KEY (company_id, user_id) REFERENCES
  tb_user;

ALTER TABLE tb_form_user
  ADD CONSTRAINT fk_tb_form_user_form_id FOREIGN KEY (form_id) REFERENCES
  tb_form;

ALTER TABLE tb_form_user
  ADD CONSTRAINT fk_tb_form_user_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;

ALTER TABLE tb_pledge
  ADD CONSTRAINT fk_tb_pledge_form_id FOREIGN KEY (form_id) REFERENCES tb_form;

ALTER TABLE tb_pledge
  ADD CONSTRAINT fk_tb_pledge_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;

ALTER TABLE tb_pledge_item
  ADD CONSTRAINT fk_tb_pledge_item_pledge_id FOREIGN KEY (pledge_id) REFERENCES
  tb_pledge;

ALTER TABLE tb_pledge_item
  ADD CONSTRAINT fk_tb_pledge_user_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;

ALTER TABLE tb_pledge_user
  ADD CONSTRAINT fk_tb_pledge_user_pledge_id FOREIGN KEY (pledge_id) REFERENCES
  tb_pledge;

ALTER TABLE tb_pledge_user
  ADD CONSTRAINT fk_tb_pledge_user_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;

ALTER TABLE tb_user
  ADD CONSTRAINT fk_tb_use_company_id FOREIGN KEY (company_id) REFERENCES
  tb_company;

ALTER TABLE tb_user_noti
  ADD CONSTRAINT fk_tb_user_noti_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;

ALTER TABLE tb_user_role
  ADD CONSTRAINT fk_tb_user_role_role_id FOREIGN KEY (role_id) REFERENCES
  tb_role;

ALTER TABLE tb_user_role
  ADD CONSTRAINT fk_tb_user_role_user_pk FOREIGN KEY (company_id, user_id)
  REFERENCES tb_user;