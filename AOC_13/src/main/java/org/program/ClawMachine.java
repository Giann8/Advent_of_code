package org.program;

import java.math.BigInteger;
import java.util.Arrays;

public class ClawMachine {
    private Integer total = 0;
    private Coordinate priceposition;
    private Bottone[] bottoni = new Bottone[2];

    public ClawMachine(long x, long y, Bottone[] bottoni) {
        this.priceposition = new Coordinate(x, y);
        this.bottoni = bottoni;
    }

  /*  public Integer getPrizeX() {
        int times = 0;
        System.out.println(priceposition.x() > priceposition.y());
        if (priceposition.x() > priceposition.y()) {
            priceposition=priceposition.reduceXY(bottoni[0]);
            times++;
            times += getPrizeX();
            return times;
        }
        return 0;
    }*/

    public Boolean getPrize() throws RuntimeException {
        boolean token;
        if ((bottoni[0].calledtime() > 100 || bottoni[1].calledtime() > 100) || (priceposition.x < 0 || priceposition.y < 0)) {
            //throw new RuntimeException("not right");
            return false;
        } else if (priceposition.x == 0 && priceposition.y == 0) {
            return true;
        } else if (priceposition.x() > priceposition.y()) {
            priceposition = this.priceposition.reduceXY(bottoni[0]);
            bottoni[0] = bottoni[0].calledBottone();
        } else {
            priceposition = this.priceposition.reduceXY(bottoni[1]);
            bottoni[1] = bottoni[1].calledBottone();
        }
        token = getPrize();
        return token;

    }

    public long getPrize2() {
        long tot;
        System.out.println(getTokenB());


        if (determinante() == 0 || /*getTokenA() > 100 || getTokenB() > 100 ||*/ getTokenB() <=0 || getTokenA() <=0) {
        System.out.println(getTokenA()%determinante());
            return 0;
        }
        tot = (getTokenA()) * 3L +( getTokenB());

        return tot;

    }


    public long determinante() {
        return (bottoni[0].x() * bottoni[1].y() - (bottoni[0].y() * bottoni[1].x()));
    }

  /*  public long[][] matriceInversa() {
        long[][] matriceinversa = new long[2][2];
        matriceinversa[0][0] = bottoni[1].y();
        matriceinversa[0][1] = -bottoni[1].x();
        matriceinversa[1][0] = -bottoni[0].y();
        matriceinversa[1][1] = bottoni[0].x();
        //System.out.println(Arrays.deepToString(matriceinversa));
        return matriceinversa;
    }

    public long getTokenA() {
        long[][] matrix = matriceInversa();
        float notdeterminante = (float) 1 /determinante();
        System.out.println(((int) notdeterminante)+" "+determinante());
        System.out.println("GetTokena "+(( matrix[0][0] * priceposition.x +  matrix[0][1] * priceposition.y) *notdeterminante));
        return  (( matrix[0][0] * priceposition.x +  matrix[0][1] * priceposition.y) / determinante());

    }

    public long getTokenB() {
        long[][] matrix = matriceInversa();
        return  (( matrix[1][0] * priceposition.x +  matrix[1][1] * priceposition.y) / determinante());

    }*/

    public long getTokenA() {
        long times=(priceposition.x * bottoni[1].y() - priceposition.y * bottoni[1].x());
        if(times%determinante()!=0){
            return 0;
        }
        return (long) (times/determinante());
    }

    public long getTokenB() {
        long times= (priceposition.y * bottoni[0].x() - priceposition.x * bottoni[0].y());
        System.out.println(times);
        if(times%determinante()!=0){
            return 0;
        }
        return (long) (times/determinante());
    }


    public Bottone[] getBottoni() {
        return bottoni;
    }

    public Integer getTotal() {
        if (bottoni[0].label().equals("A")) {
            total += bottoni[0].calledtime() * 3;
            total += bottoni[1].calledtime();
        } else {
            total += bottoni[1].calledtime() * 3;
            total += bottoni[0].calledtime();
        }
        return total;
    }

    public record Coordinate(long x, long y) {
        public Coordinate reduceXY(Bottone button) {
            return new Coordinate(x - button.x(), y - button.y());
        }


    }

}
