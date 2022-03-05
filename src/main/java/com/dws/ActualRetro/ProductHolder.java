package com.dws.ActualRetro;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductHolder {

    private Map<Long, VDConsole> consoles = new ConcurrentHashMap<>();
    private Map<Long, Videogame> games = new ConcurrentHashMap<>();

    private AtomicLong conId = new AtomicLong();
    private AtomicLong gaId = new AtomicLong();

   public void addProduct(VDConsole vdconsole){
       vdconsole.setId(conId.incrementAndGet());
       this.consoles.put(vdconsole.getId(), vdconsole);
   }
    public void addProduct(Videogame videogame){
       videogame.setId(gaId.incrementAndGet());
        this.games.put(videogame.getId(), videogame);
    }

    public boolean containsConsole(long id){
       return this.consoles.containsKey(id);
    }
    public boolean containsVideoame(long id){
        return this.games.containsKey(id);
    }

   public VDConsole delete(VDConsole vdConsole){
       return this.consoles.remove(vdConsole.getId());
   }
    public Videogame delete(Videogame videogame){
       return this.games.remove(videogame.getId());
    }
    public Collection<Videogame> getVideogames(){
       return this.games.values();
    }
    public Collection<VDConsole> getConsoles(){
        return this.consoles.values();
    }

    public VDConsole getConsole(long id){
       return this.consoles.get(id);
    }
    public Videogame getVideogame(long id){
        return this.games.get(id);
    }
}
