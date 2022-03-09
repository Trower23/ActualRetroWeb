package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ProductWebController{
    @Autowired
    ProductHolder prodholder;

    @GetMapping("/products/consoles")
    public String showVDConsoles(Model model){
        List<VDConsole> vdConsoles= new ArrayList<>(prodholder.getConsoles());
        model.addAttribute("vdConsoles", vdConsoles);
        return "consoles";
    }
    @GetMapping("/products/videogames")
    public String showVideogames(Model model){
        List<Videogame> videogames= new ArrayList<>(prodholder.getVideogames());
        model.addAttribute("videogames", videogames);
        return "videogames";
    }
    @GetMapping("/products/consoles/{id}")
    public String showVDConsole(Model model, @PathVariable long id){
        model.addAttribute("vdConsole", prodholder.getConsole(id));
        return "console";
    }
    @GetMapping("/products/videogames/{id}")
    public String showVideogame(Model model, @PathVariable long id) {
        model.addAttribute("videogame", prodholder.getVideogame(id));
        return "videogame";
    }


    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String date){
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDConsole console = new VDConsole(name, price, maxcon, newdate);
        model.addAttribute(console);
        prodholder.addProduct(console);
        return "saved_console";
    }
    @PostMapping("/products/videogames/sell")
    public String addVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pegi, @RequestParam String date, @RequestParam String genre){
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDGenre gen = VDGenre.valueOf(genre);
        Videogame videogame = new Videogame(name, price, pegi, newdate, gen);
        model.addAttribute("videogame", videogame);
        prodholder.addProduct(videogame);
        return "saved_videogame";
    }


}
