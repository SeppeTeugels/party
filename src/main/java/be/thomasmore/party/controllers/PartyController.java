package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/partydetail/{id}", "/partydetail"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";
        long allvenues = partyRepository.count();
        model.addAttribute("allvenues", allvenues);
        Optional<Party> partyFromDb = partyRepository.findById(id);
        partyFromDb.ifPresent(venue -> model.addAttribute("venue", venue));
        return "partydetails";
    }
}
