CREATE TABLE users(
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
	email VARCHAR( 128 ) NOT NULL DEFAULT ( '' ),
	name VARCHAR( 255 ),
	phone_number VARCHAR( 255 ),
	remote_id INTEGER not null,
	position varchar( 255 ),
	current_identity boolean
);
