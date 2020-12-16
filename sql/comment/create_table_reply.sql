drop table if exists reply;
create table reply(
reply_id int not null primary key,
reply_time datetime not null,
from_id int not null,
to_id int not null,
content varchar(128) not null,
owner_id int not null
);
alter table `reply` modify reply_id int auto_increment;