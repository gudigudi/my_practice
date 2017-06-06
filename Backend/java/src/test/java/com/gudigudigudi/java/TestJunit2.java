package com.gudigudigudi.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit2 {

    String message="Rebert";
    MessageUtil messageUtil= new MessageUtil(message);

    @Test
    public void testSalutationMessage(){
        System.out.println("Inside testSalutationMessage()");
        message="Hi!"+"Rebert";
        assertEquals(message,messageUtil.salutationMessage());
    }
}
