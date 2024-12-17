package org.example;

public class Wall implements Objects {
    private final Position posizione;
    private String label;

    public Wall(int x, int y) {
        this.posizione = new Position(x, y);
        this.label = "#";
    }

    @Override
    public boolean move(String direzione, Objects[][] map) {
        return false;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Position getPosizione() {
        return posizione;
    }

    @Override
    public void nextPosizione(String direzione) {
    }

    @Override
    public Boolean getAffiancato() {
        return null;
    }
}
