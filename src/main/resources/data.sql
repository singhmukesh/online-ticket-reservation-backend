INSERT INTO oauth_client_details(client_id, client_secret, web_server_redirect_uri, scope, access_token_validity,
                                 refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('web', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:9191/login',
        'READ,WRITE', 3600, 10000, 'inventory,payment,web', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO permission
    (NAME)
VALUES ('add_flight'),
       ('edit_flight'),
       ('delete_flight'),
       ('view_flight');

INSERT INTO role (NAME)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');
INSERT
INTO permission_role(PERMISSION_ID, ROLE_ID)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (4, 2);
INSERT INTO user (id, username, password,phone_number, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('1', 'anil', '{bcrypt}$2y$12$0UrGpzkwu1oT3xsMJNPSxutb8Z8wCP43aDTddFiiWUvG6NHXJd5ai','9823226555', 'admin@gmail.com',
        '1', '1', '1', '1');
insert into user (id, username, password,phone_number, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES ('2', 'test', '{bcrypt}$2y$12$zfq0FFRhRb0plLTVXba0EumJ70EZHrdyB1lDBdrn4eyl68tWZEyhu', '9823226555','user@gmail.com', '1',
        '1', '1', '1');
INSERT INTO role_user(ROLE_ID, USER_ID)
VALUES (1, 1),
       (2, 2);
