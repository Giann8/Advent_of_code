package org.program;

import java.util.*;

public class Trails {

    long tot = 0;

    public Trails(List<String[]> parsedinput) {
        this.tot = fullTrails(parsedinput);
    }

    /*public long getTotal() {
        long tot = 0;
        while (iterator().hasNext()) {
            tot += iterator().next();
        }

        return tot;
    }*/


    private long fullTrails(List<String[]> parsedinput) {
        long tot = 0;
        for (int i = 0; i < parsedinput.size(); i++) {
            for (int j = 0; j < parsedinput.get(i).length; j++) {
              //  Map<Punto, Boolean> mappa = new HashMap<>();
                Map<Punto, Integer> mappa = new HashMap<>();

                if (Objects.equals(parsedinput.get(i)[j], "0")) {
                    Punto trailhead = new Punto(i, j, 0);
                    findTrails(trailhead, parsedinput, mappa);
                    System.out.println(tot);
                }
                System.out.println(mappa);
                for(Integer p: mappa.values()){
                    //if (p)tot++;
                    tot+=p;
                }
            }
        }
        return tot;
    }

    private void findTrails(Punto trailhead, List<String[]> parsedinput, Map<Punto, Integer> mappa) {
        List<Punto> intorno = trailhead.searchIntornoPunto(parsedinput);
        for (Punto p : intorno) {
            if (trailhead.valorepunto + 1 == p.valorepunto) {
                if (p.valorepunto == 9) {
                  //  mappa.put(p, true);
                    mappa.merge(p, 1, Integer::sum);
                } else {

                    findTrails(p, parsedinput, mappa);

                }
            }

        }

    }

   /* private List<Integer> calcTrails(List<String[]> parsedinput){
        List<Integer> trailslist = new ArrayList<>();
        for(String[] s:parsedinput){
            int i =0;
            for (String number : s) {
                if(Objects.equals(number,"0")){
                    TrailHead trails = new TrailHead(parsedinput.indexOf(s),i,parsedinput);
                    trailslist.add(2);
                }
                i++;
            }
        }

        return trailslist;
    }*/


    record Punto(Integer numeroriga, Integer numerocolonna, Integer valorepunto) {

        @Override
        public Integer numerocolonna() {
            return numerocolonna;
        }

        @Override
        public Integer numeroriga() {
            return numeroriga;
        }

        private List<Punto> searchIntornoPunto(List<String[]> matrix) {
            List<Punto> puntiok = new ArrayList<>();

            if (numeroriga - 1 >= 0) {
                Punto sopra = new Punto(numeroriga - 1, numerocolonna, Integer.parseInt(matrix.get(numeroriga - 1)[numerocolonna]));
                if (Objects.equals(this.valorepunto + 1, sopra.valorepunto)) {
                    puntiok.add(sopra);
                }
            }
            if (numeroriga + 1 < matrix.size()) {
                Punto sotto = new Punto(numeroriga + 1, numerocolonna, Integer.parseInt(matrix.get(numeroriga + 1)[numerocolonna]));
                if (Objects.equals(this.valorepunto + 1, sotto.valorepunto)) {
                    puntiok.add(sotto);
                }
            }
            if (numerocolonna + 1 < matrix.getFirst().length) {
                Punto destra = new Punto(numeroriga, numerocolonna + 1, Integer.parseInt(matrix.get(numeroriga)[numerocolonna + 1]));

                if (Objects.equals(this.valorepunto + 1, destra.valorepunto)) {
                    puntiok.add(destra);
                }
            }
            if (numerocolonna - 1 >= 0) {
                Punto sinistra = new Punto(numeroriga, numerocolonna - 1, Integer.parseInt(matrix.get(numeroriga)[numerocolonna - 1]));
                if (Objects.equals(this.valorepunto + 1, sinistra.valorepunto)) {
                    puntiok.add(sinistra);
                }
            }


            return puntiok;

        }

    }
}
