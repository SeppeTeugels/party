package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Optional;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistdetails/{id}", "/artistdetails"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if(id == null) return "artistdetails";
        long allartists = artistRepository.count();
        model.addAttribute("allartists",allartists);
        Optional<Artist> ArtistFromDb = artistRepository.findById(id);
        ArtistFromDb.ifPresent(artist -> model.addAttribute("artist", artist));
        return "artistdetails";
    }
    @GetMapping({"/artistlist"})
    public String artistlist(Model model){
        final Iterable<Artist> allArtists = artistRepository.findAll();
        model.addAttribute("artists",allArtists);
        boolean showFilter = false;
        model.addAttribute("showFilter", showFilter);
        return "artistlist";
    }

    @GetMapping({"/artistlist/filter"})
    public String artistListWithFilter(Model model, @RequestParam(required = false) String keyword){
        logger.info(String.format("venueListWithFilter -- keyword=%s", keyword));
        final Iterable<Artist> allArtists = artistRepository.findAll();
        boolean showFilter = true;
        model.addAttribute("showFilter", showFilter);
        if (keyword != null){
            Iterable<Artist> filteredartist = artistRepository.findByTitleContainingIgnoreCase(keyword.toUpperCase());
            model.addAttribute("artists", filteredartist);
        }else{
            model.addAttribute("artists",allArtists);
        }
        return "artistlist";
    }

}
