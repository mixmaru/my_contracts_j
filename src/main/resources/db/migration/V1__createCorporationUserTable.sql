create table users (
                       id bigint primary key auto_increment,
                       created_at TIMESTAMP WITH TIME ZONE not null,
                       updated_at TIMESTAMP WITH TIME ZONE not null
);

create table users_individual (
                                  user_id bigint primary key,
                                  name varchar(100)
);

create table users_corporation (
                                   user_id bigint primary key,
                                   contact_person_name varchar(100),
                                   president_name varchar(100),
                                   corporation_name varchar(100)
);
