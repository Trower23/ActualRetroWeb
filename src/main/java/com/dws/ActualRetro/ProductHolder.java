package com.dws.ActualRetro;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductHolder {
    //Añadir @autowired cuando instanciemos un ProductHolder
    private Map<Integer, VDConsole> consoles = new ConcurrentHashMap<>();
    private Map<Integer, Videogame> games = new ConcurrentHashMap<>();

    private AtomicInteger conId = new AtomicInteger();
    private AtomicInteger gaId = new AtomicInteger();

    //Tenemos que tener cuidado de no añadir juegos o consolas que estén repetidos
    public void add(VDConsole newcon){
        if (!consoles.containsValue(newcon)) {
            int aux = conId.incrementAndGet();
            newcon.setId(aux);
            consoles.put(aux, newcon);
        }else{
            //Cómo hacemos para aumentar el stock de esa consola? Recordemos que según nuestro
            //modelo, tenemos que guardar el stock de cada producto que tengamos...
            //Usamos estructuras auxiliares?
        }
    }
    //Aquí lo mismo
    public void add(Videogame newga){
        int aux = gaId.incrementAndGet();
        newga.setId(aux);
        games.put(aux, newga);
    }

    //Aquí tendríamos que comprobar el atributo Stock, o bien automáticamente eliminar del mapa
    //los productos que tengan su atributo Stock a cero.
    public boolean isThere(VDConsole thisone){
        return consoles.containsValue(thisone);
    }
    public boolean isThere(Videogame thisone){
        return games.containsValue(thisone);
    }
    public boolean isThereCon(int id){
        return consoles.containsKey(id);
    }
    public boolean isThereGa(int id){
        return games.containsKey(id);
    }

    public Collection<VDConsole> getConsoles(){
        return this.consoles.values();
    }
    public Collection<Videogame> getVideogames(){
        return this.games.values();
    }

    public VDConsole getCon(int id){
        return consoles.get(id);
    }
    public Videogame getGa(int id){
        return games.get(id);
    }

    public boolean deleteCon(int id){
        //Según el método nos devuelva una cosa u otra, devolveremos un HTTPResponse en el controller u otro,
        //o un template u otro si nos encontramos en el WebController.
        //Deberíamos hacer esto también en el Get y en el Add?
        if (consoles.containsKey(id)){
            consoles.remove(id, consoles.get(id));
            return true;
        }else{
            return false;
        }
    }
    public boolean deleteGa(int id){
        if (games.containsKey(id)){
            games.remove(id, games.get(id));
            return true;
        }else{
            return false;
        }
    }

    public boolean put(int id, VDConsole newcon){
        if (consoles.containsKey(id)){
            consoles.replace(id, newcon);
            return true;
        }else{
            return false;
        }
    }
    public boolean put(int id, Videogame newga){
        if (games.containsKey(id)){
            games.replace(id, newga);
            return true;
        }else{
            return false;
        }
    }
}
