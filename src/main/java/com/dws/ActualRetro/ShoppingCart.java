package com.dws.ActualRetro;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//THERE WOULD BE ONE SHOPPING CART PER USER.

@Data
public class ShoppingCart {
    @Autowired
    VideogameService videogameService;
    @Autowired
    VideoconsoleService videoconsoleService;

    private long totalProducts;
    private float totalPrice;
    private List<VDConsole> consoleList = new ArrayList<>();
    private List<Videogame> videogameList = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idShoppingCart;


    public ShoppingCart() {
        this.totalPrice = 0;
        this.totalProducts = 0;
    }

    public void addConsole(VDConsole console) {
        if (videoconsoleService.consoleRepository.findAll().contains(console)) { // check if this product is available (Autowireded service).
            consoleList.add(console);
            totalProducts++;
            totalPrice = totalPrice + console.getPrice();
        }
        if (videoconsoleService.consoleRepository.getById(console.getId()).getStock()>1){
            videoconsoleService.consoleRepository.getById(console.getId()).removeStock();
        }else{
            videoconsoleService.consoleRepository.deleteById(console.getId());
        }
    }

    public void addVideogame(Videogame videogame) {
        if (videogameService.videogameRepository.findAll().contains(videogame)) {
            videogameList.add(videogame);
            totalProducts++;
            totalPrice = totalPrice + videogame.getPrice();
        }
        if (videogameService.videogameRepository.getById(videogame.getId()).getStock() > 1) {
            videogameService.videogameRepository.getById(videogame.getId()).removeStock();
        } else {
            videogameService.videogameRepository.deleteById(videogame.getId()); //There's only 1 item, if we sell it we should decrease stock, but, stock==0 == no product.
        }
    }

    public void deleteConsole(VDConsole console) {
        if (consoleList.contains(console)) {
            consoleList.remove(console);
            totalProducts--;
            totalPrice = totalPrice - console.getPrice();
            if (videoconsoleService.consoleRepository.existsById(console.getId())) {
                videoconsoleService.consoleRepository.getById(console.getId()).addStock();
            } else {
                videoconsoleService.consoleRepository.save(console);
            }

        }
    }

    public void deleteVideogame(Videogame videogame) {
        if (videogameList.contains(videogame)) {
            videogameList.remove(videogame);
            totalProducts--;
            totalPrice = totalPrice - videogame.getPrice();
            if (videogameService.videogameRepository.existsById(videogame.getId())){
                videogameService.videogameRepository.getById(videogame.getId()).addStock();
            }else{
                videogameService.videogameRepository.save(videogame);
            }

        }

    }
}

