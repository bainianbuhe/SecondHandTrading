DROP TABLE IF EXISTS `buckeyetrade_user`;
CREATE TABLE `buckeyetrade_user`  (
  `user_id` int NOT NULL,
  `user_name` varchar(32) ,
  `user_password` varchar(32) ,
  `college_or_school` varchar(30) ,
  `avatar_url` varchar(128) ,
  PRIMARY KEY (`user_id`) 
);
alter table buckeyetrade_user modify user_id int auto_increment;

