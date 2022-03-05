package com.dws.ActualRetro;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videogame {
    private String name;
    private int id;
    private float price;
    private int stock;
    private int pegi;
    private Fecha fecha;

    public Videogame(String na, float pri, int pe, Fecha fecha){
        this.name = na;
        this.price = pri;
        this.pegi = pe;
        this.stock = 1;
        this.fecha= fecha;
    }

    public void setId(int num){
        this.id = num;
    }

    public boolean isStock(){
        if (stock > 0){
            return true;
        }else{
            return false;
        }
    }

    public void addStock(){
        this.stock++;
    }
    public void removeStock(){
        this.stock--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videogame videogame = (Videogame) o;
        return name.equals(videogame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
