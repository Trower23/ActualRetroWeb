package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ProductWebController{
    @Autowired
    VideoconsoleService videoconsoleService;
    @Autowired
    VideogameService videogameService;

    @GetMapping("/products/consoles")
    public String showVDConsoles(Model model){
        //List<VDConsole> vdConsoles= new ArrayList<>(prodholder.getConsoles());
        List<VDConsole> vdConsoles=videoconsoleService.consoleRepository.findAll();
        model.addAttribute("vdConsoles", vdConsoles);
        return "consoles";
    }
    @GetMapping("/products/videogames")
    public String showVideogames(Model model){
        //List<Videogame> videogames= new ArrayList<>(prodholder.getVideogames());
        List<Videogame> videogames= videogameService.videogameRepository.findAll();
        model.addAttribute("videogames", videogames);
        return "videogames";
    }
    @GetMapping("/products/consoles/{id}")
    public String showVDConsole(Model model, @PathVariable long id){
        model.addAttribute("vdConsole",videoconsoleService.consoleRepository.getById(id));
        return "console";
    }
    @GetMapping("/products/videogames/{id}")
    public String showVideogame(Model model, @PathVariable long id) {
        model.addAttribute("videogame", videogameService.videogameRepository.getById(id));
        return "videogame";
    }

    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String date, @RequestParam String description){
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDConsole console = new VDConsole(name, price, maxcon, newdate, description);
        model.addAttribute("console",console);
        videoconsoleService.consoleRepository.save(console);
        return "saved_console";
    }
    @PostMapping("/products/videogames/sell")
    public String addVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pegi, @RequestParam String date, @RequestParam String genre, @RequestParam String description){
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDGenre gen = VDGenre.valueOf(genre);
        Videogame videogame = new Videogame(name, price, pegi, newdate, gen, description);
        model.addAttribute("videogame", videogame);
        videogameService.videogameRepository.save(videogame);
        return "saved_videogame";
    }


}
