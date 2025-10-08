package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TouristAttraction> getAll() {
        String sql = "SELECT * FROM attractions";
        List<TouristAttraction> attractions = jdbcTemplate.query(sql, new TouristRowMapper());

        for (TouristAttraction t : attractions) {
            t.setTags(findTagsByAttractionId(t.getId()));
            t.setCity(findCityByCitiesId(t.getCity().getId()));
        }
        return attractions;
    }

    public List<Tags> findTagsByAttractionId(int attractionId) {
        String sql = "SELECT t.ID, t.name FROM tags t JOIN attractiontags a ON a.tagID = t.ID WHERE a.attractionID = ?";
        return jdbcTemplate.query(sql, new TagRowMapper(), attractionId);
    }

    public Cities findCityByCitiesId(int citiesId) {
        String sql = "SELECT ID, name FROM cities WHERE ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CityRowMapper(), citiesId);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            System.out.println("No city found for ID: " + citiesId);
            return null;
        }
    }

    public void addAttraction(TouristAttraction attraction, List<Tags> tags) {
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
        String findIdSql = "SELECT id FROM attractions WHERE name = ?";
        Integer attractionId = jdbcTemplate.queryForObject(findIdSql, Integer.class, name);

        // Slet først alle attractionTags, der er knyttet til attractionId
        String deleteTagsSql = "DELETE FROM attractionTags WHERE attractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attractionId);

        String sql = "DELETE FROM attractions WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public TouristAttraction findAttractionByName(String name) {
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
        String sql = "UPDATE attractions SET name = ?, description = ?, citiesID = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updatedAttraction.getName(),
                updatedAttraction.getDescription(),
                updatedAttraction.getCity().getId(),
                updatedAttraction.getId());

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