package org.example.turistguide2.model;

//Model klasse, bruges til at oprette TouristAttraktions objekter.
public class TouristAttraction {
    private String name;
    private String description;

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TouristAttraction() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
