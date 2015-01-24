CREATE ROLE prodrem_user LOGIN;
ALTER ROLE prodrem_user WITH PASSWORD 'password1';
CREATE DATABASE prodrem;
CREATE DATABASE prodrem_test;
GRANT ALL PRIVILEGES ON DATABASE prodrem TO prodrem_user;
GRANT ALL PRIVILEGES ON DATABASE prodrem_test TO prodrem_user;
