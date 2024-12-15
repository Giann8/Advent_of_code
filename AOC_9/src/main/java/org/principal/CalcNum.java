package org.principal;


import java.util.*;
import java.util.List;


public class CalcNum implements Iterable<String> {
    private final long tot;
    private List<String> rearranged;
    private List<Main.Point> rearranged2;

    public CalcNum(String parsedinput) {
        this.rearranged = reArranger(parsedinput);
        this.tot = calcTot(rearranged);
    }

    public CalcNum(List<Main.Point> parsedinput) {
        this.rearranged2 = reArranger2(parsedinput);
        this.tot = calcTot2(printRearrange());
        //  this.tot=0;
    }

    public long getTot() {
        return tot;
    }

    public String getRearranged() {
        return rearranged.toString();
    }

    public String printRearrange() {
        StringBuilder finalstring = new StringBuilder();
        for (Main.Point point : rearranged2) {
            for (int i = 0; i < point.torepeat(); i++) {
                finalstring.append(point.carattere()).append(" ");
            }
        }
        return finalstring.toString();
    }

    private List<String> reArranger(String parsedinput) {
        List<String> stringa = new ArrayList<>(Arrays.asList(parsedinput.split(" ")));
        int i = stringa.size() - 1;
        for (int j = 0; j < stringa.size(); j++) {
            if (i == 0) break;
            if (Objects.equals(stringa.get(j), ".")) {
                for (; i > j; i--) {
                    if (!Objects.equals(stringa.get(i), ".")) {
                        Collections.swap(stringa, i, j);
                        break;
                    }
                }
            }
        }
        // System.out.println(stringa);
        return stringa;
    }

    private List<Main.Point> reArranger2(List<Main.Point> stringa) {
        System.out.println(stringa);
        List<Main.Point> string = stringa;
        // System.out.println("Started rearranger2");
        for (int i = string.size() - 1; i > 1; i--) {
            //System.out.println("block " + string.get(i) + " can pass " + !Objects.equals(string.get(i).getCarattere(), "."));
            if (!Objects.equals(string.get(i).getCarattere(), ".")) {
                for (int j = 0; j < i; j++) {
                    if (Objects.equals(string.get(j).getCarattere(), ".") && string.get(i).getRepeat() == string.get(j).getRepeat()) {
                        Collections.swap(string, i, j);
                        // System.out.println("scambiati valori" + string);
                        break;
                    } else if (Objects.equals(string.get(j).getCarattere(), ".") && string.get(i).getRepeat() < string.get(j).getRepeat()) {
                        List<Main.Point> rearrangedlist = new ArrayList<>();
                        for (int k = 0; k < string.size(); k++) {
                            if (k == j) {
                                rearrangedlist.add(string.get(i));
                                rearrangedlist.add(new Main.Point(".", string.get(j).getRepeat() - string.get(i).getRepeat()));
                                string.set(i, new Main.Point(".", string.get(i).getRepeat()));
                                continue;
                            }
                            rearrangedlist.add(string.get(k));
                        }
                        string = rearrangedlist;
                        //    System.out.println("rearranged completelist" + string);
                    }
                }
            }
        }
        //System.out.println("Ended rearranger2");

        return string;
    }


    private long calcTot(List<String> array) {
        long tot = 0;
        int i = 0;
        System.out.println(array);
        while (iterator().hasNext()) {
            if (i == array.size()) break;
            if (Objects.equals(array.get(i), ".")) {
                tot += 0;
            } else {
                int tmp = Integer.parseInt(array.get(i));
                System.out.print("Numero da aggiungere: " + tmp + " moltiplicato per: " + i);
                System.out.print("Totale");
                tot += (long) tmp * i;
                System.out.println(tot);
            }
            i++;
        }
        return tot;
    }

    private long calcTot2(String rearranged2) {
        long tot = 0;
        int i = 0;
        for (String punto : rearranged2.split(" ")) {
            System.out.print(punto);
            if (Objects.equals(punto, ".")) {
                tot += 0;
            } else {
                tot += (long) Integer.parseInt(punto) * i;
            }
            i++;
        }


        return tot;
    }

    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableList(rearranged).iterator();
    }

    public Iterator<Main.Point> listIterator() {
        return Collections.unmodifiableList(rearranged2).iterator();
    }

    public List<Main.Point> getRearranged2() {
        return rearranged2;
    }
}
