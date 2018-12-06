create table if not exists employee
(
	id LONG not null,
	name varchar(255) not null,
	role  varchar(255) not null,
	department varchar(255) not null,
	primary key(id)
);