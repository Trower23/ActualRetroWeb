package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public String showVideogame(Model model, @PathVariable long id){
        model.addAttribute("videogame", prodholder.getVideogame(id));
        return "videogame";
    }
    @PostMapping("/products/consoles/sell")
    public String sellVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam Date date){
        prodholder.addProduct(new VDConsole(name, price, maxcon, date));
        return "saved_console";
    }
    @PostMapping("/products/videogames/sell")
    public String sellVideogame(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int pe, @RequestParam Date date, @RequestParam VDGenre genre){
        prodholder.addProduct(new Videogame(name, price, pe, date, genre));
        return "saved_videogame";
    }






}
