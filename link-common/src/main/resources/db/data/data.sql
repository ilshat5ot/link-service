insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$10$DZgsBGRJRTbfnqxaVIUW3.ZWOcGk8uxRAN5bmuKotfHtdmiZ1D95q', 'user@gmail.com');

insert into users (username, password, email)
values ('admin', '$2a$10$AQVda6mndiiEDKx894vtheivRzTYpK98I4B2zl4q1us7tfZHoa8qq', 'admin@gmail.com');


select id from users where username = 'user';
insert into users_roles (user_id, role_id)
values (1, 1);

insert into users_roles (user_id, role_id)
values (2, 2);