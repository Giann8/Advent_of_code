package org.principal;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final String parsedoutput;
    private final boolean changedalternate;

    public Parser(String input, Integer counter, boolean alternate) {
        this.parsedoutput = createBlocks(input, counter, alternate);
        this.changedalternate=!alternate;
    }

    public String createBlocks(String input, Integer counter, boolean alternate) {

        StringBuilder block = new StringBuilder();
      //  System.out.println(input);
        for (int i = 0; i < Integer.parseInt(input); i++) {
            if (alternate) {

                block.append(".").append(" ");
            } else {
                block.append(counter).append(" ");
                //System.out.println("block is: "+block);
            }
        }

        return block.toString();
    }
    public boolean alternateChange(){
        return changedalternate;
    }

    public String getParsedoutput() {
        return this.parsedoutput;
    }


}
