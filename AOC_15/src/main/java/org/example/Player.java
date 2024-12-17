package org.example;

import java.util.List;

public class Player extends MovableObjects {
    private final String label;

    public Player(int x, int y) {
        super(x, y);
        this.label = "@";
    }


    @Override
    public String getLabel() {
        return label;
    }

}
