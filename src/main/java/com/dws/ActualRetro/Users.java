package com.dws.ActualRetro;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private long phone;

    public Users(String name, String surname, long id, String mail, String password, long phone){
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
