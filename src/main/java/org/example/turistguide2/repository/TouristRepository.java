package org.example.turistguide2.repository;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Tivoli", "Rutjebaner og såen", EnumSet.of(Tags.Fun, Tags.Educational), Cities.Copenhagen));
        attractions.add(new TouristAttraction("Bibliotek", "Man kan blive rundtosset", EnumSet.of(Tags.Library, Tags.Educational, Tags.School, Tags.Free), Cities.Copenhagen));
        attractions.add(new TouristAttraction("FCKstadion", "Parken, Nordens største", EnumSet.of(Tags.Stadion, Tags.Educational, Tags.Fun), Cities.Copenhagen));
        attractions.add(new TouristAttraction("EK", "tidligere kendt som KEA", EnumSet.of(Tags.School, Tags.Educational, Tags.Free), Cities.Copenhagen));
    }

    public List<TouristAttraction> getAll() {
        return attractions;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction attraction1 = new TouristAttraction(attraction.getName(), attraction.getDescription(), attraction.getTag(), attraction.getCity());
        attractions.add(attraction1);
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

    public TouristAttraction updateAttraction(TouristAttraction updatedAttraction) {
        for (TouristAttraction t : attractions) {
            if (t.getName().equalsIgnoreCase(updatedAttraction.getName())) {
                t.setName(updatedAttraction.getName());
                t.setDescription(updatedAttraction.getDescription());
                t.setTag(updatedAttraction.getTag());
                t.setCity(updatedAttraction.getCity());

                return t;
            }
        }
        return null;
    }
}