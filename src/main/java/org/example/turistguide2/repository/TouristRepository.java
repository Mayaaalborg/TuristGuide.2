package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        }
        return attractions;
    }

    public List<Tags> findTagsByAttractionId(int attractionId) {
        String sql = "SELECT t.name FROM tags t JOIN attractiontags at ON at.tagID = t.ID WHERE at.attractionID = ?";
        return jdbcTemplate.query(sql, new TagRowMapper(), attractionId);
    }

    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO attractions (name, description, citiesID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getCitiesID());
    }

    public void deleteAttraction(String name) {
        String sql = "DELETE FROM attractions WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public TouristAttraction findAttractionByName(String name) {
        String sql = "SELECT * FROM attractions WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new TouristRowMapper(), name);
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        String sql = "UPDATE attractions SET name = ?, description = ?, citiesID = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updatedAttraction.getName(),
                updatedAttraction.getDescription(),
                updatedAttraction.getCitiesID(),
                updatedAttraction.getId());
    }

}
