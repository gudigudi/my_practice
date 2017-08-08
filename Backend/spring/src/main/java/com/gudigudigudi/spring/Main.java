package com.gudigudigudi.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext("spring/knight.xml");

        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();

        context.close();
    }

}
