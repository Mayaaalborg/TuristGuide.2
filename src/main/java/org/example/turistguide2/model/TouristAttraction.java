package org.example.turistguide2.model;

import java.util.EnumSet;

//Model klasse, bruges til at oprette TouristAttraktions objekter.
public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private int citiesID;
    private EnumSet<Tags> tag;
    private Cities city;




    public TouristAttraction(int id, String name, String description, int citiesID) {
       this.id = id;
        this.name = name;
        this.description = description;
        this.citiesID = citiesID;
    }

    public TouristAttraction() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumSet<Tags> getTag() {
        return tag;
    }
    public void setTag(EnumSet<Tags> tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitiesID() {
        return citiesID;
    }

    public void setCitiesID(int citiesID) {
        this.citiesID = citiesID;
    }
}

