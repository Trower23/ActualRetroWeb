package com.dws.ActualRetro;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VDConsole {
    private String name;
    private long id;
    private float price;
    private int stock = 1;
    private int maxcontrollers;
    private Date date;
    private String description;

    public VDConsole(String na, float pri, int maxcon, Date date, String description){
        this.name = na;
        this.price = pri;
        this.maxcontrollers = maxcon;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VDConsole vdConsole = (VDConsole) o;
        return name.equals(vdConsole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
