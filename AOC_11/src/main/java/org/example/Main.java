package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        Stones stones = new Stones(file);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting ...");
        //stones.printStones();
        //System.out.println();

        stones.blinkList(stones.toMap());
        //System.out.println("-- LENGTH: " + stones.getLength() + " --");

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

 /*   public static void blinkNTimes(Integer n, Stones currentstones) {

        //System.out.println("Starting stones:");
        //currentstones.printStones();
        //  System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.println("Blinked " + (i + 1) + " times");
            currentstones.blinkList();
            // currentstones.printStones();
            System.out.println();
        }

    }*/


}