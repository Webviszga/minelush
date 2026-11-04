# MineLush

Magyar Minecraft szerverhálózat weboldala: hírek, wiki, játékmódok, csapat, tartalomgyártók,
webshop és admin panel egy helyen. Iskolai projekt.

## Funkciók

- **Blog:** hírek és eventek kereséssel, szűréssel, Markdown formázással és kedveléssel
- **Wiki:** fa szerkezetű tudásbázis saját AI asszisztenssel, ami csak a wiki tartalma alapján válaszol
- **Szerverek:** játékmódok listája valós idejű státusszal és játékosszámmal, részletes adatlapokkal
- **Csapat:** interaktív staff választó 3D karakter renderrel
- **Media Hub:** a tartalomgyártók videói és a partner program
- **Fiók:** belépés a játékban használt fiókkal (nLogin), kétlépcsős azonosítás
- **Webshop:** termékek, előfizetés, kreditrendszer, marketplace és napi jutalmak
- **Admin panel:** statisztikák, tartalomkezelés, felhasználók, jogosultságok és karbantartási mód

## Technológiák

| Rész | Technológia | Mappa |
|---|---|---|
| Frontend | Angular 22 (SSR) + TypeScript | `frontend/` |
| Backend | Spring Boot 4.1.1, Java 25 | `backend/` |
| Adatbázis | PostgreSQL 18 | – |
| Futtatás | Docker Compose | – |

## Mappaszerkezet

```
minelush/
├── backend/             Spring Boot API (hu.minelush.backend)
├── frontend/            Angular SSR alkalmazás
│   └── src/server.ts    Express szerver, ez továbbítja az /api kéréseket
├── docker-compose.yml   PostgreSQL adatbázis
├── .env.example         minta a környezeti változókhoz
└── README.md
```

## Előfeltételek

- JDK 25: [Oracle](https://www.oracle.com/java/technologies/downloads/#java25) vagy [Eclipse Temurin (Adoptium)](https://adoptium.net/temurin/releases/?version=25), mindkettő jó. A `JAVA_HOME` erre mutasson, különben a backend nem indul el.
- [Node.js 24](https://nodejs.org/) vagy újabb
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Környezeti változók

A gyökérben lévő `.env` fájlban vannak. Mintának ott a `.env.example`.

| Változó | Mire való | Alapérték |
|---|---|---|
| `POSTGRES_DB` | Az adatbázis neve | – (kötelező) |
| `POSTGRES_USER` | Adatbázis felhasználó | – (kötelező) |
| `POSTGRES_PASSWORD` | Adatbázis jelszó | – (kötelező) |
| `DB_HOST` | Az adatbázis címe (a backend használja) | `localhost` |
| `DB_PORT` | Az adatbázis portja (a backend használja) | `5432` |
| `BACKEND_URL` | Hova továbbítja a frontend az /api kéréseket | `http://localhost:8090` |

## Első indítás

1. Környezeti változók: másold le a `.env.example` fájlt `.env` néven, és írd át benne a jelszót.
   A `.env` nem kerül gitre.
2. Adatbázis indítása (a repó gyökerében):
   ```bash
   docker compose up -d db
   ```
3. Backend indítása (http://localhost:8090):
   ```bash
   cd backend
   .\mvnw spring-boot:run
   ```
4. Frontend indítása (http://localhost:4200):
   ```bash
   cd frontend
   npm install
   npm start
   ```

Ellenőrzés: a http://localhost:4200/api/ping címen ezt kell látnod: `{"status":"ok"}`.

## Hibaelhárítás

**A backend nem tud csatlakozni az adatbázishoz** (pl. `database "minelush" does not exist`):
a Postgres a `.env`-ben megadott adatbázist csak az első indításkor hozza létre.
Ha azóta módosult a `.env`, töröld a volume-ot és indítsd újra (ez törli a helyi adatokat!):
```bash
docker compose down -v
docker compose up -d db
```

**`release version 25 not supported`** hiba a backend indításakor:
a `JAVA_HOME` régebbi JDK-ra mutat. Állítsd át a JDK 25 mappájára.

## Szerzők

- Szép Andor
- Nagy Szemere
- Nagy Áron

## Linkek

- Figma design: https://www.figma.com/design/f1a75P9hbKmWKzPNWDX7KV/MineLush