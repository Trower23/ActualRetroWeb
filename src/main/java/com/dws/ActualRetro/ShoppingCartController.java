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
    public String buyCartConsole( @RequestParam long id){
        VDConsole console = prodHolder.getConsole(id);
        testcart.addConsole(console);
        return "added_success";
    }
    @PostMapping("/product/buy/videogame/{id}")
    public String buyCartVideogame( @RequestParam long id){
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
        //Compra todos los produtos que haya añadido, por lo que tenemos que sustraer productos
        //de nuestro holder y borrar los productos que haya en el carrito
        //Puedo implementar un método nuevo que sea "deleteAll" para trabajar con el atributo
        //stock de los productos, o puedo hacer un bucle
        model.addAttribute("totalprod", testcart.getTotalProducts());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        model.addAttribute("videogames", testcart.getVideogameList());
        model.addAttribute("consoles", testcart.getConsoleList());
        for (int i = 0; i < testcart.getConsoleList().size(); i++){
            prodHolder.delete(testcart.getConsoleList().get(i));
            testcart.deleteConsole(testcart.getConsoleList().get(i));
        }
        //testcart.getConsoleList().clear();
        for (int i = 0; i < testcart.getVideogameList().size(); i++){
            prodHolder.delete(testcart.getVideogameList().get(i));
            testcart.deleteVideogame(testcart.getVideogameList().get(i));
        }
        //Cuidado, con clear el precio no se baja. Mejor borrar cada elemento en los for. Podrían mejorarse...
        //testcart.getVideogameList().clear();
        //Más adelante tendremos que volver a este método probablemente, para hacer el pago más realista
        return "payment";
    }




}
