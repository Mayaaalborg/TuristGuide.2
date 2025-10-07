package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<Cities> {
    public Cities mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Henter kun navnet på city, og konventere det til enum i programmet
        String cityName = rs.getString("name");

        return Cities.valueOf(cityName.trim()); //.trim() fjerner whitespace fra string
    }
}
