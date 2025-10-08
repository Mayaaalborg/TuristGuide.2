package org.example.turistguide2.model;

import java.util.List;

//Model klasse, bruges til at oprette TouristAttraktions objekter.
public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private List<Tags> tags;
    private Cities city;


    public TouristAttraction() {
    }

    public TouristAttraction(int id, String name, String description, Cities city) {
       this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }
}

