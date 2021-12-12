create table users_individual (
    user_id bigint primary key auto_increment,
    name varchar(100),
    created_at TIMESTAMP WITH TIME ZONE not null,
    updated_at TIMESTAMP WITH TIME ZONE not null
)
