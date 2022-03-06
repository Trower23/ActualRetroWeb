package com.dws.ActualRetro;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// NECESITAMOS ACORDAR ESTO..
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


}

