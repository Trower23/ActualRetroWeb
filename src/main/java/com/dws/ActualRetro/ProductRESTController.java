package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductRESTController{
    @Autowired
    ProductHolder prodholder;
    @GetMapping("/products/consoles")
    public ResponseEntity<List<VDConsole>> showConsoles(){
        if (!prodholder.getConsoles().isEmpty()) {
            List<VDConsole> auxConsoles = new ArrayList<>(prodholder.getConsoles());
            return new ResponseEntity<>(auxConsoles, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/games")
    public ResponseEntity<List<Videogame>> showGames(){
        if (!prodholder.getVideogames().isEmpty()) {
            List<Videogame> auxGames = new ArrayList<>(prodholder.getVideogames());
            return new ResponseEntity<>(auxGames, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> showConsole(@PathVariable long id){
        if (prodholder.containsConsole(id)){
            return new ResponseEntity<>(prodholder.getConsole(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/products/games/{id}")
    public ResponseEntity<Videogame> showGame(@PathVariable long id){
        if (prodholder.containsConsole(id)) {
            return new ResponseEntity<>(prodholder.getVideogame(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/products/consoles")
    public ResponseEntity<VDConsole> addVConsole(@RequestBody VDConsole vdConsole){
        prodholder.addProduct(vdConsole);
        return new ResponseEntity<>(vdConsole, HttpStatus.CREATED);
    }
    @PostMapping("/products/games")
    public ResponseEntity<Videogame> addVideogame(@RequestBody Videogame videogame){
        prodholder.addProduct(videogame);
        return new ResponseEntity<>(videogame, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> deleteVDConsole(@PathVariable long id){
        if (prodholder.containsConsole(id)) {
            VDConsole vdConsole= prodholder.delete(prodholder.getConsole(id));
            return new ResponseEntity<>(vdConsole, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/products/games/{id}")
    public ResponseEntity<Videogame> deleteVideogame(@PathVariable long id){
        if (prodholder.containsVideoame(id)) {
            Videogame videogame= prodholder.delete(prodholder.getVideogame(id));
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
