package org.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrailHead {
  //  private final Integer score;

    public TrailHead(Integer numeroriga, Integer numerocolonna, List<String[]> parsedinput) {
  //      this.score = findTrails(new Punto(numeroriga, numerocolonna, Integer.parseInt(parsedinput.get(numeroriga)[numerocolonna])), parsedinput);
    }

   /* public Integer getScore() {
        return score;
    }

   /* private Integer findTrails(Punto trailhead, List<String[]> parsedinput) {
        Integer tot = 0;
        List<Punto> intorno = trailhead.searchIntornoPunto(parsedinput);
        for (Punto p : intorno) {
            if (trailhead.valorepunto + 1 == p.valorepunto) {
                if (p.valorepunto == 9) {
                    tot += 1;
                } else {
                    tot += findTrails(p, parsedinput);
                }
            }

        }

        return tot;
    }


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

            if (numeroriga - 1 > 0) {
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
            if (numerocolonna - 1 > 0) {
                Punto sinistra = new Punto(numeroriga, numerocolonna - 1, Integer.parseInt(matrix.get(numeroriga)[numerocolonna - 1]));
                if (Objects.equals(this.valorepunto + 1, sinistra.valorepunto)) {
                    puntiok.add(sinistra);
                }
            }


            return puntiok;

        }

    }*/
}
