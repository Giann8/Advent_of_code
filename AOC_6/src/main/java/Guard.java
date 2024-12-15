import org.jetbrains.annotations.NotNull;

public class Guard {
    @NotNull
    private Integer colonna;
    @NotNull
    private Integer riga;

    @NotNull
    private String direction;

    public Guard(@NotNull Integer x, @NotNull Integer y, @NotNull String direction) {
        this.colonna = x;
        this.riga = y;
        this.direction = direction;
    }

    @NotNull
    public Integer getColonna() {
        return colonna;
    }

    @NotNull
    public Integer getRiga() {
        return riga;
    }

    public void setRiga(@NotNull Integer riga) {
        this.riga = riga;
    }

    public void setColonna(@NotNull Integer colonna) {
        this.colonna = colonna;
    }

    @NotNull
    public String getDirection() {
        return direction;
    }

    public void changeDirection() {
        switch (direction) {
            case "U" -> direction = "R";
            case "R" -> direction = "D";
            case "D" -> direction = "L";
            default -> direction = "U";
        }
    }

    public EmptyPlace nextMove() {
        return switch (direction) {
            case "D" -> new EmptyPlace(colonna, riga-1);
            case "U" -> new EmptyPlace(colonna, riga+1);
            case "L" -> new EmptyPlace(colonna-1, riga);
            default -> new EmptyPlace(colonna+1, riga);
        };
    }


}
