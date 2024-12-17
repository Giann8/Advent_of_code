package org.example;

public record Position(int x, int y) {


    public Position nextPosition(String direzione) {
        return switch (direzione) {
            case ">" -> new Position(x + 1, y);
            case "^" -> new Position(x, y - 1);
            case "<" -> new Position(x - 1, y);
            case "v" -> new Position(x, y + 1);
            default -> null;
        };
    }
}
