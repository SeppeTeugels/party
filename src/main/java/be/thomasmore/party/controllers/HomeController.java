package be.thomasmore.party.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){
        int myCalculatedValue = 34*62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        String myName = "Seppe Teugels";
        String myStreet = "Fazantenlaan 20";
        String myCity = "Kapelle-op-den-Bos";
        model.addAttribute("myName", myName);
        model.addAttribute("myStreet", myStreet);
        model.addAttribute("myCity", myCity);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model){
        LocalDate datenow = LocalDate.now();
        model.addAttribute("datenow",datenow);
        LocalDate date30 = datenow.plusDays(30);
        model.addAttribute("date30",date30);
        return "pay";
    }
}
