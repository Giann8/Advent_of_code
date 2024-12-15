import org.jetbrains.annotations.NotNull;

public class StaticObject implements PointedObjects{
    @NotNull private Integer colonna;
    @NotNull private Integer riga;


    public StaticObject(Integer x, Integer y){
        this.colonna=x;
        this.riga=y;
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
        return true;
    }

    @Override
    public void onPassed() {
    }

    @Override
    public @NotNull boolean getPassed() {
        return false;
    }
}
