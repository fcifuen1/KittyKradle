CREATE TABLE User_Info
	(phone_num			varchar(15),
	 username			varchar(20) NOT NULL,
	 password			varchar(20) NOT NULL,
	 email				varchar(40),
	 first_name			varchar(30),
	 last_name			varchar(30),
	 background_check	tinyint,
	 pet_insurance 		tinyint,
	 picture_url		varchar(200),
	 PRIMARY KEY (username)
	);

CREATE TABLE User_Address
	(username 			varchar(15) NOT NULL,
	 address 			varchar(70),
	 city				varchar(30),
	 state				varchar(30),
	 country			varchar(30),
	 zipcode			int,
	 FOREIGN KEY (username) REFERENCES User_Info (username)
	 	ON DELETE CASCADE
	);

CREATE TABLE Cat_Info
	(cat_id				int NOT NULL AUTO_INCREMENT,
	 name 				varchar(30),
	 zipcode			int,
	 likes 				int,
	 bio				varchar(200),
	 picture_url		varchar(200),
	 PRIMARY KEY (cat_id)
	);

CREATE TABLE Cat_Detail
	(cat_id				int NOT NULL,
	 breed				varchar(30),
	 sex				varchar(20),
	 age				int,
	 size				varchar(20),
	 FOREIGN KEY (cat_id) REFERENCES Cat_Info (cat_id)
	 	ON DELETE CASCADE
	);

CREATE TABLE User_Likes_Cat
	(username			varchar(15) NOT NULL,
	 cat_id				int NOT NULL,
	 FOREIGN KEY (username) REFERENCES User_Info (username)
	 	ON DELETE CASCADE,
	 FOREIGN KEY (cat_id) REFERENCES Cat_Info (cat_id)
	 	ON DELETE CASCADE
	);

CREATE TABLE User_Request
	(username			varchar(15) NOT NULL,
	 cat_id				int NOT NULL,
	 FOREIGN KEY (username) REFERENCES User_Info (username)
	 	ON DELETE CASCADE,
	 FOREIGN KEY (cat_id) REFERENCES Cat_Info (cat_id)
	 	ON DELETE CASCADE
	);
