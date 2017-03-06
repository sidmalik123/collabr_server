CREATE TABLE users
(
	id int not null AUTO_INCREMENT,
	fname varchar(255) not null, 
	lname varchar(255) not null,
	email varchar(255) not null unique,
	hash_password int not null,
	created_at datetime not null,
	last_login datetime,
	validated boolean default false,
	primary key (id)
)

CREATE TABLE skills
(
	id int not null AUTO_INCREMENT,
	name varchar(30) not null unique, 
	primary key (id) -- TODO add name to primary key too
)

CREATE TABLE projects
(
	id int not null AUTO_INCREMENT,
	name varchar(255) not null,
	description varchar(255) not null,
	people_required int not null,
	owner_id int not null,
	primary key (owner_id, name),
	foreign key (owner_id) references users(id),
	check (people_required>0),
	unique (id)
)

CREATE TABLE user_skill_junction
(
	user_id int not null,
	skill_id int not null,
	primary key (user_id, skill_id),
	foreign key (user_id) references users(id),
	foreign key (skill_id) references skills(id) 
)

CREATE TABLE project_skill_junction
(
	project_id int not null,
	skill_id int not null,
	primary key (project_id, skill_id),
	foreign key (project_id) references projects(id),
	foreign key (skill_id) references skills(id) 
)

CREATE TABLE collabr_matches
(
	id int not null AUTO_INCREMENT unique,
	user_id int not null,
	project_id int not null,
	mutual boolean default false,
	primary key (user_id, project_id),
	foreign key (user_id) references users(id),
	foreign key (project_id) references projects(id)
)




