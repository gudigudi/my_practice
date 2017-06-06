package com.gudigudigudi.java;

/**
 * Prints the given message on console.
 */
public class MessageUtil {

    private String message;

    public MessageUtil(String message) {
        this.message = message;
    }

    public String printMessage(){
        System.out.println(message);
        return message;
    }

    public String salutationMessage() {
    return "Hi!"+message;
    }
}
