package com.gudigudigudi.sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TreeSet;

@SpringBootApplication
public class Main {
    private static final Logger logger= LoggerFactory.getLogger(Main.class);
    private static final String TAG="Main";

    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
        logger.info(TAG,"App started");
    }
}
