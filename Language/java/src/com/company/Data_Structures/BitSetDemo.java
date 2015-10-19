package com.company.Data_Structures;

import java.util.BitSet;

/**
 * Created by gudi on 4/13/15.
 */
public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bitSet1 = new BitSet(16);
        BitSet bitSet2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i%2) == 0) bitSet1.set(i);
            if ((i%5) != 0) bitSet2.set(i);
        }
        System.out.println("Initial pattern in bitSet1: " + bitSet1);
        System.out.println("Initial pattern in bitSet2: " + bitSet2);

        // AND bits
        bitSet2.and(bitSet1);
        System.out.println("\nbitSet2 AND bitSet2: " + bitSet2);

        // OR bits
        bitSet2.or(bitSet1);
        System.out.println("\nbitSet2 OR bitSet2: " + bitSet2);

        // XOR bits
        bitSet2.xor(bitSet1);
        System.out.println("\nbitSet2 XOR bitSet2: " + bitSet2);
    }
}
