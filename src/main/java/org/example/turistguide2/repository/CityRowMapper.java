package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
//Bruges til at konventere database felt til Java variable
//implementere RowMapper (Funktionel Interface) som bruges til at kalde mapRow metoden som henter data fra hvert ResultSet (linje i databasen)
public class CityRowMapper implements RowMapper<Cities> {
    //Overrider interface metoden
    @Override
    public Cities mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Henter kun navnet på city, og konventere det til enum i programmet
        String cityName = rs.getString("name");

        return Cities.valueOf(cityName.trim()); //.trim() fjerner whitespace fra string
    }
}
