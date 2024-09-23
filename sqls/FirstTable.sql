drop table taco;
drop table tacoorder;
drop table ingredientref;
drop table ingredient;

create table if not exists tacoorder (
	id integer not null primary key,
	deliveryName varchar(50) not null,
 	deliveryStreet varchar(50) not null,
 	deliveryCity varchar(50) not null,
 	deliveryState varchar(2) not null,
 	deliveryZip varchar(10) not null,
 	ccNumber varchar(16) not null,
 	ccExpiration varchar(5) not null,
 	ccCvv varchar(3) not null,
 	placedAt timestamp not null
);

create table if not exists taco (
 	id integer not null primary key,
 	name varchar(50) not null,
 	tacoOrderId integer not null,
 	tacoOrderKey integer not null,
 	createdAt timestamp not null
);

create table if not exists ingredientref (
	 ingredientId varchar(4) not null,
	 tacoId integer not null,
	 tacoKey integer not null,
	 primary key(ingredientId, tacoId)
);

create table if not exists ingredient (
	 id varchar(4) not null primary key,
	 name varchar(25) not null,
	 type varchar(10) not null
);

alter table taco
 	add foreign key (tacoOrderId) references tacoorder(id);

alter table ingredientref
 	add foreign key (ingredientId) references ingredient(id);
 
alter table ingredientref
 	add foreign key (tacoId) references taco(id);

delete from ingredientref;
delete from taco;
delete from tacoorder;
delete from ingredient;

insert into Ingredient (id, name, type)
 	values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
 	values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
 	values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
 	values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
 	values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
 	values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
 	values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
 	values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
 	values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
 	values ('SRCR', 'Sour Cream', 'SAUCE');