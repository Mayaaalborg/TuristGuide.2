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

    //Diregere til attractions html, og henter kode som bruges til at hente attractions i et forloop
    @GetMapping("/all")
    public String getAll(Model model) {
        List<TouristAttraction> attractions = service.getAll();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    //Henter attraction ved navn
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

    //Tilføjer ny attraction
    @GetMapping("/add")
    public String addAttractionsForm(Model model) {
        //Laver nyt attraction objekt, og oversætter variabler til HTML sprog
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        model.addAttribute("Tags", Tags.values());
        model.addAttribute("cities", Cities.values());
        return "addAttraction";
    }

    //Sender attributer tilbage til koden, og gemmer dem som object
    @PostMapping("/save")
    public String add(@ModelAttribute TouristAttraction attraction) {
        service.addAttraction(attraction);
        return "redirect:/attraction/all";
    }

    //Redigere en attraction ved navn
    @GetMapping("/{name}/edit")
    public String editAttractions(@PathVariable String name, Model model) {
        //Laver nyt attraction objekt, og oversætter variabler til HTML sprog
        TouristAttraction attraction = service.findAttractionByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("Tags", Tags.values());
        model.addAttribute("cities", Cities.values());
        return "editForm";
    }

    @PostMapping("/update/{name}")
    public String updateAttraction(@ModelAttribute TouristAttraction form) {
        service.updateAttraction(form);
        return "redirect:/attraction/" + form.getName();
    }

    @PostMapping("/{name}/delete")
    public String delete(@ModelAttribute TouristAttraction attraction) {
        service.deleteAttraction(attraction, true);
        return "redirect:/attraction/all";
    }
}

