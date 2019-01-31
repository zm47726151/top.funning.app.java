create table User(
    id int primary key auto_increament,
    openId varchar(32)
)

create table Good(
    id int primary key auto_increament,
    name varchar(32),
    descrption varchar(128),
    price int
)

create table GoodDetail(
    id int primary key auto_increament,
    imageUrl varchar(256),
)

/**

state = {"准备中","","",""}
**/
create table order(
    id int primary key auto_increament,
    
)
