package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping ("/partyedit/{id}")
    public String partyedit(Model model, @PathVariable (required = false) Integer id){
        if (id == null) return "/admin/partyedit";

//        logger.info("venueListWithFilter -- id=%s", id);
        long parties = partyRepository.count();
        model.addAttribute("parties", parties);
        Optional<Party> partyFromDb = partyRepository.findById(id);
        partyFromDb.ifPresent(party -> model.addAttribute("party", party));

        return "/admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyeditPost(Model model, @PathVariable (required = false) Integer id, @ModelAttribute("party") Party party){
        if (id == null) return "/admin/partyedit";
        logger.info("partyEditPost " + id + "-- new name= "+ party.getName());
        Optional<Party> partyFromDb = partyRepository.findById(id);
        if(partyFromDb.isPresent()){
            Party editedParty = partyFromDb.get();
            editedParty.setName(party.getName());
            editedParty.setPricePresaleInEur(party.getPricePresaleInEur());
            editedParty.setPriceInEur(party.getPriceInEur());
            editedParty.setExtraInfo(party.getExtraInfo());
            partyRepository.save(editedParty);
            model.addAttribute("party", party);
        }

        return "redirect:/partydetail/"+id;
    }
    @GetMapping ("/partynew")
    public String partynew(Model model){
        return "/admin/partynew";
    }


}
