-- Uživatelé
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('USER', 'ADMIN')),
                       email VARCHAR(255) UNIQUE
);

-- Filmy
CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        genre VARCHAR(100),
                        image_url TEXT,
                        duration_minutes INT NOT NULL
);

-- Projekce
CREATE TABLE screenings (
                            id SERIAL PRIMARY KEY,
                            movie_id INTEGER NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
                            screening_time TIMESTAMP NOT NULL,
                            capacity INT NOT NULL
);

-- Rezervace
CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                              screening_id INTEGER NOT NULL REFERENCES screenings(id) ON DELETE CASCADE,
                              parking_spot VARCHAR(10) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              UNIQUE (screening_id, parking_spot)
);

-- Komentáře, NEPOUZITE
CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          movie_id INTEGER NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
                          content TEXT NOT NULL,
                          image_url TEXT, -- volitelný obrázek od uživatele
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
