/*
recreate table limbs in MYSQL database cookbook;




*/
USE cookbook;

DROP TABLE IF EXISTS limbs;
CREATE TABLE limbs
(
  thing VARCHAR(20),  # what the thing is
  legs  INT,          # number of legs it has
  arms  INT          # number of arms it has
);

INSERT INTO limbs (thing, legs, arms) VALUES
('human', 2, 2),
('insert', 6, 0),
('squid', 0, 10),
('fish', 0, 0),
('centipede', 100, 0),
('table', 4, 0),
('armchair', 4, 2),
('phonograph', 0, 1),
('tripod', 3, 0),
('Peg Leg Pete', 1, 2),
('space alien', NULL, NULL);
