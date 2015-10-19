package java_tutorial;

import java_tutorial.Singleton;

import java.lang.String;

public class SingletonDemo {
    public static void main(String[] args) {
        Singleton tmp = Singleton.getInstance();
        tmp.demoMethod();
    }
}