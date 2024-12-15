package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        RobotMap mapparobot = new RobotMap(101, 103);
        parser(file, mapparobot);
        mapparobot.printMap();
        mapparobot.moveAll(10000);
       // System.out.println(mapparobot.getSF());
    }

    public static void parser(File file, RobotMap mapparobot) {

        try (Scanner sc = new Scanner(file)) {

            int i = 0;
            while (sc.hasNextLine()) {
                String[] riga = sc.nextLine().split(" ");

                //System.out.println(Arrays.toString(riga));


                String[] posizione = riga[0].replaceAll("[^-0-9.,]", "").split(",");
                String[] velocita = riga[1].replaceAll("[^-0-9.,]", "").split(",");


                Robot robot = new Robot(Integer.parseInt(posizione[0]), Integer.parseInt(posizione[1]), Integer.parseInt(velocita[0]), Integer.parseInt(velocita[1]));
               // System.out.println(robot.getVely());
                mapparobot.addRobot(robot);

                //  Bottone[] bots = chooseBottone(bottoni.getFirst(),bottoni.getLast());
            }
            i++;

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
}