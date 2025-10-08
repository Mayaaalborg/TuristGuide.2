package org.example.turistguide2.repository;

import org.example.turistguide2.model.Tags;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//Bruges til at konventere database felt til Java variable
//implementere RowMapper (Funktionel Interface) som bruges til at kalde mapRow metoden som henter data fra hvert ResultSet (linje i databasen)
public class TagRowMapper implements RowMapper<Tags> {
    //Overrider interface metoden
    @Override
    public Tags mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Henter kun navnet på tag, og konventere det til enum i programmet
        String tagName = rs.getString("name");

        return Tags.valueOf(tagName.trim()); //.trim() fjerner whitespace fra string
    }
}
