create table User(
    id int primary key auto_increment,
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


/**
state = {"1","2"}
state = 1 : 上架
state = 2 : 下架
**/
create table Good(
    id int primary key auto_increment,
    name varchar(32),
    description varchar(128),
    imageUrl varchar(128),
    price DECIMAL(14,2),
    stock int,
    state int default 1,
    type int/** foreign key GoodType(id) **/
)

create table GoodDetail(
    id int primary key auto_increment,
    content text,
    goodId int
)

/**
state = {"1","2"}
state = 2 : 默认
**/
create table Address(
    id int primary key auto_increment,
    userId char(32),
    area varchar(16),
    address varchar(256),
    phone varchar(32),
    nickname varchar(32),
    state int default 1
)

/**
state = {"待付款" = 1,"准备中" = 2,"已完成" = 3,"退款中" = 4,"已取消" = 5,"已退款" = 6}
**/
create table `Order`(
    id char(32) primary key,
    goods text,
    price DECIMAL(14,2),
    poster DECIMAL(14,2),
    amount DECIMAL(14,2),

    provinceName  varchar(32),
    cityName varchar(32),
    countyName varchar(32),
    detailInfo varchar(255),
    telNumber varchar(32),
    userName varchar(32),
    nationalCode varchar(32),
    postalCode varchar(32),

    note varchar(255),
    state int not null,
    userId int,
    createDT datetime not null,
    payDT datetime
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
('苹果','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),
('雪梨','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),
('橙子','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),
('西瓜','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),
('西红柿','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),
('黄皮','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,1),

('苹果2','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),
('雪梨2','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),
('橙子','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),
('西瓜','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),
('西红柿','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),
('黄皮','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,2),

('苹果3','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,3),
('雪梨3','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,3),
('橙子','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,3),
('西瓜','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,3),
('黄皮','3.0rmb/kg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','40.00',300,3)


insert into GoodDetail(content,goodId)values ("{'header':{imageList:['http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg']},'detai':{imageList:['http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg','http://127.0.0.1:8080/430bc1e8f1ae4862a26b5e70a8090460.jpg']}}",52);


