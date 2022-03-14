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
        long parties = partyRepository.count();
        model.addAttribute("parties", parties);
        Optional<Party> partyFromDb = partyRepository.findById(id);
        partyFromDb.ifPresent(party -> model.addAttribute("party", party));
        return "partydetails";
    }

    @GetMapping({"/partylist", "/partylist/{filter}"})
    public String venuelist(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Party> allparties = partyRepository.findAll();
        model.addAttribute("parties", allparties);
        long allpartiescount = partyRepository.count();
        model.addAttribute("allvenuescount", allpartiescount);
        boolean showFilter = false;
        if (filter == null) {
            model.addAttribute("showFilter", showFilter);
            return "partylist";
        } else {
            return "partylist";
        }
    }
}
