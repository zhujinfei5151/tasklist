delete from task;
delete from user;
insert into user (id, passwordHash, username) values (1, "38fe8951595f01a3c16f3d50ea0bcc53", "matti");
insert into task (id, description, done, user_id) values (1, "hey hou lets go", 0, 1);
insert into task (id, description, done, user_id) values (2, "santa is coming to town", 0, 1);
insert into task (id, description, done, user_id) values (3, "take your mother to movie theater", 0, 1);