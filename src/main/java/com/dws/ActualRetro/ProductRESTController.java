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
        List<VDConsole> auxConsoles=new ArrayList<>(prodholder.getConsoles());
        return new ResponseEntity<>(auxConsoles, HttpStatus.OK);
    }
    @GetMapping("/products/games")
    public ResponseEntity<List<Videogame>> showGames(){
        List<Videogame> auxGames= new ArrayList<>(prodholder.getVideogames());
        return new ResponseEntity<>(auxGames, HttpStatus.OK);
    }
    @GetMapping("/products/consoles/{id}")
    public ResponseEntity<VDConsole> showConsole(@PathVariable long id){
        return new ResponseEntity<>(prodholder.getConsole(id), HttpStatus.OK);
    }
    @GetMapping("/products/games/{id}")
    public ResponseEntity<Videogame> showGame(@PathVariable long id){
        return new ResponseEntity<>(prodholder.getVideogame(id), HttpStatus.OK);
    }
    //@PostMapping("/products/consoles")
   // public ResponseEntity<VDConsole> addVConsole(){
     //
    //}

}
