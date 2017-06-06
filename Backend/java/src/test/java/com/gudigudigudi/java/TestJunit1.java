package com.gudigudigudi.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit1 {

    String message="Rebert";
    MessageUtil messageUtil=new MessageUtil(message);

    @Test
    public void testPrintMessage(){
        System.out.println("Inside testPrintMessage()");
        assertEquals(message,messageUtil.printMessage());
    }
}
