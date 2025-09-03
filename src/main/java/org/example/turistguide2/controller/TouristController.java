package org.example.turistguide2.controller;

import org.example.turistguide2.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.example.turistguide2.service.TouristService;
import org.example.turistguide2.repository.TouristRepository;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attraction")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<TouristAttraction> attractions = service.getAll();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    @GetMapping("/{name}")
    public String findAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("name", name);
        model.addAttribute("attraction", attraction);
        return "findAttractionByName";
    }

    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("/add")
    public String addAttractionsForm(Model model) {
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        return "addAttraction";
    }

    @PostMapping("/save")
    public String add(@ModelAttribute TouristAttraction attraction) {
        service.addAttraction(attraction);
        return "redirect:/attraction/all";
    }

    /*
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction savedAttraction = service.addAttraction(attraction);
        return new ResponseEntity<>(savedAttraction, HttpStatus.CREATED);
    }*/
    @PostMapping("/delete/{name}")
    public String delete (@ModelAttribute TouristAttraction attraction) {
        service.deleteAttraction(attraction, true);
        return "redirect:/attraction/all";
    }

    /*
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction deleted = service.deleteAttraction(attraction, true);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
    */
    /*@GetMapping("/{name}/edit")
    public String editAttractions(@PathVariable String name, Model model) {
    TouristAttraction attraction = service.findAttractionByName(name);

    return "updateAttraction";

      }  */

    @PostMapping("/update/{name}")
    public ResponseEntity<TouristAttraction> updatedAttraction(@PathVariable String name,
                                                               @RequestBody TouristAttraction updatedAttraction) {
        TouristAttraction attraction = service.updateAttraction(name, updatedAttraction);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }
}

