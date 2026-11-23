-- webáruház kezdő adatai (a Figma terv alapján)
insert into product_category (slug, name, description, sort_order) values
    ('rangok',      'Rangok',      'Kényelmi és kozmetikai előnyök 30 napra – pay-to-win nélkül.', 1),
    ('erme',        'Érme',        'Játékbeli pénz a SkyBlock és Survival gazdaságához.', 2),
    ('kiegeszitok', 'Kiegészítők', 'Szettek, kozmetikumok, limitált csomagok.', 3),
    ('kredit',      'Kredit',      'Webes egyenleg: fizess vele a boltban, vagy utald egy barátodnak ajándékba.', 4);

insert into product (category_id, slug, name, description, price_huf, original_price_huf, server_label, image_url, perks, stock_limit, featured_rank, sort_order) values
    ((select id from product_category where slug = 'rangok'), 'vip-rang', 'VIP rang (30 nap)',
     '3 home, színes chat, /hat és egyedi prefix minden szerveren.', 1990, null, 'Minden szerver', '/img/shop/vip-rang.png',
     array['3 home pont (/sethome)', 'Színes chat és egyedi VIP prefix', '/hat – bármilyen blokk a fejedre', 'Soron kívüli belépés, ha tele a szerver'],
     null, 2, 1),
    ((select id from product_category where slug = 'rangok'), 'mvp-rang', 'MVP rang (30 nap)',
     '5 home, /fly a spawnon, egyedi prefix és havi kozmetikum.', 3990, 4990, 'Minden szerver', '/img/shop/mvp-rang.png',
     '{}', null, 4, 2),
    ((select id from product_category where slug = 'rangok'), 'lush-rang', 'LUSH rang (30 nap)',
     'A legmagasabb rang: minden előny, exkluzív kozmetikumok, prioritás a ticketekben.', 7990, null, 'Minden szerver', '/img/shop/lush-rang.png',
     '{}', null, null, 3),

    ((select id from product_category where slug = 'erme'), 'erme-500', '500 érme',
     'Gyors indítás: 500 érme a SkyBlock gazdaságba.', 790, null, 'SkyBlock', '/img/shop/erme-500.png',
     '{}', null, null, 1),
    ((select id from product_category where slug = 'erme'), 'erme-1500', '1 500 érme',
     'A legnépszerűbb csomag: 1 500 érme, 10% bónusszal.', 1990, null, 'SkyBlock', '/img/shop/erme-1500.png',
     '{}', null, 3, 2),
    ((select id from product_category where slug = 'erme'), 'erme-5000', '5 000 érme',
     'Nagy csomag 20% bónusszal a komoly szigetépítőknek.', 5490, null, 'SkyBlock', '/img/shop/erme-5000.png',
     '{}', null, null, 3),

    ((select id from product_category where slug = 'kiegeszitok'), 'boss-szett', 'Boss-szett',
     'Teljes Obszidián páncél és egy Bölcsesség talizmán – a bossok ellen.', 2490, null, 'SkyBlock', '/img/shop/boss-szett.png',
     '{}', null, 1, 1),
    ((select id from product_category where slug = 'kiegeszitok'), 'tok-korona', 'Tök-korona (limitált)',
     'Halloween kozmetikum, csak november 2-ig. Minden szerveren látszódik.', 990, null, 'Minden szerver', '/img/shop/tok-korona.png',
     '{}', 200, null, 2),
    ((select id from product_category where slug = 'kiegeszitok'), 'kezdocsomag', 'Kezdőcsomag',
     'Szerszámok, étel és 300 érme az első napokra.', 490, 990, 'Minden szerver', '/img/shop/kezdocsomag.png',
     '{}', null, null, 3),

    ((select id from product_category where slug = 'kredit'), 'kredit-1000', '1 000 kredit',
     'Webes egyenleg a boltba – vagy ajándék egy barátodnak.', 1000, null, 'Webes egyenleg', '/img/shop/kredit-1000.png',
     '{}', null, null, 1),
    ((select id from product_category where slug = 'kredit'), 'kredit-2600', '2 600 kredit',
     '2 500 Ft-ért 2 600 kredit – 100 ajándék kredittel.', 2500, null, 'Webes egyenleg', '/img/shop/kredit-2600.png',
     '{}', null, null, 2),
    ((select id from product_category where slug = 'kredit'), 'kredit-5400', '5 400 kredit',
     '5 000 Ft-ért 5 400 kredit – 400 ajándék kredittel.', 5000, null, 'Webes egyenleg', '/img/shop/kredit-5400.png',
     '{}', null, null, 3);
