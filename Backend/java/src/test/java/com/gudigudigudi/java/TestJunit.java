package com.gudigudigudi.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit {

    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testAdd(){
        String str="Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    public void testPrintMessage(){
//        message="New";
        assertEquals(message,messageUtil.printMessage());
    }
}
