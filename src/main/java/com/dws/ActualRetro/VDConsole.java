package com.dws.ActualRetro;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VDConsole {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float price;
    private int stock = 1;
    private int maxcontrollers;
    @Transient   // this annotation says that we have an attribute which's not included in a DB table. If we don't use Transient, execution fails.
    private Date date;


    public VDConsole(String na, float pri, int maxcon, Date date){
        this.name = na;
        this.price = pri;
        this.maxcontrollers = maxcon;
        this.date = date;
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
