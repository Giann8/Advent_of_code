package org.example;


public class Robot {
    private Posizione posizione;
    private Integer velx;
    private Integer vely;


    public Robot(int x, int y, int velx, int vely) {
        this.posizione = new Posizione(x, y);
        this.velx = velx;
        this.vely = vely;
    }

    public void moveRobot(int maxx, int maxy) {
        posizione = posizione.move(velx, vely, maxx, maxy);
       // System.out.println("X: " + posizione.x + " Y: " + posizione.y);
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public Integer getVelX() {
        return velx;
    }

    public Integer getVely() {
        return vely;
    }

    public record Posizione(int x, int y) {
        private Posizione move(int velx, int vely, int maxx, int maxy) {
            int newx = 0;
            int newy = 0;
            if (x + velx >= maxx) {
                newx = (x + velx) - maxx;
            } else if (x + velx < 0) {
                newx = maxx + (x + velx);
            } else {
                newx = x + velx;
            }
            if (y + vely >= maxy) {
                newy = (y + vely) - maxy;
                //System.out.println("Trying to subctract: "+(y+vely)+" to "+ maxy);
                //System.out.println(newy);
            } else if (y + vely < 0) {
                //System.out.println("Y <0");
                newy = maxy + (y + vely);
//                while (newy < 0) {
//                    newy = maxy + (y + vely);
//                }
            } else {
               // System.out.println("Added y: " + y + " + vely: " + vely);
                newy = y + vely;
            }
            return new Posizione(newx, newy);
        }
    }


}
