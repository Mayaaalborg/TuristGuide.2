
package org.example.turistguide2.service;


import org.springframework.stereotype.Service;
import org.example.turistguide2.repository.TouristRepository;
import org.example.turistguide2.model.TouristAttraction;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;

    }

    public List<TouristAttraction> getAll() {
        return repository.getAll();
    }

    public TouristAttraction findAttractionByName(String name) {
        return repository.findAttractionByName(name);
    }

    public void addAttraction(TouristAttraction attraction) {
        repository.addAttraction(attraction);
    }

    public void updateAttraction(TouristAttraction attraction) {
        repository.updateAttraction(attraction);
    }

    public void deleteAttraction(String name) {
        repository.deleteAttraction(name);
    }
}