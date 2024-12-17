package org.example;

public class Chest extends MovableObjects {
    private final String label;

    public Chest(int x, int y, String label) {
        super(x, y);
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }


}
