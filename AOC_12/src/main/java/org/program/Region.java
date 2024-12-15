package org.program;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Region implements Iterable<SubRegion> {
    public List<SubRegion> subregions = new ArrayList<>();

    public List<SubRegion> getSubregions() {
        return subregions;
    }

    public void addToSuberegion(String plot, int riga, int colonna, String[][] matrix) {
        boolean added = false;
        for (SubRegion subregion : subregions) {
            if (added) {
                break;
            }
            added = subregion.addPlot(plot, riga, colonna, matrix);

        }
       /* if (!added) {
            for (SubRegion subregion : subregions) {
                if (added) {
                    break;
                }
            added = subregion.secondChance(plot, riga, colonna, matrix);
            }
        }*/
        if (!added) {
            SubRegion newsubregion = new SubRegion();
            newsubregion.addPlot(plot, riga, colonna, matrix);
            subregions.add(newsubregion);
        }

            System.out.println(subregions.toString() + subregions.getFirst().getPrice() + " " + subregions.getLast().getPrice());

    }

    @Override
    public Iterator<SubRegion> iterator() {
        return Collections.unmodifiableList(subregions).iterator();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        forEach(plots -> string.append(plots.toString()).append(" "));
        return string.toString();
    }
}
