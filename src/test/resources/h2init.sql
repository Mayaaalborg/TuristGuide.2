CREATE TABLE IF NOT EXISTS attractions (
    ID INT PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(100),
    citiesID INT
    );

CREATE TABLE IF NOT EXISTS cities (
    ID INT PRIMARY KEY,
    name VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS tags (
    ID INT PRIMARY KEY,
    name VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS attractionTags (
    attraction_ID INT,
    tags_ID INT,
    PRIMARY KEY (attraction_ID, tags_ID)
    FOREIGN KEY (attraction_ID) REFERENCES attraction (ID)
    FOREIGN KEY (tags_ID) REFERENCES tags(ID)
    );


-- init data


INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (1, "Tivoli", "Rutjebaner og såen", 1);
INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (2, "Bibliotek", "Man kan blive rundtosset", 1);
INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (3, "FCKstadion", "Parken, Nordens største", 1);
INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (4, "EK", "tidligere kendt som KEA", 1);

INSERT IGNORE INTO cities(ID, name) VALUES (1, "København");
INSERT IGNORE INTO cities(ID, name) VALUES (2, "Sjælland");
INSERT IGNORE INTO cities(ID, name) VALUES (3, "Roskilde");
INSERT IGNORE INTO cities(ID, name) VALUES (4, "Fyn");

INSERT IGNORE INTO tags(ID, name) VALUES (1, "Park");
INSERT IGNORE INTO tags(ID, name) VALUES (2, "Stadion");
INSERT IGNORE INTO tags(ID, name) VALUES (3, "School");
INSERT IGNORE INTO tags(ID, name) VALUES (4, "Library");
INSERT IGNORE INTO tags(ID, name) VALUES (5, "Fun");
INSERT IGNORE INTO tags(ID, name) VALUES (6, "Educational");
INSERT IGNORE INTO tags(ID, name) VALUES (7, "Museum");
INSERT IGNORE INTO tags(ID, name) VALUES (8, "Free");


INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (1, 1);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (1, 2);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (1, 3);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (2, 3);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (2, 5);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (2, 7);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (3, 3);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (3, 1);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (3, 5);
INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (4, 3);