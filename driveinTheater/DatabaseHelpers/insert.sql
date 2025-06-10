-- Uživatelé
INSERT INTO users (username, password, role,email) VALUES
                                                       ('admin', '$2a$10$JOqK2.yCnQi8VeJurtV4ouysLeVfVnzAPwE1sDRyRQENdOZKTENIW', 'ADMIN','stepan.pise@gmail.com'),
                                                       ('user1', '$2a$10$R1gRNWZ0eTPaXzZgoKoe5ebxqWke2Y3jTvpOjDJ5aNoEqDhApcudK', 'USER','stepan2.pise@gmail.com'),
                                                       ('user2', '$2a$10$acNg157.YFrjTXjAc3yqwufUMpkWMOuv8AoJx0w9JYaX.BqxZlqc2', 'USER','stepan3.pise@gmail.com');

--1
-- Filmy
INSERT INTO movies (title, description, genre, image_url, duration_minutes) VALUES
                                                                                ('Back to the Future', 'Kultovní sci-fi komedie s autem DeLorean.', 'Sci-Fi', 'future', 116),
                                                                                ('Mad Max: Fury Road', 'Post-apokalyptický akčňák s auty, prachem a šílenstvím.', 'Action', 'madmax', 120),
                                                                                ('Cars', 'Animák o autech, které závodí a mluví.', 'Animation', 'cars', 117),
                                                                                ('The Dark Knight', 'Superhrdinský film o Batmanovi, který se postaví Jokerovi.', 'Action', 'batman', 152),
                                                                                ('Inception', 'Sci-fi thriller o světech ve světech, kde snění má své pravidla.', 'Sci-Fi', 'inception', 148),
                                                                                ('The Lion King', 'Klasický animovaný film o životě lva Simba.', 'Animation', 'lionking', 88),
                                                                                ('Pulp Fiction', 'Kriminální příběh se spoustou nečekaných zvratů a postav.', 'Crime', 'pulp', 154),
                                                                                ('The Matrix', 'Akční sci-fi, kde je realita jen simulace a bojuje se proti strojům.', 'Sci-Fi', 'matrix', 136),
                                                                                ('The Godfather', 'Klasický mafiánský film o rodině Corleone.', 'Crime', 'godfather', 175),
                                                                                ('Forrest Gump', 'Příběh muže, který ovlivnil historii Ameriky, aniž by to věděl.', 'Drama', 'forrestg', 142);

-- Projekce (screenings)
INSERT INTO screenings (movie_id, screening_time, capacity) VALUES
                                                                (1, '2025-05-18 20:00:00', 50),
                                                                (1, '2025-05-17 20:00:00', 50),
                                                                (2, '2025-05-19 21:30:00', 50),
                                                                (2, '2025-05-20 21:30:00', 50),
                                                                (3, '2025-05-20 18:00:00', 50),
                                                                (4, '2025-05-21 22:00:00', 50),
                                                                (5, '2025-05-22 19:30:00', 50),
                                                                (6, '2025-05-23 17:45:00', 50),
                                                                (7, '2025-05-24 23:15:00', 50),
                                                                (8, '2025-05-25 20:30:00', 50),
                                                                (9, '2025-05-26 18:00:00', 50),
                                                                (10, '2025-05-27 21:00:00', 50);

-- Projekce (screenings)
INSERT INTO screenings (movie_id, screening_time, capacity) VALUES
    (1, '2025-05-27 21:00:00', 50);



-- Rezervace (screening_id místo movie_id)
INSERT INTO reservations (user_id, screening_id, parking_spot) VALUES
                                                                   (2, 1, 'A1'),
                                                                   (3, 2, 'B4'),
                                                                   (2, 3, 'C2');

-- Komentáře (stále napojené na movie_id)
INSERT INTO comments (user_id, movie_id, content, created_at) VALUES
                                                                  (2, 1, 'Super film, úplně jak v mládí!', '2025-04-10 15:00:00'),
                                                                  (3, 2, 'Trochu moc hlasitý, ale akce výborná.', '2025-04-11 17:45:00'),
                                                                  (2, 3, 'Děcka to milujou.', '2025-04-12 10:20:00');


select * from users
