-- webáruház: kategóriák és termékek
create table product_category (
    id          bigint generated always as identity primary key,
    slug        varchar(30)  not null unique,
    name        varchar(50)  not null,
    description varchar(200) not null,
    sort_order  integer      not null
);

create table product (
    id                 bigint generated always as identity primary key,
    category_id        bigint       not null references product_category (id),
    slug               varchar(60)  not null unique,
    name               varchar(80)  not null,
    description        varchar(300) not null,
    price_huf          integer      not null check (price_huf >= 0),
    -- áthúzott (eredeti) ár, ha akciós; különben null
    original_price_huf integer      check (original_price_huf > price_huf),
    -- melyik szerveren érvényes, pl. "SkyBlock", "Minden szerver"
    server_label       varchar(40)  not null,
    image_url          varchar(255) not null,
    -- "Mit kapsz?" lista a termék oldalon
    perks              text[]       not null default '{}',
    -- limitált termékeknél a darabszám (pl. "Csak 200 db"), különben null
    stock_limit        integer      check (stock_limit > 0),
    -- a "Kiemelt" sorban a helye; null = nem kiemelt
    featured_rank      integer,
    sort_order         integer      not null,
    active             boolean      not null default true
);

create index product_category_idx on product (category_id);
