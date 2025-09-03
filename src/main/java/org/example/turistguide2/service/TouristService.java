
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

    public TouristAttraction addAttraction(TouristAttraction attraction){
        return repository.addAttraction(attraction);
    }

    public TouristAttraction findAttractionByName(String name){
        TouristAttraction attraction = repository.findAttractionByName(name);
        return attraction;
    }

    public TouristAttraction deleteAttraction (TouristAttraction attraction, boolean delete) {
        if (delete) {
            return repository.deleteAttraction(attraction);
        }
        return null;
    }
    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction){
        return repository.updateAttraction(name , updatedAttraction);
    }

}
