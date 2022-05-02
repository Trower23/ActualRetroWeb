package com.dws.ActualRetro;

import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
public class ProductWebController {
    @Autowired
    VideogameService videogameService;
    @Autowired
    ConsoleService consoleService;
    @Autowired
    UserService userService;   //we need to create new methods here to register users and save them on our database.


    @GetMapping("/products/consoles")
    public String showVDConsoles(Model model) {
        List<VDConsole> vdConsoles = consoleService.consoleRepository.findAll();
        model.addAttribute("vdConsoles", vdConsoles);
        return "consoles";
    }

    @GetMapping("/products/videogames")
    public String showVideogames(Model model) {
        List<Videogame> videogames = videogameService.videogameRepository.findAll();
        model.addAttribute("videogames", videogames);
        return "videogames";
    }

    @GetMapping("/products/consoles/{id}")
    public String showVDConsole(Model model, @PathVariable long id) {
        model.addAttribute("vdConsole", consoleService.consoleRepository.findById(id));
        return "console";
    }

    @GetMapping("/products/videogames/{id}")
    public String showVideogame(Model model, @PathVariable long id) {
        model.addAttribute("videogame", videogameService.videogameRepository.findById(id));
        return "videogame";
    }

    //He quitado la parte de Date
    /*
    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String date, @RequestParam String description) {
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        String sanitizedDesc = Sanitizers.FORMATTING.sanitize(description);
        VDConsole console = new VDConsole(name, price, maxcon, newdate, sanitizedDesc);
        model.addAttribute("console", console);
        consoleService.consoleRepository.save(console);
        return "saved_console";
    }

    @PostMapping("/products/videogames/sell")
    public String addVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pegi, @RequestParam String date, @RequestParam String genre, @RequestParam String description) {
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDGenre gen = VDGenre.valueOf(genre);
        String sanitizedDesc = Sanitizers.FORMATTING.sanitize(description);
        Videogame videogame = new Videogame(name, price, pegi, newdate, gen, sanitizedDesc);
        model.addAttribute("videogame", videogame);
        videogameService.videogameRepository.save(videogame);

        return "saved_videogame";
    }*/
    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String description) {
        Date newdate = new Date();
        String sanitizedDesc = Sanitizers.FORMATTING.sanitize(description);
        VDConsole console = new VDConsole(name, price, maxcon, newdate, sanitizedDesc);
        model.addAttribute("console", console);
        consoleService.consoleRepository.save(console);
        return "saved_console";
    }

    @PostMapping("/products/videogames/sell")
    public String addVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pegi, @RequestParam String genre, @RequestParam String description) {
        Date newdate = new Date();
        VDGenre gen = VDGenre.valueOf(genre);
        String sanitizedDesc = Sanitizers.FORMATTING.sanitize(description);
        Videogame videogame = new Videogame(name, price, pegi, newdate, gen, sanitizedDesc);
        model.addAttribute("videogame", videogame);
        videogameService.videogameRepository.save(videogame);

        return "saved_videogame";
    }
    //-- Queries-- //

    @GetMapping("/filter/consoles/pricefilter/")
    public String getConsolesBetweenPrices(Model model, @RequestParam int pricemin, @RequestParam int pricemax) {
        model.addAttribute("vdConsoles", consoleService.consoleRepository.findConsoleBetweenPrices(pricemin, pricemax));
        return "consoles";
    }

    @GetMapping("/filter/videogames/pricefilter/")
    public String getVideogamesBetweenPrices(Model model, @RequestParam int pricemin, @RequestParam int pricemax) {
        model.addAttribute("videogames", videogameService.videogameRepository.findVideogameBetweenPrices(pricemin, pricemax));
        return "videogames";
    }

    @GetMapping("/filter/videogames/pegifilter/")
    public String getVideogamesWhichPegiIs(Model model, @RequestParam int pegi) {
        model.addAttribute("videogames", videogameService.videogameRepository.findGamesPegi(pegi));
        return "videogames";
    }

    @GetMapping("/filter/consoles/controllersfilter/")
    public String getConsolesWithControllers(Model model, @RequestParam int maxcon){
        model.addAttribute("vdConsoles", consoleService.consoleRepository.findConsoleWithControllers(maxcon));
        return "consoles";
    }


}