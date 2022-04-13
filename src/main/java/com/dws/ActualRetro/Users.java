package com.dws.ActualRetro;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Users {
    private String name;
    private String surname;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String mail;
    private String password;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
    public Users(String name, String surname, String mail, String password, String phone){
        this.name=name;
        this.surname= surname;
        this.mail=mail;
        this.password= password;
        this.phone= phone;
        this.shoppingCart=new ShoppingCart();
    }
    public void setId(long id){
        this.id = id;
    }
}
