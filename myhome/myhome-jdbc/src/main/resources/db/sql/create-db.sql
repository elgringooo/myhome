
SET DATABASE SQL SYNTAX ORA TRUE; 

CREATE TABLE Customer (
  id number(11)  NOT NULL PRIMARY KEY ,
  name varchar(20)
) ;

CREATE TABLE Address (
  id number(11)  NOT NULL PRIMARY KEY ,
  address varchar2(200),
  country varchar2(200)
); 