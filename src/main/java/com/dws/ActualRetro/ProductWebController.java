package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class ProductWebController {
    @Autowired
    VideogameService videogameService;
    @Autowired
    ConsoleService consoleService;
    @Autowired
    UserService userService;   //we need to create new methods here to register users and save them on our database.
    @PersistenceContext
    EntityManager entityManager;


    @GetMapping("/products/consoles")
    public String showVDConsoles(Model model) {
        //List<VDConsole> vdConsoles= new ArrayList<>(prodholder.getConsoles());
        List<VDConsole> vdConsoles = consoleService.consoleRepository.findAll();
        model.addAttribute("vdConsoles", vdConsoles);
        return "consoles";
    }

    @GetMapping("/products/videogames")
    public String showVideogames(Model model) {
        //List<Videogame> videogames= new ArrayList<>(prodholder.getVideogames());
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

    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String date, @RequestParam String description) {
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDConsole console = new VDConsole(name, price, maxcon, newdate, description);
        model.addAttribute("console", console);
        consoleService.consoleRepository.save(console);
        return "saved_console";
    }

    @PostMapping("/products/videogames/sell")
    public String addVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pegi, @RequestParam String date, @RequestParam String genre, @RequestParam String description) {
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDGenre gen = VDGenre.valueOf(genre);
        Videogame videogame = new Videogame(name, price, pegi, newdate, gen, description);
        model.addAttribute("videogame", videogame);
        videogameService.videogameRepository.save(videogame);
        return "saved_videogame";
    }
    //-- Queries-- //

    @GetMapping("/products/videogames/{pricemin}/{pricemax}")
    public List<Videogame> getGamesBetweenPrices(@PathVariable int pricemin, @PathVariable int pricemax) {
        TypedQuery<Videogame> query = entityManager.createQuery("SELECT v FROM Videogame v WHERE v.price BETWEEN :pricemin AND :pricemax", Videogame.class);
        return query.setParameter("pricemin", pricemin).setParameter("pricemax", pricemax).getResultList();
    }

}