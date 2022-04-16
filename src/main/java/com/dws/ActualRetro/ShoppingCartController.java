package com.dws.ActualRetro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    ConsoleService consoleService;
    @Autowired
    VideogameService videogameService;

    //We dont have users yet, so user1 is a try user, to show functionality.

    @PostConstruct
    public void init(){
        Users user1= new Users("Juan", "Gonzalez", "juangon@gmail.com", "passJuord", "661665553");
        userService.userRepository.save(user1);
    }

    @GetMapping("/products/cart")
    public String showCart(Model model){
        ShoppingCart testcart = userService.userRepository.findById(1).getShoppingCart();
        model.addAttribute("consoles", testcart.getConsoleList());
        model.addAttribute("videogames", testcart.getVideogameList());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        return "cart_products";
    }

    @GetMapping("/product/buy/console/{id}")
    public String buyCartConsole( @PathVariable long id){
        ShoppingCart testcart = userService.userRepository.findById(1).getShoppingCart();
        VDConsole console = consoleService.consoleRepository.findById(id);
        testcart.addConsole(console);
        shoppingCartService.shoppingCartRepository.save(testcart);
        return "added_success";
    }
    @GetMapping("/product/buy/videogame/{id}")
    public String buyCartVideogame( @PathVariable long id){
        ShoppingCart testcart = userService.userRepository.findById(1).getShoppingCart();
        Videogame videogame = videogameService.videogameRepository.findById(id);
        testcart.addVideogame(videogame);
        shoppingCartService.shoppingCartRepository.save(testcart);
        return "added_success";
    }

    @GetMapping("/product/remove/console/{id}")
    public String removeCartConsole(Model model, @PathVariable long id){
        ShoppingCart testcart = userService.userRepository.findById(1).getShoppingCart();
        VDConsole console = consoleService.consoleRepository.findById(id);
        model.addAttribute("console", console);
        testcart.deleteConsole(consoleService.consoleRepository.findById(id));
        shoppingCartService.shoppingCartRepository.save(testcart);
        return "deleted_success_cart";
    }
    @GetMapping("/product/remove/videogame/{id}")
    public String removeCartVideoGame(Model model, @PathVariable long id){
        ShoppingCart testcart = userService.userRepository.findById(1).getShoppingCart();
        Videogame videogame = videogameService.videogameRepository.findById(id);
        model.addAttribute("videogame", videogame);
        testcart.deleteVideogame(videogame);
        shoppingCartService.shoppingCartRepository.save(testcart);
        return "deleted_success_cart";
    }
    @GetMapping("/product/buy")
    public String buyCart(Model model){
        Users user1 = userService.userRepository.findById(1);
        ShoppingCart testcart = user1.getShoppingCart();
        model.addAttribute("totalprod", testcart.getTotalProducts());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        List<VDConsole> auxconsolelist = new ArrayList<>();
        List<Videogame> auxvideogamelist = new ArrayList<>();
        int size = testcart.getConsoleList().size();
        for (int i = size - 1; i >= 0; i--) {
            VDConsole console = testcart.getConsoleList().get(i);
            VDConsole auxco = consoleService.consoleRepository.findById(console.getId());
            auxco.removeStock();    //Este método no borra la entrada cuando no hay Stock, no sabemos por qué
                                    //Con videojuegos pasa lo mismo
            if (!auxco.isStock()){
                consoleService.consoleRepository.delete(console);
            }
            auxconsolelist.add(console);
            user1.buyConsole(console);
            testcart.deleteConsole(console);
        }
        size = testcart.getVideogameList().size();
        for (int i = size - 1; i >= 0; i--){
            Videogame videogame = testcart.getVideogameList().get(i);
            Videogame auxvid = videogameService.videogameRepository.findById(videogame.getId());
            auxvid.removeStock();
            if (!auxvid.isStock()){
                videogameService.videogameRepository.delete(videogame);
            }
            auxvideogamelist.add(videogame);
            user1.buyVideogame(videogame);
            testcart.deleteVideogame(videogame);
        }
        model.addAttribute("consoles", auxconsolelist);
        model.addAttribute("videogames", auxvideogamelist);
        shoppingCartService.shoppingCartRepository.save(testcart);
        return "payment";
    }



}
