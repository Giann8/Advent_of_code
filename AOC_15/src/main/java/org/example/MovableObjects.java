package org.example;

import java.util.Arrays;

public abstract class MovableObjects implements Objects {
    private Position posizione;
    private Boolean affiancato = false;

    public MovableObjects(int x, int y) {
        this.posizione = new Position(x, y);
    }


    @Override
    public boolean move(String direzione, Objects[][] map) {
        boolean check = false;
        Objects[][] copy = createCopy(map);
        /*if ((direzione.equals("^") || direzione.equals("v")) && (this.getLabel().equals("[") || this.getLabel().equals("]"))) {
            if (java.util.Objects.equals(this.getLabel(), "[")) {
                System.out.println("Posizione: " + posizione + " NextPos: " + posizione.nextPosition(direzione) + " Check: " + check);
                if (!checkNextPos(copy, direzione)) {
                    return false;
                }
                check=true;
                map = copy;
                map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() + 1] = map[posizione.y()][posizione.x() + 1];
                map[posizione.y()][posizione.x() + 1] = new FreeSpace(posizione.x() + 1, posizione.y());
                map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() + 1].nextPosizione(direzione);

            } else if (java.util.Objects.equals(this.getLabel(), "]")) {
                if (!checkNextPos(copy, direzione)) {
                    return false;
                }
                check=true;
                map = copy;
                map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() - 1] = map[posizione.y()][posizione.x() - 1];
                map[posizione.y()][posizione.x() - 1] = new FreeSpace(posizione.x() - 1, posizione.y());
                map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() - 1].nextPosizione(direzione);
            }
        } else*/
        check = map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x()].move(direzione, map);

        if (check) {
            if ((direzione.equals("^") || direzione.equals("v"))) {
                if (this.getClass().equals(Chest.class)) {
                    setAffiancato(true);
                }

                boolean checktwo;
                if (java.util.Objects.equals(this.getLabel(), "[")) {
                    if (!copy[posizione.y()][posizione.x() + 1].getAffiancato()) {
                        checktwo = copy[posizione.y()][posizione.x() + 1].move(direzione, copy);

                    System.out.println("Copy");
                    for (Objects[] riga : copy) {
                        for (Objects s : riga) {
                            System.out.print(s.getLabel());
                        }
                        System.out.println();
                    }
//                    checktwo = checkNextPos(map, direzione);
//                    if (!checktwo) return false;
//                    map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() + 1] = map[posizione.y()][posizione.x() + 1];
//                    map[posizione.y()][posizione.x() + 1] = new FreeSpace(posizione.x() + 1, posizione.y());
                    // map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() + 1].nextPosizione(direzione);

                } else if ((java.util.Objects.equals(this.getLabel(), "]"))) {
                    checktwo = copy[posizione.nextPosition(direzione).y()][posizione.x() - 1].move(direzione, copy);

                    System.out.println("Copy");
                    for (Objects[] riga : copy) {
                        for (Objects s : riga) {
                            System.out.print(s.getLabel());
                        }
                        System.out.println();
                    }
//                    checktwo = checkNextPos(map, direzione);
//                    if (!checktwo) return false;
//
//                    map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() - 1] = map[posizione.y()][posizione.x() - 1];
//                    map[posizione.y()][posizione.x() - 1] = new FreeSpace(posizione.x() - 1, posizione.y());
                    // map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() - 1].nextPosizione(direzione);
                }
            }
            map[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x()] = this;
            map[posizione.y()][posizione.x()] = new FreeSpace(posizione.x(), posizione.y());
            this.nextPosizione(direzione);
            return true;
        }
        return false;
    }

    public boolean checkNextPos(Objects[][] mappa, String direzione) {
        Objects[][] copy = createCopy(mappa);
        if (this.getLabel().equals("[")) {
            return copy[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() + 1].move(direzione, copy) && copy[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x()].move(direzione, copy);
        }
        return copy[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x() - 1].move(direzione, copy) && copy[posizione.nextPosition(direzione).y()][posizione.nextPosition(direzione).x()].move(direzione, copy);
    }

    public Position getPosizione() {
        return posizione;
    }

    public void setAffiancato(boolean s) {
        this.affiancato = s;
    }

    @Override
    public Boolean getAffiancato() {
        return affiancato;
    }

    public Objects[][] createCopy(Objects[][] mappa) {
        Objects[][] copy = new Objects[mappa.length][];
        int i = 0;
        for (Objects[] riga : mappa) {
            copy[i] = Arrays.copyOf(riga, riga.length);
            i++;
        }
        return copy;
    }

    @Override
    public void nextPosizione(String direzione) {
        posizione = posizione.nextPosition(direzione);
    }
}
