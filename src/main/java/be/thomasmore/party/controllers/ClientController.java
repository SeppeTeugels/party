package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Controller
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clientdetails")
    public String clientController(Model model) {
        Optional<Client> ClientFromDb = clientRepository.findById(1);
        if (ClientFromDb.isPresent()) {
            model.addAttribute("client", ClientFromDb.get());
        }
        return "clientdetails";
    }


    public String getmessage(Client client) {
        String s = "";
        Date date = new Date();
        int hours = date.getHours();
        if (hours <= 12) {
            s += "Good Morning";
        } else if (hours <= 17) {
            s += "Good Afternoon";
        } else {
            s += "Good Night";
        }
        String[] name = client.getName().split(" ");
        if (client.getGender().equals("M")) {
            s += " mister " + name[1];
        }
        if (client.getGender().equals("F")) {
            s += " misses " + name[1];
        }
        return s;
    }

    public String showSecretCode(Client client) {
        Random random = new Random();
        String s = "";
        String[] name = client.getName().split(" ");
        if (client.getGender().equals("M")) {
            s += " mister " + name[1] + ", ";
        }
        if (client.getGender().equals("F")) {
            s += " misses " + name[1] + ", ";
        }
        s += "ur SecretCode is: ";
        String geboortejaar = client.getBirthdate().replaceAll("-","");
        int digitcode = random.nextInt(100000,999999);
        s += ""+name[1].charAt(0)+name[1].charAt(1)+name[1].charAt(name[1].length()-1)+digitcode;
        return s;
    }

    @GetMapping("/greetingNewClient")
    public String greetingNewClient(Model model) {
        Optional<Client> client = clientRepository.findById(1);
        if (client.isPresent()) {
            Client clientdetails = client.get();
            String message = getmessage(clientdetails);
            String secretcode = showSecretCode(clientdetails);
            model.addAttribute("message", message);
            model.addAttribute("secretcode", secretcode);
        }
        return "greetingNewClient";
    }

}
