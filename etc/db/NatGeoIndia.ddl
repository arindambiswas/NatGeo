DROP TABLE IF EXISTS ma_participant;
DROP TABLE IF EXISTS fb_user;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS enum_gender;
DROP TABLE IF EXISTS enum_member_status;

/**********************************/
/* Table Name: enum_member_status */
/**********************************/
CREATE TABLE enum_member_status(
  id SERIAL,
  status VARCHAR(255) NOT NULL,
  description TEXT
);

/**********************************/
/* Table Name: enum_gender */
/**********************************/
CREATE TABLE enum_gender(
  id SERIAL NOT NULL,
  gender VARCHAR(20) NOT NULL,
  description TEXT
);

/**********************************/
/* Table Name: member */
/**********************************/
CREATE TABLE member(
  id SERIAL,
  guid VARCHAR(255),
  firstname VARCHAR(255),
  lastname VARCHAR(255),
  dob DATE,
  email VARCHAR(255),
  mobile VARCHAR(255),
  gender SMALLINT,
  photo_url VARCHAR(500),
  private_key VARCHAR(255),
  create_date TIMESTAMP DEFAULT now(),
  member_status SMALLINT
);

/**********************************/
/* Table Name: fb_user */
/**********************************/
CREATE TABLE fb_user(
  id SERIAL,
  member BIGINT,
  photo_url VARCHAR(500),
  create_date TIMESTAMP DEFAULT now(),
  access_token VARCHAR(500),
  is_valid_access_token BOOLEAN,
  facebook_id VARCHAR(255)
);

/**********************************/
/* Table Name: ma_participant */
/**********************************/
CREATE TABLE ma_participant(
  id SERIAL,
  member BIGINT,
  height VARCHAR(10),
  weight VARCHAR(10),
  age VARCHAR(10)
);


ALTER TABLE enum_member_status ADD PRIMARY KEY (id);

ALTER TABLE enum_gender ADD PRIMARY KEY (id);

ALTER TABLE member ADD PRIMARY KEY (id);
ALTER TABLE member ADD CONSTRAINT FK_member_0 FOREIGN KEY (member_status) REFERENCES enum_member_status (id);
ALTER TABLE member ADD CONSTRAINT FK_member_1 FOREIGN KEY (gender) REFERENCES enum_gender (id);

ALTER TABLE fb_user ADD PRIMARY KEY (id);
ALTER TABLE fb_user ADD CONSTRAINT FK_fb_user_0 FOREIGN KEY (member) REFERENCES member (id);

ALTER TABLE ma_participant ADD PRIMARY KEY (id);
ALTER TABLE ma_participant ADD CONSTRAINT FK_ma_participant_0 FOREIGN KEY (member) REFERENCES member (id);

