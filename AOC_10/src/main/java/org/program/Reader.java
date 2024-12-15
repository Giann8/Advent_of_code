package org.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
private List<String[]> input;

public Reader(File file){
    this.input=readInput(file);
}

    public List<String[]> getInput() {
        return input;
    }

    public List<String[]> readInput(File file) {
     List<String[]> matrix=new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                matrix.add(line.split(""));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file detected");
        }

        return matrix;
    }
}
