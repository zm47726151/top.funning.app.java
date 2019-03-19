create table User(
    id int primary key auto_increment,
    openId varchar(32) UNIQUE
)

/**
state = {"show" = 1,"hide" = 2}
**/
create table GoodType(
    id int primary key auto_increment,
    name varchar(32),
    state int enum('1','2') not null;
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
    state int  enum('1','2') not null default 1,
    type int/** foreign key GoodType(id) **/
)

create table GoodDetail(
    id int primary key auto_increment,
    content text,
    goodId int
)

/**
state = {"待付款" = 1,"准备中" = 2,"已完成" = 3,"退款中" = 4,"已取消" = 5,"已退款" = 6,"已付款" = 7}
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
    state int enum('1','2','3','4','5','6','7') not null,
    userId int,
    createDT datetime not null,
    payDT datetime
)

CREATE TABLE `Admin` (
  `id` int primary key auto_increment,
  `username` varchar(64) UNIQUE NOT NULL,
  `password` char(40) NOT NULL,
  `fail` int,
  `lastFailTime` datetime,
  `salt` char(12) NOT NULL
)

CREATE TABLE `KV`(
  `key` varchar(64) primary key,
  `value` varchar(255)
)

insert into Admin(username,password,salt)values ('fruits@knxy.top','3f4870db36720549b5da31975febf212cfb33e70','33f1c5df698d');

insert into GoodType(name,state) values
('家居日常',1),
('洗浴护理',1),
('除湿驱蚊',1),
('文具文品',1),
('五金工具',1)

insert into Good(name,description,imageUrl,price,stock,type) values
('维达(Vinda) 手帕纸','超韧4层纸巾*36包纸巾(无香)','https://fruits.knxy.top/1.jpg','19.00',300,9),
('洗衣液','','https://fruits.knxy.top/2.jpg','40.00',300,9),
('洗洁精','','https://fruits.knxy.top/3.jpg','11.00',300,9),
('手帕','','https://fruits.knxy.top/4.jpg','15.00',300,9),
('利是封','','https://fruits.knxy.top/5.jpg','5.00',300,9),
('威猛先生厨房去污剂','','https://fruits.knxy.top/6.jpg','25.00',300,9),
('电蚊液','','https://fruits.knxy.top/7.jpg','15.00',300,9),
('电池','','https://fruits.knxy.top/8.jpg','2.00',300,9),
('雨伞','','https://fruits.knxy.top/9.jpg','25.00',300,9),
('卫生棉','','https://fruits.knxy.top/10.jpg','10.00',300,9),
('牙刷','','https://fruits.knxy.top/11.jpg','7.00',300,9),
('牙膏','','https://fruits.knxy.top/12.jpg','21.00',300,9),

('沐浴露','','https://fruits.knxy.top/13.jpg','32.00',300,10),
('香皂','','https://fruits.knxy.top/14.jpg','2.00',300,10),
('洗衣液','','https://fruits.knxy.top/15.jpg','8.00',300,10),
('洗头水','','https://fruits.knxy.top/16.jpg','38.00',300,10),
('漱口水','','https://fruits.knxy.top/17.jpg','10.00',300,10),
('剃须刀','','https://fruits.knxy.top/18.jpg','108.00',300,10),

('电蚊液','','https://fruits.knxy.top/19.jpg','12.00',300,11),
('花露水','','https://fruits.knxy.top/20.jpg','11.00',300,11),
('电蚊器','','https://fruits.knxy.top/21.jpg','15.00',300,11),
('电蚊片','','https://fruits.knxy.top/22.jpg','21.00',300,11),
('电蚊拍','','https://fruits.knxy.top/23.jpg','21.00',300,11),
('蚊香','','https://fruits.knxy.top/24.jpg','10.00',300,11),

('铅笔','','https://fruits.knxy.top/25.jpg','2.00',300,13),
('圆珠笔','','https://fruits.knxy.top/26.jpg','2.00',300,13),
('原稿纸','','https://fruits.knxy.top/27.jpg','1.00',300,13),
('信纸','','https://fruits.knxy.top/28.jpg','1.00',300,13),
('橡皮','','https://fruits.knxy.top/29.jpg','1.00',300,13),

('锤子','','https://fruits.knxy.top/31.jpg','20.00',300,14),
('钉子','','https://fruits.knxy.top/32.jpg','1.00',300,14),
('扳手','','https://fruits.knxy.top/33.jpg','18.00',300,14),
('螺丝','','https://fruits.knxy.top/34.jpg','1.00',300,14),
('螺丝刀','','https://fruits.knxy.top/35.jpg','10.00',300,14),
('铁丝','','https://fruits.knxy.top/36.jpg','4.00',300,14)


insert into Gooddetail(content,goodId) values
('{"header": {"imageList": ["https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg", "https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg", "https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg"]},"detail": {"imageList": ["https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg", "https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg", "https://fruits.knxy.top/430bc1e8f1ae4862a26b5e70a8090460.jpg"]}}',69)