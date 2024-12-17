package org.example;

public class FreeSpace implements Objects {
    private Position posizione;
    private String label;
    public FreeSpace(int x, int y) {
        this.posizione = new Position(x, y);
        this.label=".";
    }

    @Override
    public boolean move(String direzione, Objects[][] map) {
        return true;
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
