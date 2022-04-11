package com.dws.ActualRetro;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Videogame {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float price;
    private int stock = 1;
    private int pegi;
    @Transient
    private Date date;
    private VDGenre genre;
    private String description;

    public Videogame(String na, float pri, int pe, Date date, VDGenre gen, String description){
        this.name = na;
        this.price = pri;
        this.pegi = pe;
        this.date = date;
        this.genre = gen;
        this.description= description;
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
