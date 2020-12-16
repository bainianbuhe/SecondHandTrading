drop table if exists comment;
create table comment(
comment_id int not null primary key,
comment_time datetime not null,
from_id int not null,
content varchar(128) not null,
owner_id int not null
);
alter table `comment` modify comment_id int auto_increment;