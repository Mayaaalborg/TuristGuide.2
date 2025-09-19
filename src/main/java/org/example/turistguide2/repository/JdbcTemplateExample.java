package org.example.turistguide2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class JdbcTemplateExample {
        public static void main(String[] args) {
                DataSource dataSource = new DriverManagerDataSource(
                        "jdbc:mysql://localhost:3306/TuristGuide", "root", "Ngn24wwt"
                );

                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractions (ID INT PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), citiesID INT)");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cities (ID INT PRIMARY KEY, name VARCHAR(100))");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tags (ID INT PRIMARY KEY, name VARCHAR(100))");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractionTags (attraction.ID INT PRIMARY KEY, tags.id INT PRIMARY KEY");


                jdbcTemplate.update("INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (?,?,?,?)",1, "Tivoli", "Rutjebaner og såen", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (?,?,?,?)",2, "Bibliotek", "Man kan blive rundtosset", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (?,?,?,?)",3, "FCKstadion", "Parken, Nordens største", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(ID, name, description, citiesID) VALUES (?,?,?,?)",4, "EK", "tidligere kendt som KEA", 1);

                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?, ?)", 1, "København");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?, ?)", 2, "Sjælland");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?, ?)", 3, "Roskilde");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?, ?)", 4, "Fyn");

                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 1, "Park");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 2, "Stadion");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 3, "School");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 4, "Library");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 5, "Fun");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 6, "Educational");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 7, "Museum");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)", 8, "Free");


                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 1, 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 1, 2);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 1, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 2, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 2, 5);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 2, 7);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 3, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 3, 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 3, 5);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attraction.ID, tags.ID) VALUES (?, ?)", 4, 3);


        }

}