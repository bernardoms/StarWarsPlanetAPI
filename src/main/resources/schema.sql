DROP TABLE planet IF EXISTS;
create table planet 
(
   id long not null AUTO_INCREMENT,
   nome varchar(255) not null,
   clima varchar(255) not null,
   terreno varchar(255) not null,
   qtdAparicao integer,
   primary key(id)
);