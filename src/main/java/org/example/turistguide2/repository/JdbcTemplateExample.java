package org.example.turistguide2.repository;

import org.example.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplateExample {
        public static void main(String[] args) {

                JdbcTemplate jdbcTemplate = new JdbcTemplate();

                //Drop table så attactions tabellen ikke duplikere pga auto increment på ID
                jdbcTemplate.execute("DROP TABLE IF EXISTS attractions");

                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractions (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), citiesID INT)");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cities (ID INT PRIMARY KEY, name VARCHAR(100))");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tags (ID INT PRIMARY KEY, name VARCHAR(100))");
                jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS attractionTags (attractionID INT, tagID INT, PRIMARY KEY (attractionID, tagID))");

                jdbcTemplate.update("INSERT IGNORE INTO attractions(name, description, citiesID) VALUES (?,?,?)", "Tivoli", "Rutjebaner og såen", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(name, description, citiesID) VALUES (?,?,?)", "Bibliotek", "Man kan blive rundtosset", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(name, description, citiesID) VALUES (?,?,?)", "FCKstadion", "Parken, Nordens største", 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractions(name, description, citiesID) VALUES (?,?,?)",  "EK", "tidligere kendt som KEA", 1);

                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 1, "Copenhagen");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 2, "Zealand");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 3, "Roskilde");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 4, "Jutland");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 5, "Fyn");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 6, "Odense");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 7, "Aarhus");
                jdbcTemplate.update("INSERT IGNORE INTO cities(ID, name) VALUES (?,?)", 8, "Bornholm");
                //Copenhagen, Zealand, Roskilde, Jutland, Fyn, Odense, Aarhus, Bornholm

                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",1, "Park");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",2, "Stadion");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",3, "School");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",4, "Library");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",5, "Fun");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",6, "Educational");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",7, "Museum");
                jdbcTemplate.update("INSERT IGNORE INTO tags(ID, name) VALUES (?, ?)",8, "Free");


                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 1, 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 1, 2);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 1, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 2, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 2, 5);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 2, 7);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 3, 3);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 3, 1);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 3, 5);
                jdbcTemplate.update("INSERT IGNORE INTO attractionTags(attractionID, tagID) VALUES (?, ?)", 4, 3);

                List<TouristAttraction> attractions = new ArrayList<>();
                SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM attractions");

                while (rowSet.next()){
                        int id = rowSet.getInt("id");
                        String name = rowSet.getString("name");
                        String description = rowSet.getString("description");
                        int city = rowSet.getInt("citiesID");
                        attractions.add(new TouristAttraction(id, name, description, city));
                }

                //System.out.println(TouristAttraction);
        }
       // public

}