insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$10$DZgsBGRJRTbfnqxaVIUW3.ZWOcGk8uxRAN5bmuKotfHtdmiZ1D95q', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 2);