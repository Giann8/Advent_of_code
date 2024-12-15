package org.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//ogni sottoregione
public class SubRegion implements Iterable<Plot> {
    private final List<Plot> subregion = new ArrayList<>();
    private Long totalperimeter = 0L;


    public Boolean addPlot(String plot, int riga, int colonna, String[][] matrix) {
        Plot current = new Plot(plot, riga, colonna);
        boolean nearofsomething = false;
        for (Plot alreadyin : this) {

            if (current.isNear(alreadyin, matrix)) {
                nearofsomething = true;
            }

        }


        //do other add in subregion with this;
        //System.out.println(nearofsomething);
        if (subregion.isEmpty() || nearofsomething) {
            subregion.add(current);
        }


        return nearofsomething;
    }


    public Long getPrice() {
        getTotalPerimeter();
        return totalperimeter * subregion.size();
    }

    public void getTotalPerimeter() {
        totalperimeter = 0L;
        forEach(plot -> totalperimeter += plot.getPerimeter());
    }

    @Override
    public Iterator<Plot> iterator() {
        return subregion.iterator();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        forEach(plot -> string.append(plot.getId()).append(plot.getPerimeter()).append(" "));
        return string.toString();
    }
}
