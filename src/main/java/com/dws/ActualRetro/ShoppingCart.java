package com.dws.ActualRetro;



import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class ShoppingCart {
    private static long totalProducts= 0;
    private static long totalPrice= 0;
    private Map<Long, VDConsole> consoles = new ConcurrentHashMap<>();
    private Map<Long, Videogame> games = new ConcurrentHashMap<>();
    private long idShoppingCart;
    private static AtomicLong generalId = new AtomicLong(0);

    public ShoppingCart(){
        this.idShoppingCart= generalId.incrementAndGet();
    }

    public void addConsole (VDConsole console){
        consoles.put((long) console.getId(), console);
        totalProducts++;
        totalPrice= (long) (totalPrice+console.getPrice());
    }

    public void addGame (Videogame videogame){
        games.put((long) videogame.getId(), videogame);
        totalProducts++;
        totalPrice= (long) (totalPrice+ videogame.getPrice());
    }



}

