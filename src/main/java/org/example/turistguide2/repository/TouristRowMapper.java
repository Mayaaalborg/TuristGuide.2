package org.example.turistguide2.repository;

import org.example.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TouristRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNums) throws SQLException {
        TouristAttraction t = new TouristAttraction();
        t.setId(rs.getInt("ID"));
        t.setName(rs.getString("name"));
        t.setDescription(rs.getString("description"));
        t.setCitiesID(rs.getInt("citiesID"));
        return t;
    }

}
