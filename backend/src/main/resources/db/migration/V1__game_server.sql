-- főoldal "Szerverek" kártyáinak adatai
create table game_server (
    id            bigint generated always as identity primary key,
    name          varchar(50)  not null unique,
    description   varchar(200) not null,
    version_label varchar(20)  not null,
    image_url     varchar(255) not null
);
