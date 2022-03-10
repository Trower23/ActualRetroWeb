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
    //Tenemos que pasarle a este controller, de alguna forma, el usuario, con su carrito.
    //Hay que guardar a los usuarios en algún lado... hacemos un UserHolder? Y... esto nos
    //llevaría a hacer un ShoppingCartRESTController?
    ShoppingCart testcart = new ShoppingCart();


    @GetMapping("/products/cart")
    //En verdad, debería ser algo como @GetMapping("/products/cart/{user}) y le pasamos un
    //identificador de user. Todos los métodos tendremos que añadirles eso más adelante
    public String showCart(Model model){
        model.addAttribute("consoles", testcart.getConsoleList());
        model.addAttribute("videogames", testcart.getVideogameList());
        model.addAttribute("totalprice", testcart.getTotalPrice());
        return "cart_products";
    }

    //Para añadir cosas al carrito, primero lo tendremos que tener disponible, es decir,
    //añadido al productHolder
    @PostMapping("/product/buy/console/{id}")
    public String buyCartConsole( @RequestParam long id){
        //Sólo pasamos el id de lo que quiere comprar el User y listo
        VDConsole console = prodHolder.getConsole(id);
        testcart.addConsole(console);
        return "added_success";
    }
    @PostMapping("/product/buy/videogame/{id}")
    public String buyCartVideogame( @PathVariable long id){
        Videogame videogame = prodHolder.getVideogame(id);
        testcart.addVideogame(videogame);
        return "added_success";
    }
    @GetMapping("/product/buy/console/{id}")
    public String removeCartConsole(Model model, @PathVariable long id){
        model.addAttribute("videogame",prodHolder.getConsole(id));
        testcart.deleteConsole(prodHolder.getConsole(id));
        return "deleted_success_cart";
    }
    @GetMapping("/product/buy/videogame/{id}")
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
