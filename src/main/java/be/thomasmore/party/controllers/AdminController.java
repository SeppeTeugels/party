package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping ("/admin/partyedit")
    public String partyedit(Model model){
        return "/admin/partyedit";
    }
    @GetMapping ("/admin/partynew")
    public String partynew(Model model){
        return "/admin/partynew";
    }
}
