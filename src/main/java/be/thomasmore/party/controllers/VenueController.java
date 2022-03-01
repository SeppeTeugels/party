package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable (required = false) Integer id) {
        if(id == null) return "venuedetails";
        long allvenues = venueRepository.count();
        model.addAttribute("allvenues",allvenues);
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        venueFromDb.ifPresent(venue -> model.addAttribute("venue", venue));
        return "venuedetails";
    }

    @GetMapping({"/venuelist" , "/venuelist/{filter}"})
    public String venuelist(Model model, @PathVariable (required = false) String filter){
        final Iterable<Venue> allVenues = venueRepository.findAll();
        Boolean showFilter = false;
        if(filter == null){
            model.addAttribute("showFilter",showFilter);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("filter")){
            model.addAttribute("venues",allVenues);
            showFilter = true;
            model.addAttribute("showFilter",showFilter);
            return "venuelist";
        }
        model.addAttribute("showFilter",showFilter);
        model.addAttribute("venues",allVenues);
        return "venuelist";
    }
}
