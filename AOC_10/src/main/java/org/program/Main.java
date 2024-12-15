package org.program;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Reader reader = new Reader(file);
        List<String[]> parsedinput = reader.getInput();
        for (String[] s : parsedinput) {
            for (String r : s) {
                System.out.print(r);

            }
            System.out.println();
        }

        Trails trails = new Trails(parsedinput);
        System.out.println(trails.tot);
    }
}