CREATE TABLE clct_master_info (
  clct_id				INTEGER 	NOT NULL AUTO_INCREMENT,
  clct_nm				VARCHAR(50) NOT NULL,
  clct_period_cd		VARCHAR(50) NOT NULL,
  clct_period_month		VARCHAR(50) NOT NULL,
  clct_period_time		VARCHAR(50) NOT NULL,
  storage_period_year	VARCHAR(50) NOT NULL,
  storage_period_month	VARCHAR(50) NOT NULL,
  creator_id			VARCHAR(50) NOT NULL,
  creat_datetime		VARCHAR(50) NOT NULL,
  modifier_id			VARCHAR(50),
  modify_datetime		VARCHAR(50),
  PRIMARY KEY(clct_id)
);

create table clct_file_setup_info
(
    file_id		   INTEGER     not null AUTO_INCREMENT,
    file_path      VARCHAR(50) not null,
    file_delimiter VARCHAR(50) not null,
    file_ext       VARCHAR(50) not null,
    method_nm      VARCHAR(50) not null,
    method_cd      VARCHAR(50) not null,
    file_encode    VARCHAR(50) not null,
    creator_id     VARCHAR(50) not null,
    creat_datetime VARCHAR(50) not null,
    modifier_id		VARCHAR(50),
    modify_datetime	VARCHAR(50),
    PRIMARY KEY(file_id)
);

CREATE TABLE clct_file_choice_info (
   file_uniq_id			INTEGER		NOT NULL    AUTO_INCREMENT,
   file_id				INTEGER		NOT NULL,
   col_nm				VARCHAR(50) NOT NULL,
   creator_id			VARCHAR(50) NOT NULL,
   creat_datetime		VARCHAR(50) NOT NULL,
   modifier_id			VARCHAR(50),
   modify_datetime		VARCHAR(50),
   PRIMARY KEY(file_uniq_id),
   FOREIGN KEY(file_id)  REFERENCES  clct_file_setup_info(file_id)
);

INSERT INTO clct_file_setup_info
( file_path, file_delimiter, file_ext, method_nm, method_cd, file_encode, creator_id, creat_datetime	)
VALUES
( 'C:\Users\user\test', 'A070001', 'A060001', '파일데이터수집00', 'A010001', 'A050001', 'tester00', '2022-05-05');

CREATE TABLE country (
 id   INTEGER      NOT NULL AUTO_INCREMENT,
 name VARCHAR(128) NOT NULL,
 age  VARCHAR(2)   NOT NULL,
 PRIMARY KEY (id)
);