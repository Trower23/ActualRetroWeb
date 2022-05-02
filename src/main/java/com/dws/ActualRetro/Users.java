package com.dws.ActualRetro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Users {
    private String name;
    private String surname;
    private String username;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String mail;
    private String password;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Videogame> videogamesHistory;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VDConsole> consolesHistory;

    //Lista de roles que posee el usuario
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    public Users(String name, String surname, String username, String mail, String password, String phone){
        this.name=name;
        this.surname= surname;
        this.username=username;
        this.mail=mail;
        this.password= password;
        this.phone= phone;
        this.shoppingCart=new ShoppingCart();
        this.videogamesHistory = new ArrayList<>();
        this.consolesHistory = new ArrayList<>();
    }

    public void setId(long id){
        this.id = id;
    }
    public void setShoppingCart(ShoppingCart shopCa){
        this.shoppingCart = shopCa;
    }
    public void buyVideogame(Videogame videogame){
        this.videogamesHistory.add(videogame);
    }
    public void buyConsole(VDConsole vdConsole){
        this.consolesHistory.add(vdConsole);
    }

}
