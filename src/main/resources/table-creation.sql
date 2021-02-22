--drop table accounts_owners;
drop table accounts;
drop table app_users;
drop table account_types;

-- currently only allows for one user per account, if I want there
-- to be more, will need to implement another table
create table accounts (
	account_id		serial,
	type_id			int not null,
	account_value	numeric(10,2) not null,
	user_id			int not null,
	account name	varchar not null,

	constraint user_account_pk
	primary key (account_id),

	constraint account_type_fk
	foreign key (type_id)
	references account_types,

	constraint app_users_fk
	foreign key(user_id)
	references app_users(user_id)
);

create table account_types (
	type_id		serial,
	type_name	varchar unique not null,

	constraint account_types_pk
	primary key (type_id)
);

insert into user_roles (role_name)
values ('SAVINGS'), ('CHECKING'), ('JOINT');

create table app_users (
	user_id		serial,
	username	varchar unique not null,
	password	varchar not null,
	first_name	varchar not null,
	last_name	varchar not null,

	constraint user_id_pk
	primary key (user_id)
);