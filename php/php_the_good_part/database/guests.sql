CREATE DATABASE 'website' ;
USE 'website' ;

CREATE TABLE 'guest' (
  'guestid'     INT NOT NULL AUTO_INCREMENT PRIMARY  KEY ,
  'fname'       VARCHAR( 30 ) NOT NULL ,
  'lname'       VARCHAR( 40 ) NOT NULL ,
  'comments'    TEXT NOT NULL
);
