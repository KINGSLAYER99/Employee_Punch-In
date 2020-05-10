create table Employee(
Employ_id int auto_increment,
Employ_name varchar(25),
Date_Of_Birth date,
Address varchar(50),
contact bigint(10) unsigned,
Designation varchar(20),
Salary int(8) unsigned,
Hours_worked tinyint unsigned,
Transactions int unsigned,
Bonus int unsigned,primary key(Employ_id));


create table Verify(
Employ_id int,
username varchar(25),
password varchar(25),
constraint fk1 foreign key(Employ_id) references Employee(Employ_id) on delete cascade);



create table entry_scan(
Employ_id int,
EDate date,
EntryTime time,
ExitTime time,
constraint fk2 foreign key(Employ_id) references Employee(Employ_id) on delete cascade);



delimiter $$
create trigger trig_entry before update on entry_scan
for each row
begin
declare timehr integer;
set timehr=hour(current_time())-hour(old.EntryTime);
update Employee set Hours_worked=Employee.Hours_worked+timehr where Employ_id=old.Employ_id;
update Employee set Bonus=Employee.Bonus+(20*(timehr)) where Employ_id=old.Employ_id;
end
$$




delimiter $$
create trigger trig_remove before delete on Employee
for each row
begin
delete from entry_scan where Employ_id=old.Employ_id;
delete from Verify where Employ_id=old.Employ_id;
end
$$




delimiter $$
create function login(user_name varchar(25),pass_word varchar(25))
returns int
READS SQL DATA
DETERMINISTIC
begin
declare e_id int;
set e_id=(select Employ_id from Verify where Username=user_name and Password=pass_word);
return e_id;
end
$$
$$
