/*
TRUNCATE TABLE enum_gender CASCADE;
TRUNCATE TABLE enum_member_status CASCADE;
*/

-- **************************************************
-- START : enum_member_status
-- **************************************************

INSERT INTO enum_member_status 
	(id, status, description)
VALUES
	(1, 'ACTIVE', '');

INSERT INTO enum_member_status 
	(id, status, description)
VALUES
	(2, 'INACTIVE', '');
	
-- **************************************************
-- END : enum_member_status
-- **************************************************
	
-- **************************************************
-- START : enum_gender
-- **************************************************

INSERT INTO enum_gender 
	(id, gender, description)
VALUES
	(1, 'MALE', '');

INSERT INTO enum_gender 
	(id, gender, description)
VALUES
	(2, 'FEMALE', '');
	
-- **************************************************
-- END : enum_gender
-- **************************************************

-- **************************************************
-- START : member
-- **************************************************

INSERT INTO member 
	(id, guid, firstname, lastname)
VALUES
	(-1, '123-456-789', 'Arindam', 'Biswas');

-- **************************************************
-- END : member
-- **************************************************

-- **************************************************
-- START : fb_user
-- **************************************************

INSERT INTO fb_user 
	(id, member, photo_url, access_token, facebook_id)
VALUES
	(-1, -1, 'photo_url', 'acess_token', '123456789');

-- **************************************************
-- END : fb_user
-- **************************************************

-- **************************************************
-- START : ma_participant
-- **************************************************

INSERT INTO ma_participant 
	(id, member, height, weight, age)
VALUES
	(-1, -1, '180', '85', '25');

-- **************************************************
-- END : ma_participant
-- **************************************************	