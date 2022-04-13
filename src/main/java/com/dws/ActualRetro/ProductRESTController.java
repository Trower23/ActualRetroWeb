package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRESTController{
    /*@Autowired
    ProductHolder prodholder;*/

    @Autowired
    VideogameService videogameService;
    @Autowired
    VideoconsoleService videoconsoleService;
    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
        Users user1= new Users("Juan", "Gonzalez", "juangon@gmail.com", "passJuord", "661665553");
        Videogame videogame= new Videogame("Mario bross", 10, 18, new Date(10,4,2002), VDGenre.ACTION, "description");
        VDConsole console= new VDConsole("XBOX 360", 10, 2, new Date(10,10,2002), "desc");
        userService.userRepository.save(user1);
        videogameService.videogameRepository.save(videogame);
        videoconsoleService.consoleRepository.save(console);
        user1.getShoppingCart().addVideogame(videogame);
        /*user1.getShoppingCart().addConsole(console);*/

    }


    @GetMapping("/products/consoles")
    public ResponseEntity<List<VDConsole>> showConsoles(){
        if (!videoconsoleService.consoleRepository.findAll().isEmpty()) {
            List<VDConsole> auxConsoles = new ArrayList<>(videoconsoleService.consoleRepository.findAll());
            return new ResponseEntity<>(auxConsoles, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/games")
    public ResponseEntity<List<Videogame>> showGames(){
        if (!videogameService.videogameRepository.findAll().isEmpty()) {
            List<Videogame> auxGames = new ArrayList<>(videogameService.videogameRepository.findAll());
            return new ResponseEntity<>(auxGames, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> showConsole(@PathVariable long id){
        if (videoconsoleService.consoleRepository.existsById(id)){
            return new ResponseEntity<>(videoconsoleService.consoleRepository.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/products/games/{id}")
    public ResponseEntity<Videogame> showGame(@PathVariable long id){
        if (videogameService.videogameRepository.existsById(id)) {
            return new ResponseEntity<>(videogameService.videogameRepository.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products/consoles")
    public ResponseEntity<VDConsole> addVConsole(@RequestBody VDConsole vdConsole){
        List<VDConsole> consoles = videoconsoleService.consoleRepository.findAll();
        if (consoles.contains(vdConsole)){
            VDConsole newcon = consoles.get(consoles.indexOf(vdConsole));
            newcon.addStock();
            videoconsoleService.consoleRepository.save(newcon);
            return new ResponseEntity<>(newcon, HttpStatus.CREATED);
        }else{
            videoconsoleService.consoleRepository.save(vdConsole);
            return new ResponseEntity<>(vdConsole, HttpStatus.CREATED);
        }
    }

    @PostMapping("/products/games")
    public ResponseEntity<Videogame> addVideogame(@RequestBody Videogame videogame){
        List<Videogame> games = videogameService.videogameRepository.findAll();
        if (games.contains(videogame)){
            Videogame newgame = games.get(games.indexOf(videogame));
            newgame.addStock();
            videogameService.videogameRepository.save(newgame);
            return new ResponseEntity<>(newgame, HttpStatus.CREATED);
        }else{
            videogameService.videogameRepository.save(videogame);
            return new ResponseEntity<>(videogame, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> deleteVDConsole(@PathVariable long id){
        if (videoconsoleService.consoleRepository.existsById(id)) {
            VDConsole vdConsole =videoconsoleService.consoleRepository.findById(id);
            vdConsole.removeStock();
            videoconsoleService.consoleRepository.save(vdConsole);
            if (!vdConsole.isStock()) {
                videoconsoleService.consoleRepository.delete(vdConsole);
            }
            return new ResponseEntity<>(vdConsole, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/products/games/{id}")
    public ResponseEntity<Videogame> deleteVideogame(@PathVariable long id){
        if (videogameService.videogameRepository.existsById(id)) {
            Videogame videogame = videogameService.videogameRepository.findById(id);
            videogame.removeStock();
            videogameService.videogameRepository.save(videogame);
            if (!videogame.isStock()) {
                videogameService.videogameRepository.delete(videogame);
            }
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> putVDConsole(@PathVariable long id, @RequestBody VDConsole vdConsole){
        if (videoconsoleService.consoleRepository.existsById(id)){
            vdConsole.setId(id);
            vdConsole.setStock(videoconsoleService.consoleRepository.getById(id).getStock());
            videoconsoleService.consoleRepository.deleteById(id);
            videoconsoleService.consoleRepository.save(vdConsole);
            return new ResponseEntity<>(vdConsole, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/products/games/{id}")
    public ResponseEntity<Videogame> putVideogame(@PathVariable long id, @RequestBody Videogame videogame){
        if (videogameService.videogameRepository.existsById(id)){
            videogame.setId(id);
            videogame.setStock(videogameService.videogameRepository.getById(id).getStock());
            videogameService.videogameRepository.save(videogame);
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
