package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Gør klassen til en Spring Repository-komponent, så den kan bruges med dependency injection
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate; // Bruges til at udføre SQL-forespørgsler mod databasen

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TouristAttraction> getAll() {
        String sql = "SELECT * FROM attractions";
        List<TouristAttraction> attractions = jdbcTemplate.query(sql, new TouristRowMapper());

        // For hver attraction hentes de relaterede tags og city-objekt
        for (TouristAttraction t : attractions) {
            t.setTags(findTagsByAttractionId(t.getId())); // Finder alle tags, som hører til attraction
            t.setCity(findCityByCitiesId(t.getCity().getId())); // Finder city ud fra cityID
        }
        return attractions; // Returnerer listen med færdige attraction-objekter
    }

    public List<Tags> findTagsByAttractionId(int attractionId) {
        // Finder alle tags knyttet til en attraction via join-tabel
        String sql = "SELECT t.ID, t.name FROM tags t JOIN attractiontags a ON a.tagID = t.ID WHERE a.attractionID = ?";
        return jdbcTemplate.query(sql, new TagRowMapper(), attractionId); // Returnerer en liste af Tags-objekter
    }

    public Cities findCityByCitiesId(int citiesId) {
        // Finder én by baseret på ID
        String sql = "SELECT ID, name FROM cities WHERE ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CityRowMapper(), citiesId); // Returnerer City-objekt
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            System.out.println("No city found for ID: " + citiesId); // Hvis ingen resultater findes
            return null;
        }
    }

    public void addAttraction(TouristAttraction attraction, List<Tags> tags) {
        // Indsætter ny attraction i databasen
        String sql = "INSERT INTO attractions (name, description, citiesID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getCity().getId());

        // SQL laver auto ID
        Integer attractionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        //Tjekker om man har valgt tags - for hvert tag laves der en række i attractionTags
        if (tags != null && !tags.isEmpty()) {
            String sqlTag = "INSERT INTO attractionTags (attractionID, tagID) VALUES (?, ?)";
            for (Tags tag : tags) {
                jdbcTemplate.update(sqlTag, attractionId, tag.getId());
            }
        }
    }
    public void deleteAttraction(String name) {
        // Finder attractionID ud fra navn
        String findIdSql = "SELECT id FROM attractions WHERE name = ?";
        Integer attractionId = jdbcTemplate.queryForObject(findIdSql, Integer.class, name);

        // Slet først alle attractionTags, der er knyttet til attractionId
        String deleteTagsSql = "DELETE FROM attractionTags WHERE attractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attractionId);

        // Sletter selve attraction fra attractions-tabellen
        String sql = "DELETE FROM attractions WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public TouristAttraction findAttractionByName(String name) {
        // Finder en attraction ud fra navn
        String sql = "SELECT * FROM attractions WHERE name = ?";
        List<TouristAttraction> results = jdbcTemplate.query(sql, new TouristRowMapper(), name);

        // Hvis der ikke findes nogen attraktion med det navn, returneres null (ingen fejl kastes)
        if (results.isEmpty()) {
            return null; // Returnér null i stedet for at kaste fejl
        }
        // Henter første match fra resultatlisten
        TouristAttraction attraction = results.get(0);

        // Tilføjer tags og city til attraction-objektet
        attraction.setTags(findTagsByAttractionId(attraction.getId()));
        attraction.setCity(findCityByCitiesId(attraction.getCity().getId()));

        // Returnerer det fuldt udfyldte attraction-objekt
        return attraction;
    }

    public void updateAttraction(TouristAttraction updatedAttraction, List<Tags> tags) {
        // Finder en attraction ud fra navn
        String sql = "UPDATE attractions SET name = ?, description = ?, citiesID = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updatedAttraction.getName(),
                updatedAttraction.getDescription(),
                updatedAttraction.getCity().getId(),
                updatedAttraction.getId());
// Sletter alle eksisterende tag-koblinger for at erstatte dem
        String deleteSql = "DELETE FROM attractionTags WHERE attractionID = ?";
        jdbcTemplate.update(deleteSql, updatedAttraction.getId());

        //Tjekker om man har valgt tags - for hvert tag laves der en række i attractionTags
        if (tags != null && !tags.isEmpty()) {
            String sqlTag = "INSERT INTO attractionTags (attractionID, tagID) VALUES (?, ?)";
            for (Tags tag : tags) {
                jdbcTemplate.update(sqlTag, updatedAttraction.getId(), tag.getId());
            }
        }

    }
}