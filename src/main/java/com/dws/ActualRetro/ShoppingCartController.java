package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingCartController {
    @Autowired
    ProductHolder prodHolder;
    ShoppingCart testcart = new ShoppingCart();

    @GetMapping("/products/cart")
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
        model.addAttribute("videogame",prodHolder.getConsole(id));
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
        model.addAttribute("videogames", testcart.getVideogameList());
        model.addAttribute("consoles", testcart.getConsoleList());
        for (int i = 0; i < testcart.getConsoleList().size(); i++){
            prodHolder.delete(testcart.getConsoleList().get(i));
            testcart.deleteConsole(testcart.getConsoleList().get(i));
        }
        for (int i = 0; i < testcart.getVideogameList().size(); i++){
            prodHolder.delete(testcart.getVideogameList().get(i));
            testcart.deleteVideogame(testcart.getVideogameList().get(i));
        }
        return "payment";
    }




}
