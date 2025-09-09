package org.example.turistguide2.model;

import java.util.EnumSet;

//Model klasse, bruges til at oprette TouristAttraktions objekter.
public class TouristAttraction {
    private String name;
    private String description;
    private EnumSet<Tags> tag;
    private Citys city;

    public TouristAttraction(String name, String description, EnumSet<Tags> tag, Citys city) {
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

    public String getDescription() {
        return description;
    }

    public EnumSet<Tags> getTag() {
        return tag;

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Citys getCity()
    { return city; }
}
