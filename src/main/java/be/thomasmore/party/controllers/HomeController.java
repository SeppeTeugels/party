package be.thomasmore.party.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({"/","/home"})
    public String home(Model model, Principal principal){
        final String loginName = (principal != null) ? principal.getName() : "NOBODY";
        logger.info("home - logged in: "+ loginName);
        model.addAttribute("loginname",loginName);
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
        String weekday = weekday();
        model.addAttribute("weekday",weekday);
        return "pay";
    }

    public String weekday(){
        String weekday = "Voor je het weet is het weekend! ";
        LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (day) {
            case FRIDAY:
                weekday = "Prettig weekend, je hebt het verdiend!";
            case SATURDAY:
                weekday = "Prettig weekend, je hebt het verdiend!";
            case SUNDAY:
                weekday = "Prettig weekend, je hebt het verdiend!";
        }
        return weekday;
    }
}
