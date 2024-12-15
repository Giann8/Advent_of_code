package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Stones implements Iterable<Stone> {
    private Integer repeats = 0;
    private List<Stone> stones = new ArrayList<>();
    private final Map<String, MulNumber> mults = new HashMap<>();
    private final Map<String, String[]> splits = new HashMap<>();

    public Stones(File file) {
        this.stones = ParseInput(file);
    }

    public Map<String, Long> toMap() {
        Map<String, Long> mappa = new HashMap<>();
        forEach(stone -> {
            if (mappa.containsKey(stone.getNumero())) {
                mappa.replace(stone.getNumero(), mappa.get(stone.getNumero()) + 1);
            } else {
                mappa.put(stone.getNumero(),  1L);
            }
        });
        return mappa;
    }

    public BigInteger getLength(Map<String, Long> mappa) {
        BigInteger tot = new BigInteger("0");
        for (String s : mappa.keySet()) {
           tot= tot.add(BigInteger.valueOf(mappa.get(s)));
        }
        return tot;
    }

    public void printStones() {
        stones.forEach(stone -> System.out.print(stone.getNumero() + " "));
    }

    public void blinkList(Map<String, Long> mappa) {

        Map<String, Long> nextmap = new HashMap<>();
        System.out.println("Blinked " + (repeats) + " times");
        // List<Stone> copy = List.copyOf(stones);

        // System.out.println(mappa);

        for (String numero : mappa.keySet()) {
            Stone stone = new Stone(numero);
            if (Objects.equals(stone.getNumero(), "0")) {

                stone.setNumero("1");

            } else if (stone.getNumero().length() % 2 == 0) {

                Stone newstone = new Stone(stone.splitStone());
                newstone.safeMult();
                // stones.add(newstone);
                if (nextmap.containsKey(newstone.getNumero())) {
                    nextmap.replace(newstone.getNumero(), nextmap.get(newstone.getNumero()) + mappa.get(numero));
                } else {
                    nextmap.put(newstone.getNumero(), mappa.get(numero));
                }

            } else {
                stone.multStone();
            }

            stone.safeMult();
            if (nextmap.containsKey(stone.getNumero())) {
                nextmap.replace(stone.getNumero(), nextmap.get(stone.getNumero()) + mappa.get(numero));
            } else {
                nextmap.put(stone.getNumero(), mappa.get(numero));
            }


        }
        if (repeats < 75) {
            System.out.println("-- LENGTH: " + getLength(nextmap) + " --");
            // checkZeros(mults);
            repeats++;
            // printStones();
            // System.out.println();
            blinkList(nextmap);
            // System.out.println(repeats);
        }
    }


       /* for (Stone stone : copy) {
            String key = stone.getNumero();
            if (Objects.equals(stone.getNumero(), "0")) {

                stone.setNumero("1");

            } else if (stone.getNumero().length() % 2 == 0) {

                Stone newstone = new Stone(stone.splitStone(splits));
                newstone.safeMult();
                stones.add(newstone);

            } else {
                if (!mults.containsKey(key)) {

                    mults.put(key, new MulNumber(stone.multStone(), 3));
                    // System.out.println(mults.get(key)+key);
                    continue;
                }

                // System.out.println(mults.get(key) + key);

                stone.setNumero(mults.get(stone.getNumero()).numero());

                //  System.out.println(mults);

            }
            stone.safeMult();
        }

        if (repeats < 1) {
            // checkZeros(mults);
            repeats++;
            // printStones();
            // System.out.println();
            blinkList();
            // System.out.println(repeats);
        }
    }*/

    private void checkZeros(Map<String, MulNumber> mults) {
        mults.forEach((s, mulNumber) -> mulNumber.decrease());
        mults.values().removeIf(mulNumber -> mulNumber.counter == 0);
    }

    private List<Stone> ParseInput(File file) {

        List<Stone> stones = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] stringa = sc.nextLine().split(" ");
                for (String s : stringa) {
                    Stone stone = new Stone(s);
                    stones.add(stone);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file detected");
        }
        return stones;


    }

    public record MulNumber(String numero, Integer counter) {

        public MulNumber decrease() {
            return new MulNumber(numero, counter - 1);
        }

    }

    @Override
    public Iterator<Stone> iterator() {
        return List.copyOf(stones).iterator();
    }

    public Iterator<String> multsIterator() {
        return mults.keySet().iterator();
    }
}
