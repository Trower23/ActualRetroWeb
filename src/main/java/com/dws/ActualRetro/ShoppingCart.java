package com.dws.ActualRetro;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class ShoppingCart {
    private long total;
    private Map<Long, VDConsole> consoles = new ConcurrentHashMap<>();
    private Map<Long, Videogame> games = new ConcurrentHashMap<>();
    private long idShoppingCart;
    private static AtomicLong generalId = new AtomicLong(0);

    public ShoppingCart(){
        this.total=0;
        this.idShoppingCart= generalId.incrementAndGet();
    }

}

