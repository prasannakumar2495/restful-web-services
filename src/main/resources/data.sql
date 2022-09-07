insert into USER_DETAILS(id,dob,name)
values(1000,current_date(),'PK');

insert into USER_DETAILS(id,dob,name)
values(1001,current_date(),'Prasanna');

insert into USER_DETAILS(id,dob,name)
values(1002,current_date(),'Kumar');

INSERT INTO post (id, `description`, USER_DETAILS_ID)
VALUES (2001, 'Kotlin', 1000);

INSERT INTO post (id, `description`, USER_DETAILS_ID)
VALUES (2002, 'Java', 1001);

INSERT INTO post (id, `description`, USER_DETAILS_ID)
VALUES (2003, 'Python', 1001);

INSERT INTO post (id, `description`, USER_DETAILS_ID)
VALUES (2004, 'C++', 1002);