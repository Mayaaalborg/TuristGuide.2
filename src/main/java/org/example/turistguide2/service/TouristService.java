
package org.example.turistguide2.service;


import org.example.turistguide2.model.Tags;
import org.springframework.stereotype.Service;
import org.example.turistguide2.repository.TouristRepository;
import org.example.turistguide2.model.TouristAttraction;

import java.util.List;

@Service
public class TouristService {

    //Vi opretter reference til repository som taler med databasen.
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public void addAttraction(TouristAttraction attraction, List<Tags> tags) {
        repository.addAttraction(attraction, tags);
    }


    public List<TouristAttraction> getAll() {
        return repository.getAll();
    }


    public TouristAttraction findAttractionByName(String name) {
        return repository.findAttractionByName(name);
    }


    public void updateAttraction(TouristAttraction attraction, List<Tags> tags) {
        repository.updateAttraction(attraction, tags);
    }


    public void deleteAttraction(String name) {
        repository.deleteAttraction(name);
    }
}