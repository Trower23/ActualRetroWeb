package com.dws.ActualRetro;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    private String name;
    private String surname;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String mail;
    private String password;
    private long phone;

    public User(String name, String surname, long id, String mail, String password, long phone){
        this.name=name;
        this.surname= surname;
        this.id=id;
        this.mail=mail;
        this.password= password;
        this.phone= phone;
    }
    public void setId(long id){
        this.id = id;
    }
}
