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

   public void addProduct(VDConsole vdConsole){
       long cond = this.containsProduct(vdConsole);
       if (cond == -1) {
           vdConsole.setId(conId.incrementAndGet());
           this.consoles.put(vdConsole.getId(), vdConsole);
       }else{
           this.consoles.get(cond).addStock();
       }
   }

   public void addProduct(Videogame videogame){
       long cond = this.containsProduct(videogame);
       if (cond == -1){
           videogame.setId(gaId.incrementAndGet());
           this.games.put(videogame.getId(), videogame);
       }else{
           this.games.get(cond).addStock();
       }
    }

    public long containsProduct(VDConsole vdConsole){
       if (this.consoles.containsValue(vdConsole)){
           List<VDConsole> auxlist = new ArrayList<>(consoles.values());
           return auxlist.get(auxlist.indexOf(vdConsole)).getId();
       }else{
           //Si no lo contiene, le devuelvo un -1
           return -1;
       }
    }
    public long containsProduct(Videogame videogame){
       if (this.games.containsValue(videogame)){
           List<Videogame> auxlist = new ArrayList<>(games.values());
           return auxlist.get(auxlist.indexOf(videogame)).getId();
       }else{
           return -1;
       }
    }
    public boolean containsConsole(long id){
        return this.consoles.containsKey(id);
    }
    public boolean containsVideogame(long id){
        return this.games.containsKey(id);
    }

    public VDConsole delete(VDConsole vdConsole){
        long cond = this.containsProduct(vdConsole);
       if (cond == -1) {
           //No encuentra el producto
           return null;
       }else{
           this.consoles.get(cond).removeStock();
           if (!this.consoles.get(cond).isStock()) {
               return this.consoles.remove(vdConsole.getId());
           }
           return vdConsole;
       }
   }
    public Videogame delete(Videogame videogame){
        long cond = this.containsProduct(videogame);
        if (cond == -1) {
            return null;
        }else{
            this.games.get(cond).removeStock();
            if (!this.games.get(cond).isStock()) {
                return this.games.remove(videogame.getId());
            }
            return videogame;
        }
    }
    public void put(long id, VDConsole vdConsole){
       consoles.replace(id, vdConsole);
    }
    public void put(long id, Videogame videogame){
       games.replace(id, videogame);

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
