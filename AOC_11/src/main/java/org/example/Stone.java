package org.example;

import javax.management.monitor.StringMonitor;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Stone {
    private String numero;

    public Stone(String numero) {
        this.numero = String.valueOf(Long.parseLong(numero));
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String multStone() {
        numero = Long.toString(Long.parseLong(numero) * 2024L);
        return numero;
    }

    public String splitStone() {
        /*if (splits.get(this.getNumero()) != null) {
            String substring = splits.get(this.getNumero())[1];
            numero = splits.get(this.getNumero())[0];
            return substring;
        }
        */

        String[] arr = new String[2];
        List<String> cifre = new ArrayList<>(Arrays.asList(numero.split("")));

        List<String> substring = new ArrayList<>();
        for (int i = 0; i < cifre.size() / 2; i++) {

            substring.add(cifre.get(i));
        }
        for (String s : substring) {
            cifre.remove(s);
        }

        arr[0] = String.join("", substring);
        //System.out.print( String.join("",substring)+" ");
        arr[1] = String.join("", cifre);
        // System.out.println( String.join("",cifre));
      //  splits.put(this.getNumero(), arr);

        numero = arr[1];

        return arr[0];
    }

    public void safeMult() {
        numero = String.valueOf(Long.parseLong(numero));
    }


}
