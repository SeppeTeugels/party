package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String venueListWithFilter(Model model, @RequestParam(required = false) Integer minCapacity, @RequestParam(required = false) Integer maxCapacity, @RequestParam(required = false) Double maxKm, @RequestParam(required = false) String filterFood, @RequestParam(required = false) String filterIndoor, @RequestParam(required = false) String filterOutdoor) {
        logger.info(String.format("venueListWithFilter -- min=%d", minCapacity));
        model.addAttribute("minCapacity", minCapacity);
        logger.info(String.format("venueListWithFilter -- max=%d", maxCapacity));
        model.addAttribute("maxCapacity", maxCapacity);
        logger.info(String.format("venueListWithFilter -- maxKm=%f", maxKm));
        model.addAttribute("maxKm", maxKm);
        logger.info(String.format("venueListWithFilter -- maxfood=%s", filterFood));

        Boolean foodFilterBoolean = null;


        if (filterFood != null) {
            if (filterFood.equals("yes")) {
                foodFilterBoolean = true;
            } else if (filterFood.equals("no")) {
                foodFilterBoolean = false;
            }
        }
        Boolean indoorFilterBoolean = null;
        if (filterFood != null) {
            if (filterFood.equals("yes")) {
                indoorFilterBoolean = true;
            } else if (filterFood.equals("no")) {
                indoorFilterBoolean = false;
            }
        }

        Boolean outdoorFilterBoolean = null;
        if (filterOutdoor != null) {
            if (filterOutdoor.equals("yes")) {
                outdoorFilterBoolean = true;
            } else if (filterOutdoor.equals("no")) {
                outdoorFilterBoolean = false;
            }
        }
        Iterable<Venue> venuesfiltered = venueRepository.findByFilterContainingIgnoreCase(minCapacity, maxCapacity, maxKm, foodFilterBoolean, indoorFilterBoolean, outdoorFilterBoolean);

        final Iterable<Venue> allVenues = venueRepository.findAll();
        boolean showFilter = true;
        int counter = 0;
        for (Venue ven : venuesfiltered) {
            counter++;
        }
        model.addAttribute("showFilter", showFilter);
        long allvenuescount = venueRepository.count();
        model.addAttribute("amount", counter);
        return "venuelist";
    }
}
