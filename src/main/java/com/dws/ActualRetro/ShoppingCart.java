package com.dws.ActualRetro;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//No es un holder, es una parte de User. Cada User tendrá un carrito de la compra

@Data
public class ShoppingCart {
    private  long totalProducts;
    private  float totalPrice;
    private List<VDConsole> consoleList= new ArrayList<>();
    private List<Videogame> videogameList = new ArrayList<>();
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idShoppingCart;
    private static AtomicLong generalId = new AtomicLong(0);

    public ShoppingCart(){
        this.idShoppingCart= generalId.incrementAndGet();
        this.totalPrice= 0;
        this.totalProducts= 0;
    }

    public void addConsole (VDConsole console){
        consoleList.add(console);
        totalProducts++;
        totalPrice= totalPrice+console.getPrice();
    }

    public void addVideogame (Videogame videogame){
        videogameList.add(videogame);
        totalProducts++;
        totalPrice= totalPrice+ videogame.getPrice();
    }

    public boolean deleteConsole (VDConsole console){
        if (consoleList.contains(console)){
            consoleList.remove(console);
            totalProducts--;
            totalPrice= totalPrice- console.getPrice();
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteVideogame (Videogame videogame){
        if (videogameList.contains(videogame)){
            videogameList.remove(videogame);
            totalProducts--;
            totalPrice= totalPrice - videogame.getPrice();
            return true;
        }else {
            return false;
        }
    }

}

