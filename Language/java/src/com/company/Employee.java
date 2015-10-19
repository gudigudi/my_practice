package com.company;

/**
 * Created by gudi on 4/12/15.
 */
public class Employee {
    public String name;
    public String address;
    public transient int SSN;
    public int number;

    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name
            + " " + this.address);
    }
}
