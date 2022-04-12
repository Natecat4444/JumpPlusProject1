use bank;

create table Customer(
	id int primary key auto_increment,
    username varchar(50) unique not null,
    password varchar(50) not null,
    name varchar(50) not null,
    number varchar(50) not null,
    address varchar(50) not null
);

Create table Account(
	balance double not null,
    acc_id int primary key auto_increment,
    user_id int not null,
    foreign key (user_id) references Customer(id)
);

Create Table Transaction(
	id int primary key auto_increment,
    user_id int not null,
    acc_id int not null,
    type varchar(10) not null,
    ammount double not null,
    foreign key (user_id) references Customer(id),
    foreign key (acc_id) references Account(acc_id)
)