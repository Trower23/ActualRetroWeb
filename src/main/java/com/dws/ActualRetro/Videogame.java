package com.dws.ActualRetro;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videogame {
    private String name;
    private long id;
    private float price;
    private int stock;
    private int pegi;
    private Date date;
    private VDGenre genre;

    public Videogame(String na, float pri, int pe, Date date, VDGenre gen){
        this.name = na;
        this.price = pri;
        this.pegi = pe;
        this.stock = 1;
        this.date = date;
        this.genre = gen;
    }

    public void setId(long num){
        this.id = num;
    }
    public long getId(){
        return this.id;
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

    //Para poder acceder al género de los juegos, ya que querremos clasificarlos
    //por género

    public String genre(){
        return this.genre.toString();
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
