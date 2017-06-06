package com.gudigudigudi.sb.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class City implements Serializable{
//    private String state;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    protected City(){}
    protected City(String name,String state) {
        this.name=name;
        this.state=state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
