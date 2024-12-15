package org.example;

import java.util.*;

public class RobotMap implements Iterable<Robot> {
    List<Robot> robots = new ArrayList<>();
    private Integer[][] printablemap;
    private int maxx;
    private int maxy;


    public RobotMap(int maxx, int maxy) {
        this.maxx = maxx;
        this.maxy = maxy;
        azzeraMap();
    }

    public void azzeraMap() {
        printablemap = new Integer[maxy][maxx];
        for (int i = 0; i < maxy; i++) {
            for (int j = 0; j < maxx; j++) {
                printablemap[i][j] = 0;
            }
        }
    }

    public void updateMap() {
        azzeraMap();
        forEach((robot) -> {
            printablemap[robot.getPosizione().y()][robot.getPosizione().x()] += 1;
        });
    }

    public void printMap() {
        updateMap();
        int centro = 0;
        int riga = 0;
        for (Integer[] i : printablemap) {
            int colonna = 0;

            for (int j : i) {
                if (colonna == maxx / 2 && j != 0) {
                    centro++;
                }

//                if ((colonna == maxx / 2) || (riga == maxy / 2)) {
//                    colonna++;
//                    System.out.print(" ");
//                    continue;
//                }
                if (j == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(j);
                }
                colonna++;
            }
            riga++;
            System.out.println();
        }


    }


    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    public void moveAll(int times) {
        int maxsf = 0;
        int minsf = 0;
        int varianzay = 0;
        int varianzax = 0;

        for (int i = 0; i < times; i++) {

            System.out.println("TIME: " + (i + 1));
            forEach(robot -> {
                robot.moveRobot(maxx, maxy);
            });
            updateMap();
            int sf = getSF();

            if (sf > maxsf) {
                maxsf = sf;
            }
            if (minsf == 0 || minsf > sf) {
                minsf = sf;
            }
            if(sf==minsf){
                printMap();
            }

        }

        System.out.println("-----------");
        System.out.println("Maxsf: " + maxsf + " Minsf: " + minsf + " Varianzay: " + varianzay+" Varianzax: "+ varianzax);

    }



    public Integer getSF() {
        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;
        for (int i = 0; i < maxy; i++) {
            for (int j = 0; j < maxx; j++) {
                if (printablemap[i][j] != 0) {
                    if (i < maxy / 2 && j < maxx / 2) {
                        quad1 += printablemap[i][j];
                    } else if (i < maxy / 2 && j > maxx / 2) {
                        quad2 += printablemap[i][j];
                    } else if (i > maxy / 2 && j < maxx / 2) {
                        quad3 += printablemap[i][j];
                    } else if (i != maxy / 2 && j != maxx / 2) {
                        quad4 += printablemap[i][j];
                    }
                }
            }
        }
        System.out.println("quad1: " + quad1 + "quad2: " + quad2 + "quad3: " + quad3 + "quad4: " + quad4);
        return quad1 * quad2 * quad3 * quad4;
    }


    @Override
    public Iterator<Robot> iterator() {
        return List.copyOf(robots).iterator();
    }
}
