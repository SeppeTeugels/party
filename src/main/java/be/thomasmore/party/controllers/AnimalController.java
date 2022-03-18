package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping({"/animaldetail/{id}", "/animaldetail"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "animaldetail";
        long animals = animalRepository.count();
        model.addAttribute("animals", animals);
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        animalFromDb.ifPresent(animal -> model.addAttribute("animal", animal));
        return "animaldetail";
    }

    @GetMapping({"/animallist", "/animallist/{filter}"})
    public String venuelist(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Animal> allanimals = animalRepository.findAll();
        model.addAttribute("allanimals", allanimals);
        long allanimalscount = animalRepository.count();
        model.addAttribute("allvenuescount", allanimalscount);
        boolean showFilter = false;
        if (filter == null) {
            model.addAttribute("showFilter", showFilter);
            return "animallist";
        } else {
            return "animallist";
        }
    }


}
