CREATE TABLE media_content (
    media_id INT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    release_year INT NOT NULL
);

CREATE TABLE movies (
    media_id INT PRIMARY KEY REFERENCES media_content(media_id),
    duration INT NOT NULL,
    genre VARCHAR(50) NOT NULL
);

CREATE TABLE series (
    media_id INT PRIMARY KEY REFERENCES media_content(media_id),
    genre VARCHAR(50) NOT NULL,
    seasons INT NOT NULL
);

CREATE TABLE episodes (
    media_id INT REFERENCES media_content(media_id),
    episode_id INT PRIMARY KEY,
    duration INT NOT NULL,
    episode_number INT NOT NULL,
    UNIQUE(episode_id, episode_number)
);

INSERT INTO media_content VALUES (1, 'Inception');
INSERT INTO movies VALUES (1, 148, 'Sci-Fi');

INSERT INTO media_content VALUES (2, 'Breaking Bad');
INSERT INTO series VALUES (2, "Crime", 5);

INSERT INTO media_content VALUES (3, "The Fly", 20);
INSERT INTO episodes VALUES (3, 1, 40, 1);