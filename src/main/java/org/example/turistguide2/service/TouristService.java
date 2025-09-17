
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

    public TouristAttraction findAttractionByName(String name){
        return repository.findAttractionByName(name);

    }

    public TouristAttraction addAttraction(TouristAttraction attraction){
        return repository.addAttraction(attraction);
    }


        public TouristAttraction updateAttraction(TouristAttraction updatedAttraction){
            TouristAttraction existing = repository.findAttractionByName(updatedAttraction.getName());
            if (existing != null) {
                return repository.updateAttraction(updatedAttraction);
            }
            return null;
        }

        public TouristAttraction deleteAttraction (TouristAttraction attraction, boolean delete) {
            if (delete) {
                return repository.deleteAttraction(attraction);
            }
            return null;
        }
}