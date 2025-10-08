package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//Bruges til at konventere database felt til Java variable
//implementere RowMapper (Funktionel Interface) som bruges til at kalde mapRow metoden som henter data fra hvert ResultSet (linje i databasen)
public class TouristRowMapper implements RowMapper<TouristAttraction> {
    //Overrider interface metoden
    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNums) throws SQLException {
        //Henter linjens felt i DB og konventere det til et objekt
        TouristAttraction t = new TouristAttraction();
        t.setId(rs.getInt("ID"));
        t.setName(rs.getString("name"));
        t.setDescription(rs.getString("description"));
        t.setCity(Cities.fromId(rs.getInt("citiesID")));
        return t;
    }
}
