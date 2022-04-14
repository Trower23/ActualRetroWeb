package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    ConsoleService consoleService;
    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
        Videogame videogame= new Videogame("Super Mario Brothers", 150, 0, new Date(13,9,1985), VDGenre.PLATFORMS, "Original Super Mario Bros for NES");
        VDConsole console= new VDConsole("XBOX 360", 85, 4, new Date(22,11,2005), "Consola de séptima generación creada por Microsoft");
        videogameService.videogameRepository.save(videogame);
        consoleService.consoleRepository.save(console);
    }


    @GetMapping("/products/consoles")
    public ResponseEntity<List<VDConsole>> showConsoles(){
        if (!consoleService.consoleRepository.findAll().isEmpty()) {
            List<VDConsole> auxConsoles = new ArrayList<>(consoleService.consoleRepository.findAll());
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
        if (consoleService.consoleRepository.existsById(id)){
            return new ResponseEntity<>(consoleService.consoleRepository.findById(id), HttpStatus.OK);
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
        List<VDConsole> consoles = consoleService.consoleRepository.findAll();
        if (consoles.contains(vdConsole)){
            VDConsole newcon = consoles.get(consoles.indexOf(vdConsole));
            newcon.addStock();
            consoleService.consoleRepository.save(newcon);
            return new ResponseEntity<>(newcon, HttpStatus.CREATED);
        }else{
            consoleService.consoleRepository.save(vdConsole);
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
        if (consoleService.consoleRepository.existsById(id)) {
            VDConsole vdConsole = consoleService.consoleRepository.findById(id);
            vdConsole.removeStock();
            consoleService.consoleRepository.save(vdConsole);
            if (!vdConsole.isStock()) {
                consoleService.consoleRepository.delete(vdConsole);
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
        if (consoleService.consoleRepository.existsById(id)){
            vdConsole.setId(id);
            vdConsole.setStock(consoleService.consoleRepository.findById(id).getStock());
            consoleService.consoleRepository.deleteById(id);
            consoleService.consoleRepository.save(vdConsole);
            return new ResponseEntity<>(vdConsole, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/products/games/{id}")
    public ResponseEntity<Videogame> putVideogame(@PathVariable long id, @RequestBody Videogame videogame){
        if (videogameService.videogameRepository.existsById(id)){
            videogame.setId(id);
            videogame.setStock(videogameService.videogameRepository.findById(id).getStock());
            videogameService.videogameRepository.save(videogame);
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
