create table task(
    id integer not null auto_increment,
    title varchar(50) not null,
    description varchar(150) not null,
    progress varchar(10) not null,
    due_date datetime default null,
    complete_date datetime default null,
    created_at datetime default current_timestamp,
    primary key (id, created_at)
);

insert into task (title, description, progress)
values ('買い物に行く', 'カレーの材料を買う', 'todo')
