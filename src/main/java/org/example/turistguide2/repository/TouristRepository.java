package org.example.turistguide2.repository;

import org.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Tivoli", "Rutjebaner og såen"));
        attractions.add(new TouristAttraction("Bibliotek", "Man kan blive rundtosset"));
        attractions.add(new TouristAttraction("FCKstadion", "Nordens største"));
        attractions.add(new TouristAttraction("EK", "tidligere kendt som KEA"));
    }

    public List<TouristAttraction> getAll() {
        return attractions;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    public TouristAttraction deleteAttraction(TouristAttraction attraction) {
        for (int i = 0; i < attractions.size(); i++) {
            TouristAttraction t = attractions.get(i);
            if (attraction.getName().equals(t.getName())) {
                return attractions.remove(i);
            }
        }
        return null;
    }

    public TouristAttraction findAttractionByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction t : attractions) {
            if (t.getName().equals(name)) {
                t.setName(updatedAttraction.getName());
                t.setDescription(updatedAttraction.getDescription());
                return t;
            }
        }
        return null;
    }
}