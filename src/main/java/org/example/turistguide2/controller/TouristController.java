package org.example.turistguide2.controller;

import org.example.turistguide2.model.Cities;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller klasse
@Controller
//Request mapping (URL som vores mappinger tilgåes igennem)
@RequestMapping("attraction")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    //Viser alle attractions på /attraction/all
    @GetMapping("/all")
    public String getAll(Model model) {
        List<TouristAttraction> attractions = service.getAll();
        model.addAttribute("attractions", attractions);
        return "attractions"; // Returnerer attractions.html
    }

    //Henter attraction ved navn
    @GetMapping("/{name}")
    public String findAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("name", name);
        model.addAttribute("attraction", attraction);
        return "findAttractionByName";
    }

    // Viser tags for en bestemt attraction
    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    //Formular til tilføjelse af nye attractions
    @GetMapping("/add")
    public String addAttractionsForm(Model model) {
        //Laver nyt attraction objekt, og oversætter variabler til HTML sprog
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", Tags.values()); // Sender tags til checkbox
        model.addAttribute("cities", Cities.values()); // Sender cities til dropdown
        return "addAttraction";
    }

    // Gemmer ny attraction
    @PostMapping("/save")
    public String add(@ModelAttribute TouristAttraction attraction,
                      @RequestParam(value = "tagIds", required = false) List<Integer> tagIds,
                      Model model) {

        TouristAttraction existing = service.findAttractionByName(attraction.getName());

        // Tjekker om en attraktion med samme navn allerede findes.
        // Hvis ja, vises en fejlbesked og formularen indlæses igen med tomt attraction-objekt og dropdown-data.
        if (existing != null) {
            model.addAttribute("errorMessage", "Der findes allerede en attraktion med dette navn.");
            // Tilføjer et tomt attraction-objekt, så Thymeleaf stadig har noget at binde felterne til
            model.addAttribute("attraction", new TouristAttraction());
            // Tilføjer listen af tags og cities
            model.addAttribute("tags", Tags.values());
            model.addAttribute("cities", Cities.values());
            return "addAttraction"; // Gå tilbage til formularen med fejlbesked
        }


        // Laver liste af tag-Id'er om fra HTML til tag objekter
        List<Tags> tags = null;
        if (tagIds != null && !tagIds.isEmpty()) {
            tags = tagIds.stream()
                    .map(Tags::fromId)
                    .filter(t -> t != null)
                    .toList();
        }

        service.addAttraction(attraction, tags);
        return "redirect:/attraction/all"; // Går tilbage til listen efter gem
    }

    //Viser formular til redigering af attractions
    @GetMapping("/{name}/edit")
    public String editAttractions(@PathVariable String name, Model model) {
        //Laver nyt attraction objekt, og oversætter variabler til HTML sprog
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", Tags.values());
        model.addAttribute("cities", Cities.values());
        return "editForm";
    }

    // Opdatere attractions med ændingerne
    @PostMapping("/update/{name}")
    public String updateAttraction(@PathVariable String name,
                                   @ModelAttribute TouristAttraction form,
                                   @RequestParam(value = "tagIds", required = false) List<Integer> tagIds) {

        List<Tags> tags = null;
        if (tagIds != null && !tagIds.isEmpty()) {
            tags = tagIds.stream()
                    .map(Tags::fromId)
                    .filter(t -> t != null)
                    .toList();
        }
        service.updateAttraction(form, tags);
        return "redirect:/attraction/" + name; // Viser den opdaterede attraction
    }

    // Sletter attraction ud fra navn
    @PostMapping("/{name}/delete")
    public String delete(@PathVariable String name) {
        service.deleteAttraction(name);
        return "redirect:/attraction/all";
    }
}
