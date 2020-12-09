create table ProductDetails
(
	productID int primary key ,
	name varchar(50) not null,
	vendorname varchar(50) not null,
	category varchar(50) not null,
	price int not null,
	quantity int not null,
	total varchar(10)not null,
	date varchar(20) not null,
	time varchar(20) not null,
)
use MyProjectJavaDB
create table VendorDetails
(
	
	vendorID int primary key ,
	name varchar(50) not null,
	address varchar(50),
	phone char(10) unique not null,
	email varchar(250)unique,
	
)
use MyProjectJavaDB
select * from ProductDetails
select * from CustomerPaymentDetails
create table CustomerDetails
(
	
	customerID int primary key ,
	name varchar(50) not null,
	address varchar(50) not null,
	city varchar(15) not null,
	phone char(10) unique not null,
	email varchar(250)null,
	
)


create table CustomerPaymentDetails
(
	billno int primary key,
	customerID int not null ,
	name varchar(50) not null,
	totalquantity int not null,
	netamount int not null,	
	gst int not null,
	total int not null,
	date varchar(20) not null,
	time varchar(20) not null,
)
use MyProjectJavaDB
select * from CustomerDetails
select * from CustomerPaymentDetails

create table PurchasedItems
(
	billno int	not null ,
	name varchar(50)not null,
	price int not null,
	quantity int not null,
	total int not null,
)
select * from PurchasedItems