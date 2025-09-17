package org.example.turistguide2.model;

import java.util.EnumSet;

//Model klasse, bruges til at oprette TouristAttraktions objekter.
public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private EnumSet<Tags> tag;
    private Cities city;

    public TouristAttraction(String name, String description, EnumSet<Tags> tag, Cities city) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.city = city;
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

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }
}

