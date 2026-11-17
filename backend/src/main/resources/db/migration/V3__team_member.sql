-- főoldal csapattagok adatai
create table team_member (
    id          bigint generated always as identity primary key,
    name        varchar(16)  not null unique,
    role        varchar(30)  not null,
    description varchar(200) not null,
    head_url    varchar(255) not null,
    render_url  varchar(255)
);