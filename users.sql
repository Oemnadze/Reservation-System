use Reservation;
drop table if exists users;
drop table if exists blacklist;
create table users (
	username varchar(60),
    password varchar(60),
    mail varchar(60),
    avatar varchar(60),
    cancelledOrders int
);

create table blacklist(
	username varchar(60)
);