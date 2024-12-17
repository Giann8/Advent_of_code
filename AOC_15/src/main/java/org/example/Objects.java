package org.example;

public interface Objects {
    boolean move(String direzione,Objects[][] map);
    public String getLabel();
    Position getPosizione();
    void nextPosizione(String direzione);
    Boolean getAffiancato();
}
