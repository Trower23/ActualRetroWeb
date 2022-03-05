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
       //Cojo el valor que me devuelve el método containsProduct
       long cond = this.containsProduct(vdconsole);
       if (cond == -1) {
           //Si no contiene el producto, lo añado al mapa
           vdconsole.setId(conId.incrementAndGet());
           this.consoles.put(vdconsole.getId(), vdconsole);
       }else{
           //Si lo contiene, incremento el Stock que tenemos
           this.consoles.get(cond).addStock();
       }
   }

   //Lo mismo que con el método anterior
    public void addProduct(Videogame videogame){
       long cond = this.containsProduct(videogame);
       if (cond == -1){
           videogame.setId(gaId.incrementAndGet());
           this.games.put(videogame.getId(), videogame);
       }else{
           this.games.get(cond).addStock();
       }
    }

    public long containsProduct(VDConsole vdconsole){
       //Miro a ver si contiene el producto
       if (this.consoles.containsValue(vdconsole)){
           //Si lo contiene, hago una lista auxiliar de todos los productos que tenemos
           List<VDConsole> auxlist = new ArrayList<>(consoles.values());
           //Y devolveré el id (.getId()) de el objeto (.get()) que encaje con el producto que
           //le pasamos por parámetro (.indexOf())
           return auxlist.get(auxlist.indexOf(vdconsole)).getId();
       }else{
           //Si no lo contiene, le devuelvo un -1
           return -1;
       }
    }
    //Lo mismo que con el método anterior
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
        return this.consoles.remove(vdConsole.getId());
    }
    public Videogame delete(Videogame videogame){
        return this.games.remove(videogame.getId());
    }
    //No devuelvo nada ni compruebo nada porque la comprobación
    //ya la hace la api REST y el producto se la pasan a la api
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
