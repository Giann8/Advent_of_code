package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Position currentplayerpos;
    static List<String> direzioni = new ArrayList<>();

    public static void main(String[] args) {

        File file = new File(args[0]);


        Objects[][] mappa = parser(file);
        System.out.println(currentplayerpos);
        System.out.println(direzioni);

        System.out.println("Starting map:");
        printMap(mappa);

        for (String direction : direzioni) {
            boolean check = false;
            System.out.println("Direzione: " + direction);
            check = mappa[currentplayerpos.y()][currentplayerpos.x()].move(direction, mappa);
            if (check) {
                currentplayerpos = currentplayerpos.nextPosition(direction);
            }
           // System.out.println(currentplayerpos);
            printMap(mappa);
        }
        printMap(mappa);
        System.out.println(getGpsTotal(mappa));

    }

    public static Objects[][] parser(File file) {
        Objects[][] map;
        List<Objects[]> premap = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            int rigaint = 0;
            while (sc.hasNextLine()) {
                String[] riga = sc.nextLine().split("");
                if (riga[0].isEmpty()) {
                    continue;
                }
                System.out.println("Riga: " + Arrays.toString(riga));
                if (java.util.Objects.equals(riga[0], "v") || java.util.Objects.equals(riga[0], ">") || java.util.Objects.equals(riga[0], "<") || java.util.Objects.equals(riga[0], "^")) {
                    direzioni.addAll(Arrays.stream(riga).toList());
                } else {
                    String[] rigadoubled = raddoppiatore(riga);
                    Objects[] rigaoggetti = new Objects[rigadoubled.length];
                    int colonna = 0;

                    for (String carat : rigadoubled) {
                        switch (carat) {
                            case "#" -> rigaoggetti[colonna] = new Wall(colonna, rigaint);
                            case "@" -> {
                                rigaoggetti[colonna] = new Player(colonna, rigaint);
                                currentplayerpos = rigaoggetti[colonna].getPosizione();
                            }
                            case "[", "]" -> rigaoggetti[colonna] = new Chest(colonna, rigaint, carat);
                            default -> rigaoggetti[colonna] = new FreeSpace(colonna, rigaint);
                        }
                        colonna++;
                    }
                    premap.add(rigaoggetti);
                    rigaint++;
                }

            }
            map = new Objects[premap.size()][];
            int i = 0;
            for (Objects[] object : premap) {
                map[i] = object;
                i++;
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(Arrays.deepToString(map));
        return map;
    }

    public static void printMap(Objects[][] mappa) {
        for (Objects[] objects : mappa) {
            for (Objects object : objects) {
                System.out.print(object.getLabel());
            }
            System.out.println();
        }
    }

    public static Integer getGpsTotal(Objects[][] mappa) {
        int totale = 0;

        for (Objects[] righe : mappa) {

            for (Objects coordinate : righe) {
                if (coordinate.getLabel().equals("[")) {
                    totale += (coordinate.getPosizione().y() * 100) + coordinate.getPosizione().x();

                }

            }

        }
        return totale;
    }

    public static String[] raddoppiatore(String[] s) {
        String[] doubled = new String[s.length * 2];
        int j = 1;
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("O")) {
                doubled[i + j - 1] = "[";
                doubled[i + j] = "]";
                j++;
                continue;
            } else if (s[i].equals("@")) {
                doubled[i + j - 1] = "@";
                doubled[i + j] = ".";
                j++;
                continue;
            }
            doubled[i + j - 1] = s[i];
            doubled[i + j] = s[i];
            j++;
        }
        System.out.println(Arrays.toString(doubled));
        return doubled;
    }
}