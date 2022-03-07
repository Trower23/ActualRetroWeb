package com.dws.ActualRetro;
import lombok.Data;

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
        totalPrice= (long) (totalPrice+console.getPrice());
    }

    public void addVideogame (Videogame videogame){
        videogameList.add(videogame);
        totalProducts++;
        totalPrice= (long) (totalPrice+ videogame.getPrice());
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

    //Como no tenemos una clase "Producto" de la que hereden VDConsole y Videogame
    //Deberíamos implementar esa clase...
    public List<Object> getProducts(){
        List<Object> auxlist = new ArrayList<>();
        auxlist.addAll(this.consoleList);
        auxlist.addAll(this.videogameList);
        return auxlist;
    }

}

