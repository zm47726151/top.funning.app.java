create table User(
    id int primary key auto_increament,
    openId varchar(32)
)

/**
state = {"show","hide"}
**/
create table GoodType(
    id int primary key auto_increament,
    name varchar(32),
    state int/**foreign key GoodType(id)**/
)

create table Good(
    id int primary key auto_increament,
    name varchar(32),
    descrption varchar(128),
    price int,
    type int/** foreign key GoodType(id) **/
)

create table GoodDetail(
    id int primary key auto_increament,
    imageUrl varchar(256),
)

/**
state = {"待付款","准备中","已完成","退款中","已取消"}
**/
create table order(
    id int primary key auto_increament
)
