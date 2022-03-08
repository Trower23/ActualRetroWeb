package com.dws.ActualRetro;

import lombok.Data;

@Data
public class User {
    private String name;
    private String surname;
    private long id;
    private String mail;
    private String password;
    private long phone;
    private ShoppingCart usercart;

    public User(String name, String surname, long id, String mail, String password, long phone){
        this.name=name;
        this.surname= surname;
        this.mail=mail;
        this.password= password;
        this.phone= phone;
    }

    public void setId(long id){
        this.id = id;
    }
    //Añadimos metodos para crear y borrar el carrito, o añadir y eliminar productos del carrito?
}
