package org.program;

import java.util.Objects;

public class Plot {
    private final String id;
    private Long perimeter = (long) 4;
    final Position posizione;

    public Plot(String plot, Integer riga, Integer colonna) {
        this.id = plot;
        this.posizione = new Position(riga, colonna);
    }


    public String getId() {
        return id;
    }

    public Long getPerimeter() {
        return perimeter;
    }

    public void reducePerimeter() {
        if (perimeter != 0) {
            perimeter--;
        }
    }

    public static String getElementMatrix(String[][] matrix, Position p) {
        return matrix[p.riga][p.colonna];
    }

    public Boolean isNear(Plot plot, String[][] matrix) {
        // System.out.println("Stessa colonna" + ((((this.posizione.colonna - plot.posizione.colonna == 1) && plot.posizione.riga - this.posizione.riga == 0) || ((this.posizione.riga - plot.posizione.riga == 1) && plot.posizione.colonna - this.posizione.colonna == 0))));
       // System.out.println("Diagonale si " + ((this.posizione.upRight().equals(plot.posizione) && Objects.equals(getElementMatrix(matrix, this.posizione.right()), this.id))));
        if (((this.posizione.colonna - plot.posizione.colonna == 1) && plot.posizione.riga - this.posizione.riga == 0) || ((this.posizione.riga - plot.posizione.riga == 1) && (plot.posizione.colonna - this.posizione.colonna == 0))) {
            this.reducePerimeter();
            plot.reducePerimeter();
            return true;
        }
       // System.out.println((this.posizione.riga==plot.posizione.riga+1 &&(this.posizione.colonna < matrix[0].length - 1) && checkIfOneNear(plot.posizione.colonna,matrix[this.posizione.riga])));
        return ((this.posizione.upRight().equals(plot.posizione) && Objects.equals(getElementMatrix(matrix, this.posizione.right()), this.id)) || (this.posizione.riga==plot.posizione.riga+1 &&(this.posizione.colonna < matrix[0].length - 1) && checkIfOneNear(plot.posizione.colonna,matrix[this.posizione.riga])));
        //System.out.println(plot.&&&&getElementMatrix(matrix, plot.posizione.down()).equals(getElementMatrix(matrix,this.posizione.upRight()))&&(plot.posizione.equals(this.posizione.upRight())));


    }

    public Boolean checkIfOneNear(int colonna,String[] line){

        for(int i= this.posizione.riga+1;i< line.length;i++){
            if(line[i].equals(this.id)) {
                if (colonna == i) {
                    return true;
                }
            }else{

            }
        }
        return false;
    }


   /* public Boolean isDiagonal(String[][] matrix) {
        if (this.posizione.colonna < matrix[0].length - 1) {
            System.out.println(" " + this.posizione.riga + " " + this.posizione.colonna + " " + getElementMatrix(matrix, this.posizione.right()) + " " + this.id);
        }
        return (this.posizione.colonna < matrix[0].length - 1/*(Objects.equals(plot.posizione, this.posizione.upRight()) && Objects.equals(getElementMatrix(matrix, this.posizione.right()), this.id));
    }
    */

    public record Position(int riga, int colonna) {
        public Position upRight() {
            return new Position(riga - 1, colonna + 1);
        }

        public Position upLeft() {
            return new Position(riga - 1, colonna - 1);
        }

        public Position rightRight() {
            return new Position(riga, colonna + 2);
        }

        public Position down() {
            return new Position(riga + 1, colonna);
        }

        public Position left() {
            return new Position(riga, colonna - 1);
        }

        public Position right() {
            return new Position(riga, colonna + 1);
        }

    }

}
