CREATE TABLE cities (
                        ID INT PRIMARY KEY,
                        name VARCHAR(100)
);

CREATE TABLE attractions (
                             ID INT PRIMARY KEY,
                             name VARCHAR(100),
                             description VARCHAR(100),
                             citiesID INT,
                             FOREIGN KEY (citiesID) REFERENCES cities(ID)
);

CREATE TABLE tags (
                      ID INT PRIMARY KEY,
                      name VARCHAR(100)
);

CREATE TABLE attractionTags (
                                attractionID INT,
                                tagID INT,
                                PRIMARY KEY (attractionID, tagID),
                                FOREIGN KEY (attractionID) REFERENCES attractions(ID),
                                FOREIGN KEY (tagID) REFERENCES tags(ID)
);

-- init data

INSERT INTO cities(ID, name) VALUES (1, 'Copenhagen');
INSERT INTO cities(ID, name) VALUES (2, 'Zealand');
INSERT INTO cities(ID, name) VALUES (3, 'Roskilde');
INSERT INTO cities(ID, name) VALUES (4, 'Jutland');
INSERT INTO cities(ID, name) VALUES (5, 'Fyn');
INSERT INTO cities(ID, name) VALUES (6, 'Odense');
INSERT INTO cities(ID, name) VALUES (7, 'Aarhus');
INSERT INTO cities(ID, name) VALUES (8, 'Bornholm');

INSERT INTO attractions(id, name, description, citiesid)
VALUES (1, 'Tivoli', 'Rutjebaner og såen', 1),
       (2, 'Bibliotek', 'Man kan blive rundtosset', 1),
       (3, 'FCKstadion', 'Parken, Nordens største', 1),
       (4, 'EK', 'tidligere kendt som KEA', 1);

INSERT INTO tags(ID, name) VALUES (1, 'Park');
INSERT INTO tags(ID, name) VALUES (2, 'Stadion');
INSERT INTO tags(ID, name) VALUES (3, 'School');
INSERT INTO tags(ID, name) VALUES (4, 'Library');
INSERT INTO tags(ID, name) VALUES (5, 'Fun');
INSERT INTO tags(ID, name) VALUES (6, 'Educational');
INSERT INTO tags(ID, name) VALUES (7, 'Museum');
INSERT INTO tags(ID, name) VALUES (8, 'Free');

INSERT INTO attractionTags(attractionID, tagID) VALUES (1, 1);
INSERT INTO attractionTags(attractionID, tagID) VALUES (1, 2);
INSERT INTO attractionTags(attractionID, tagID) VALUES (1, 3);
INSERT INTO attractionTags(attractionID, tagID) VALUES (2, 3);
INSERT INTO attractionTags(attractionID, tagID) VALUES (2, 5);
INSERT INTO attractionTags(attractionID, tagID) VALUES (2, 7);
INSERT INTO attractionTags(attractionID, tagID) VALUES (3, 3);
INSERT INTO attractionTags(attractionID, tagID) VALUES (3, 1);
INSERT INTO attractionTags(attractionID, tagID) VALUES (3, 5);
INSERT INTO attractionTags(attractionID, tagID) VALUES (4, 3);