DROP TABLE ers_reimbursement;
DROP TABLE ers_users;
DROP TABLE ers_reimbursement_status; 
DROP TABLE ers_reimbursement_type;
DROP TABLE ers_user_roles; 

CREATE TABLE ers_reimbursement_status (
	reimb_status_id serial PRIMARY KEY,
	reimb_status varchar(10) NOT null
);

CREATE TABLE ers_reimbursement_type(
	reimb_type_id serial PRIMARY KEY,
	reimb_type varchar(10) NOT null
);

CREATE TABLE ers_user_roles(
	ers_user_role_id serial PRIMARY KEY,
	user_role varchar(10) NOT NULL
);

CREATE TABLE ers_users(
	ers_users_id serial PRIMARY KEY,
	ers_username varchar(50) NOT NULL,
	ers_password varchar(50) NOT NULL,
	user_first_name varchar(100) NOT NULL,
	user_last_name varchar(100) NOT NULL,
	user_email varchar(100) NOT NULL,
	user_role_id int REFERENCES ers_user_roles(ers_user_role_id),
	CONSTRAINT user_info UNIQUE(ers_username,user_email)
);

CREATE TABLE ers_reimbursement(
	reimb_id serial PRIMARY KEY,
	reimb_amount decimal(18,2),
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author int REFERENCES ers_users(ers_users_id) NOT NULL,
	reimb_resolver int REFERENCES ers_users(ers_users_id),
	reimb_status_id int REFERENCES ers_reimbursement_status(reimb_status_id) NOT NULL,
	reimb_type_id int REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL
);

INSERT INTO ers_reimbursement_type(reimb_type)
VALUES
	('LODING'),
	('TRAVEL'),
	('FOOD'),
	('OTHER'
);

INSERT INTO ers_reimbursement_status(reimb_status)
VALUES
	('PENDING'),
	('APPROVED'),
	('DENIED'
);

INSERT INTO ers_user_roles(user_role)
VALUES
	('Manager'),
	('Employee'
);

INSERT INTO ers_users
(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
VALUES('employee11', 'password', 'emp', 'test', 'emptest@revature.com', 2),
('manager11', 'password', 'manager', 'test', 'mgrtest@revature.com', 1);

INSERT INTO emp_reimbursement_sys.ers_reimbursement
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id)
VALUES(1500.25, '7/21/2022', 'Relocation Expenses for Cognizant employment', 3, 1, 2);

select * from ers_users where ers_users_id =1;