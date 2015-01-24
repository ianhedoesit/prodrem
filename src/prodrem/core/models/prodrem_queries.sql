-- name: all-users
-- Selects all users
SELECT id,
       username,
       email,
       accountname
      FROM users;

-- name: insert-user<!
-- Queries a single user
INSERT INTO users (username, email, accountname)
  VALUES (:username, :email, :accountname);

-- name: delete-user<!
-- Deletes a single user
DELETE FROM users WHERE id = :id;

-- name: update-user<!
-- Update a single user
UPDATE users SET
  username = :username,
  email = :email,
  accountname = :accountname
  WHERE id = :id;

-- name: drop-users-table!
-- Drop the users table
DROP TABLE users;

-- name: create-users-table-if-not-exists!
-- Create the users table if it does not exist
CREATE TABLE IF NOT EXISTS users (
  id serial PRIMARY KEY,
  username VARCHAR (40) NOT NULL, -- Prodrem username
  email VARCHAR (60) NOT NULL,
  accountname VARCHAR (40) NOT NULL); -- GitHub (or other) username
