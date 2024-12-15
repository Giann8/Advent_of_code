import org.jetbrains.annotations.NotNull;

public interface PointedObjects {
    @NotNull Integer getColonna();
    @NotNull Integer getRiga();
    @NotNull boolean isStatic();
     void onPassed();
    @NotNull boolean getPassed();
}
