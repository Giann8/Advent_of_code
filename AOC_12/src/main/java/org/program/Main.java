package org.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BigInteger totprice = new BigInteger("0");
        String[][] input = parseMatrixFromFile(args[0]);
        System.out.println(Arrays.deepToString(input));
        Map<String, Region> regionitot = scanRegions(input);
        // System.out.println(regionitot.get("R").subregions);
        for (Region key : regionitot.values())
            for (SubRegion r : key)
              totprice=  totprice.add( BigInteger.valueOf(r.getPrice()));

        System.out.println("Total price: " + totprice + "$$$$$");
    }

    public static String[][] parseMatrixFromFile(String filePath) {
        // Initialize a scanner to read the file
        try (Scanner scanner = new Scanner(new File(filePath))) {

            // List to store rows as String arrays (split into characters)
            List<String[]> rows = new ArrayList<>();

            // Read each line from the file and split into individual characters
            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine().split(""));  // Split the line into individual characters
            }

            // Convert the List<String[]> to a 2D String array
            String[][] matrix = new String[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                matrix[i] = rows.get(i); // Populate the 2D matrix
            }

            return matrix;  // Return the constructed matrix

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;  // Return null if the file is not found
        }
    }

    public static Map<String, Region> scanRegions(String[][] matrix) {
        Map<String, Region> regionitotali = new HashMap<>();
        if (matrix == null) {
            throw new RuntimeException("Found empty matrix, closing");
        }
        int rows = 0;

        for (String[] row : matrix) {
            int columns = 0;
            for (String value : row) {
                regionitotali.computeIfAbsent(value, k -> new Region());
                regionitotali.get(value).addToSuberegion(value, rows, columns,matrix);
               // System.out.println(regionitotali.get(value).getSubregions());
                columns++;
            }
            rows++;
        }
        return regionitotali;
    }
}