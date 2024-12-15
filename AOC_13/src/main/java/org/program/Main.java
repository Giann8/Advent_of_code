package org.program;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        long tot = 0;
        List<ClawMachine> clawmachines = new ArrayList<>();
        File file = new File(args[0]);
        clawmachines = parser(file);
        for (ClawMachine c : clawmachines) {
            // System.out.println("current:" + Arrays.deepToString(c.matriceInversa()));
            //System.out.println(c.getTokenA() + " " + c.getTokenB() + " prize is: " + c.getPrize2());
            System.out.println("Prize: " + c.getPrize2());
            tot += c.getPrize2();

        }
        System.out.println(tot);
    }

    public static Bottone[] chooseBottone(Bottone a, Bottone b) {
        Bottone[] bottoni = new Bottone[2];
        if (a.x() > b.x()) {
            bottoni[0] = a;
            bottoni[1] = b;
        } else {
            bottoni[1] = a;
            bottoni[0] = b;
        }
        return bottoni;
    }

    public static List<ClawMachine> parser(File file) {
        List<ClawMachine> clawmachines = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            List<Bottone> bottoni = new ArrayList<>();
            int i = 0;
            while (sc.hasNextLine()) {
                String riga = sc.nextLine();

                System.out.println(riga);
                if (riga.isEmpty()) {
                    bottoni = new ArrayList<>();
                }


                if ((riga.contains("Button A:"))) {
                    riga = riga.replaceAll("[^0-9.,]", "");
                    //System.out.println(riga);
                    String[] coordinate = riga.split(",");
                    bottoni.add(new Bottone(Long.parseLong(coordinate[0]), Long.parseLong(coordinate[1]), 0, "A"));
                    System.out.println("Button A: " + Arrays.toString(coordinate));
                } else if (riga.contains("Button B:")) {
                    riga = riga.replaceAll("[^0-9.,]", "");
                    // System.out.println(riga);
                    String[] coordinate = riga.split(",");
                    bottoni.add(new Bottone(Long.parseLong(coordinate[0]), Long.parseLong(coordinate[1]), 0, "B"));
                    System.out.println("Button B: " + Arrays.toString(coordinate));
                } else if ((riga.startsWith("Prize: "))) {
                    long toadd = Long.parseLong("10000000000000");
                    riga = riga.replaceAll("[^0-9.,]", "");
                   /* int lunghezzamezza = riga.length()/2;
                    if (riga.length() % 2 != 0) {
                        lunghezzamezza=(riga.length()/2)+1;
                    }
                    String[] coordinate = {riga.substring(0, lunghezzamezza), riga.substring(lunghezzamezza)};*/
                    String[] coordinate = riga.split(",");
                    System.out.println(toadd);
                    System.out.println("Prize: " + Arrays.toString(coordinate));
                    //System.out.println((riga.length() / 2));

                    //  Bottone[] bots = chooseBottone(bottoni.getFirst(),bottoni.getLast());
                    Bottone[] bots = new Bottone[]{bottoni.getFirst(), bottoni.getLast()};
                    clawmachines.add(new ClawMachine(Long.parseLong(coordinate[0])+toadd, Long.parseLong(coordinate[1])+toadd, bots));
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return clawmachines;
    }
}