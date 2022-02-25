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
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
        }
        return "venuedetails";
    }

    @GetMapping({"/venuelist"})
    public String venuelist(Model model){
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues",allVenues);
        return "venuelist";
    }

    @GetMapping({"/venuelist/outdoor/{filter}","/venuelist/outdoor", "/venuelist/{filter}"})
    public String venuelistOutdoor(Model model,@PathVariable (required = false) String filter){
        if (filter == null){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        if (filter.equals("yes")){
            Iterable<Venue> allVenues = venueRepository.findByOutdoor(true);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("no")){
            Iterable<Venue> allVenues = venueRepository.findByOutdoor(false);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("all")){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        else {
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        return "venuelist";
    }
    @GetMapping({"/venuelist/indoor/{filter}","/venuelist/indoor", "/venuelist/{filter}"})
    public String venuelistIndoor(Model model,@PathVariable (required = false) String filter){
        if (filter == null){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        if (filter.equals("yes")){
            Iterable<Venue> allVenues = venueRepository.findByIndoor(true);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("no")){
            Iterable<Venue> allVenues = venueRepository.findByIndoor(false);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("all")){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        else {
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        return "venuelist";
    }
    @GetMapping({"/venuelist/size/{filter}","/venuelist/size", "/venuelist/{filter}"})
    public String venuelistSize(Model model,@PathVariable (required = false) String filter){
        if (filter == null){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        if (filter.equals("s")){
            Iterable<Venue> allVenues = venueRepository.findBySmaller(200);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("m")){
            Iterable<Venue> allVenues = venueRepository.findByCapacityBetween(200,600);
            model.addAttribute("venues",allVenues);
            return "venuelist";
        }
        else if(filter.equals("l")){
            Iterable<Venue> allVenues = venueRepository.findByBigger(600);
            model.addAttribute("venues",allVenues);
        }
        else if(filter.equals("all")){
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        else {
            Iterable<Venue> allVenues = venueRepository.findAll();
            model.addAttribute("venues",allVenues);
        }
        return "venuelist";
    }
}
