create table phones (id varchar(255) not null, citycode integer, contrycode integer, number integer, user_id varchar(255), primary key (id))
create table users (id varchar(255) not null, created timestamp, email varchar(255), isactive boolean, last_login timestamp, modified timestamp, name varchar(255), password varchar(255), token varchar(255), primary key (id))
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table phones add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
