create table User(
    id int primary key auto_increament,
    openId varchar(32)
)

/**
state = {"show" = 1,"hide" = 2}
**/
create table GoodType(
    id int primary key auto_increment,
    name varchar(32),
    state int
)

create table Good(
    id int primary key auto_increment,
    name varchar(32),
    description varchar(128),
    imageUrl varchar(128),
    price int,
    stock int,
    type int/** foreign key GoodType(id) **/
)

create table GoodDetail(
    id int primary key auto_increment,
    imageUrlList varchar(256),
)

/**
state = {"待付款","准备中","已完成","退款中","已取消"}
**/
create table order(
    id int primary key auto_increment
)

insert into GoodType(name,state) values
('年货礼盒',1),
('车里草莓',1),
('好吃推荐',1),
('所有果品',1),
('苹果蕉梨',1),
('休闲干果',1),
('西瓜蜜瓜',1),
('芒橙柑柚',1)

insert into Good(name,description,imageUrl,price,stock,type) values
('苹果','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),
('雪梨','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),
('橙子','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),
('西瓜','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),
('西红柿','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),
('黄皮','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,1),

('苹果2','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),
('雪梨2','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),
('橙子','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),
('西瓜','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),
('西红柿','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),
('黄皮','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,2),

('苹果3','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,3),
('雪梨3','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,3),
('橙子','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,3),
('西瓜','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,3),
('黄皮','3.0rmb/kg','https://image.age.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg','4000',300,3)