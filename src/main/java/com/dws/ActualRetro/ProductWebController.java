package com.dws.ActualRetro;

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
    @Autowired
    @PersistenceContext
    EntityManager entityManager; //Deberíamos usar el entityManager


    @GetMapping("/products/consoles")
    public String showVDConsoles(Model model) {
        //List<VDConsole> vdConsoles= new ArrayList<>(prodholder.getConsoles());
        List<VDConsole> vdConsoles = consoleService.consoleRepository.findAll();
        /*
        TypedQuery<VDConsole> q = entityManager.createQuery("SELECT c FROM vdconsole c", VDConsole.class);
        List<VDConsole> vdConsoles = q.getResultList();
        */
        model.addAttribute("vdConsoles", vdConsoles);
        return "consoles";
    }

    @GetMapping("/products/videogames")
    public String showVideogames(Model model) {
        //List<Videogame> videogames= new ArrayList<>(prodholder.getVideogames());
        List<Videogame> videogames = videogameService.videogameRepository.findAll();
        /*
        TypedQuery<Videogame> q = entityManager.createQuery("SELECT c FROM videogame c", Videogame.class);
        List<Videogame> videogames = q.getResultList();
        */
        model.addAttribute("videogames", videogames);
        return "videogames";
    }

    @GetMapping("/products/consoles/{id}")
    public String showVDConsole(Model model, @PathVariable long id) {
        model.addAttribute("vdConsole", consoleService.consoleRepository.findById(id));
        /*
        TypedQuery<VDConsole> q = entityManager.createQuery("SELECT c FROM vdconsole WHERE ID = :id", VDConsole.class);
        q.setParameter("id", id);
        model.addAttribute("vdConsole", q.getSingleResult());
        */
        return "console";
    }

    @GetMapping("/products/videogames/{id}")
    public String showVideogame(Model model, @PathVariable long id) {
        model.addAttribute("videogame", videogameService.videogameRepository.findById(id));
        /*
        TypedQuery<Videogame> q = entityManager.createQuery("SELECT c FROM videogame WHERE ID = :id", Videogame.class);
        q.setParameter("id", id);
        model.addAttribute("videogame", q.getSingleResult());
        */
        return "videogame";
    }

    @PostMapping("/products/consoles/sell")
    public String addVDConsole(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int maxcon, @RequestParam String date, @RequestParam String description) {
        Date newdate = new Date();
        newdate.parseDate(date, "-");
        VDConsole console = new VDConsole(name, price, maxcon, newdate, description);
        model.addAttribute("console", console);
        consoleService.consoleRepository.save(console);
        /*
        TypedQuery<VDConsole> q = entityManager.createQuery("INSERT INTO vdconsole (DESCRIPTION, MAXCONTROLLERS, NAME, PRICE)"
        + "VALUES (" + description + ", " + maxcon + ", " + name + ", " + price + ")", VDConsole.class);
        O algo parecido, habría que parametrizarlo*/
        //No hay que añadir control de Stock? Comparar si el juego es el mismo por el nombre, y demás
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
        /*
        TypedQuery<Videogame> q = entityManager.createQuery("INSERT INTO vdconsole (DESCRIPTION, GENRE, NAME, PEGI, PRICE)"
        + "VALUES (" + description + ", " + gen.ordinal() + ", " + name + ", " + pegi + ", " + price + ")", Videogame.class);
        */
        //Aquí lo mismo con el Stock. Entiendo que quizá las Querys de estos métodos estén mal, ya que no especifico ni
        //el campo Stock ni el campo ID
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