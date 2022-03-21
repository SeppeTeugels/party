package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping ("/partyedit/{id}")
    public String partyedit(Model model, @PathVariable (required = false) Integer id){
        Iterable<Venue> allvenues = venueRepository.findAll();
        model.addAttribute("allvenues",allvenues);
        logger.info("partyEdit "+id);
        return "/admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyeditPost(Model model, @PathVariable (required = false) Integer id, @ModelAttribute("party") Party party){
        if (id == null) return "/admin/partyedit";
        logger.info("partyEditPost " + id + "-- new name= "+ party.getName());
            partyRepository.save(party);
        return "redirect:/partydetail/"+id;
    }

    @ModelAttribute("party")
    public Party findParty(@PathVariable (required = false) Integer id){
        logger.info("findParty"+ id);
        if(id ==null){
            return new Party();
        }
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()){
            return optionalParty.get();
        }


        return null;
    }
    @GetMapping ("/partynew")
    public String partynew(Model model){
        Iterable<Venue> allvenues = venueRepository.findAll();
        model.addAttribute("allvenues",allvenues);
        return "/admin/partynew";
    }

    @PostMapping("/partynew") public String partyNewPost(Model model, @ModelAttribute("party") Party party) {
        partyRepository.save(party);
        int id=partyRepository.save(party).getId();
        return "redirect:/partydetail/" + id;
    }

}
