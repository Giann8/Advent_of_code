

import java.io.File;
public class Main {
    public static void main(String[] args) {
        Integer totalpassed;
        File file = new File("./InputTest.txt");

        Map map = new Map(file);

        while (!map.endGame()) {
            map.moveGuard();
        }
        totalpassed = map.calcTotPassed();

        System.out.println(totalpassed);
    }
}