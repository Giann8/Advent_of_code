import org.jetbrains.annotations.NotNull;

public class EmptyPlace implements PointedObjects{

    @NotNull private Integer colonna;
    @NotNull private Integer riga;
    @NotNull private Boolean passed = false;

    public EmptyPlace(@NotNull Integer x, @NotNull Integer y){
        this.colonna=x;
        this.riga =y;
    }
    public EmptyPlace(@NotNull Integer x, @NotNull Integer y, @NotNull Boolean passed){
        this.colonna=x;
        this.riga=y;
        this.passed=passed;
    }

    @Override
    public @NotNull Integer getColonna() {
        return colonna;
    }

    @Override
    public @NotNull Integer getRiga() {
        return riga;
    }

    @Override
    public  boolean isStatic() {
        return false;
    }

    public void onPassed(){
        this.passed=true;
    }

    @Override
    public  boolean getPassed() {
        return passed;
    }
}
