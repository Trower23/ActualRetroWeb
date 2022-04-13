package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    //@Autowired
    //ProductHolder prodHolder;
    //CartService cartService;

    ShoppingCart testcart = new ShoppingCart();   //De momento no tenemos usuarios (Fase III) para el carro.

   /*@GetMapping("/products/cart")
    public String showCart(Model model){
        model.addAttribute("consoles", testcart.getConsoleList());
        model.addAttribute("videogames", testcart.getVideogameList());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        return "cart_products";
    }

    @GetMapping("/product/buy/console/{id}")
    public String buyCartConsole( @PathVariable long id){
        VDConsole console = prodHolder.getConsole(id);
        testcart.addConsole(console);
        return "added_success";
    }
    @GetMapping("/product/buy/videogame/{id}")
    public String buyCartVideogame( @PathVariable long id){
        Videogame videogame = prodHolder.getVideogame(id);
        testcart.addVideogame(videogame);
        return "added_success";
    }

    @GetMapping("/product/remove/console/{id}")
    public String removeCartConsole(Model model, @PathVariable long id){
        model.addAttribute("console",prodHolder.getConsole(id));
        testcart.deleteConsole(prodHolder.getConsole(id));
        return "deleted_success_cart";
    }
    @GetMapping("/product/remove/videogame/{id}")
    public String removeCartVideoGame(Model model, @PathVariable long id){
        model.addAttribute("videogame",prodHolder.getVideogame(id));
        testcart.deleteVideogame(prodHolder.getVideogame(id));
        return "deleted_success_cart";
    }
    @GetMapping("/product/buy")
    public String buyCart(Model model){
        model.addAttribute("totalprod", testcart.getTotalProducts());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        List<VDConsole> auxconsolelist = new ArrayList<>();
        List<Videogame> auxvideogamelist = new ArrayList<>();
        int size = testcart.getConsoleList().size();
        for (int i = size - 1; i >= 0; i--){
            VDConsole console = testcart.getConsoleList().get(i);
            prodHolder.delete(console);
            auxconsolelist.add(console);
            testcart.deleteConsole(console);
        }
        size = testcart.getVideogameList().size();
        for (int i = size - 1; i >= 0; i--){
            Videogame videogame = testcart.getVideogameList().get(i);
            prodHolder.delete(videogame);
            auxvideogamelist.add(videogame);
            testcart.deleteVideogame(videogame);
        }
        model.addAttribute("consoles", auxconsolelist);
        model.addAttribute("videogames", auxvideogamelist);
        return "payment";
    }



*/
}
