package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class VenueController {
    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";
        long allvenues = venueRepository.count();
        model.addAttribute("allvenues", allvenues);
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        venueFromDb.ifPresent(venue -> model.addAttribute("venue", venue));
        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/{filter}"})
    public String venuelist(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        long allvenuesconut = venueRepository.count();
        model.addAttribute("allvenuescount", allvenuesconut);
        boolean showFilter = false;
        if (filter == null) {
            model.addAttribute("showFilter", showFilter);
            return "venuelist";
        } else {
            return "venuelist";
        }
    }

    @GetMapping({"/venuelist/filter"})
    public String venueListWithFilter(Model model, @RequestParam(required = false) Integer minCapacity, @RequestParam(required = false) Integer maxCapacity) {
        logger.info(String.format("venueListWithFilter -- min=%d", minCapacity));
        logger.info(String.format("venueListWithFilter -- max=%d", maxCapacity));
        Iterable<Venue> venuesbetween = venueRepository.findByCapacityBetween(minCapacity,maxCapacity);
        Iterable<Venue> venuesbigger= venueRepository.findBybigger(minCapacity);
        Iterable<Venue> venuessmaller = venueRepository.findBySmaller(maxCapacity);
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        boolean showFilter = true;
        if(minCapacity == null && maxCapacity == null){
            model.addAttribute("venues", allVenues);
            model.addAttribute("showFilter", showFilter);
            long allvenuescount = venueRepository.count();
            model.addAttribute("amount",allvenuescount);
            return "venuelist";
        }else if (minCapacity != null && maxCapacity == null){
            model.addAttribute("showFilter", showFilter);
            model.addAttribute("venues",venuesbigger);
            int counter = 0 ;
            for ( Venue ven : venuesbigger){
                counter ++;
            }
            model.addAttribute("amount", counter);
            return "venuelist";
        }else if(minCapacity == null){
            model.addAttribute("showFilter", showFilter);
            model.addAttribute("venues",venuessmaller);
            int counter = 0 ;
            for ( Venue ven : venuessmaller){
                counter ++;
            }
            model.addAttribute("amount", counter);
            return "venuelist";
        }else {
            model.addAttribute("showFilter", showFilter);
            model.addAttribute("venues",venuesbetween);
            int counter = 0 ;
            for ( Venue ven : venuesbetween){
                counter ++;
            }
            model.addAttribute("amount", counter);
            return "venuelist";
        }
    }
}
