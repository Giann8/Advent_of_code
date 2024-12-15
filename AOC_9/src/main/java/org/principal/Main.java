package org.principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String input;
        File rawinput = new File(args[0]);
        input = readInput(rawinput);
        System.out.println(input);

        //String parsedinput = parseInput(input);

        //  System.out.println("parsedinput "+parsedinput);

        List<Point> parsedinput2 = parseInput2(input);
        // System.out.println("parsedinput2 "+parsedinput2);
        CalcNum calcolatore2 = new CalcNum(parsedinput2);
        // System.out.println(calcolatore2.getRearranged2());
        System.out.println(calcolatore2.printRearrange());

        System.out.println("---TOT:" + calcolatore2.getTot() + "---");
        // CalcNum calcolatore = new CalcNum(parsedinput);
        //System.out.println(calcolatore.getRearranged());
        //System.out.println("---TOT:" + calcolatore.getTot() + "---");
        //System.out.println(calcolatore.getTot());
    }


    public static String readInput(File file) {
        StringBuilder line = new StringBuilder();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                line.append(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file detected");
        }

        return line.toString();
    }

    public static String parseInput(String input) {

        StringBuilder parsedriga = new StringBuilder();
        boolean alternate = false;
        int i = 0;
        for (Character s : input.toCharArray()) {
            Parser parser = new Parser(s.toString(), i, alternate);
            if (!alternate) i++;
            System.out.println(i);
            parsedriga.append(parser.getParsedoutput());
            alternate = parser.alternateChange();
        }

        return parsedriga.toString();
    }

    public static List<Point> parseInput2(String input) {
        List<Point> punti = new ArrayList<>();
        boolean alternate = false;
        int i = 0;
        for (Character s : input.toCharArray()) {
            if (!alternate) {
                punti.add(new Point(Integer.toString(i), Integer.parseInt(s.toString())));
                i++;
            } else {
                punti.add(new Point(".", Integer.parseInt(s.toString())));
            }
            System.out.println(i);
            alternate = !alternate;
        }
        return punti;
    }

    public record Point(String carattere, int torepeat) {

        public String getCarattere() {
            return this.carattere;
        }

        public int getRepeat() {
            return this.torepeat;
        }
    }

}

