package com.dws.ActualRetro;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VDConsole {
    private String name;
    private long id;
    private float price;
    private int stock;
    private int maxcontrollers;
    //Crear clase fecha.


    public VDConsole(String na, float pri, int maxcon){
        this.name = na;
        this.price = pri;
        this.maxcontrollers = maxcon;
        this.stock = 1;
    }

    public void setId(long num){
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
        VDConsole vdConsole = (VDConsole) o;
        return name.equals(vdConsole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
