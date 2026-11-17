-- tartalomgyártók részleg
create table content_creator (
    id               bigint generated always as identity primary key,
    handle           varchar(30)  not null unique,
    subscriber_count integer      not null check (subscriber_count >= 0),
    image_url        varchar(255) not null,
    channel_url      varchar(255) not null
);

