package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(videoconsoleService.consoleRepository.getById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/products/games/{id}")
    public ResponseEntity<Videogame> showGame(@PathVariable long id){
        if (videogameService.videogameRepository.existsById(id)) {
            return new ResponseEntity<>(videogameService.videogameRepository.getById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products/consoles")
    public ResponseEntity<VDConsole> addVConsole(@RequestBody VDConsole vdConsole){
        if (videoconsoleService.consoleRepository.findAll().contains(vdConsole)){
            videoconsoleService.consoleRepository.getById(vdConsole.getId()).addStock();
        }
        videoconsoleService.consoleRepository.save(vdConsole);
        return new ResponseEntity<>(vdConsole, HttpStatus.CREATED);
    }

    @PostMapping("/products/games")
    public ResponseEntity<Videogame> addVideogame(@RequestBody Videogame videogame){
        if (videogameService.videogameRepository.findAll().contains(videogame)){
            videogameService.videogameRepository.getById(videogame.getId()).addStock();
        }
        videogameService.videogameRepository.save(videogame);
        return new ResponseEntity<>(videogame, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> deleteVDConsole(@PathVariable long id){
        if (videoconsoleService.consoleRepository.existsById(id)) {
            VDConsole vdConsole =videoconsoleService.consoleRepository.getById(id);
            videoconsoleService.consoleRepository.deleteById(id);
            return new ResponseEntity<>(vdConsole, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/products/games/{id}")
    public ResponseEntity<Videogame> deleteVideogame(@PathVariable long id){
        if (videogameService.videogameRepository.existsById(id)) {
            Videogame videogame = videogameService.videogameRepository.getById(id);
            videogameService.videogameRepository.deleteById(id);
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
