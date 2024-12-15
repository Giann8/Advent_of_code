import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    @NotNull
    private List<List<PointedObjects>> mappa = new ArrayList<>();
    @NotNull
    private Guard guard;

    public Map(File file) {
        this.mappa = builderMappa(file);

    }

    private List<List<PointedObjects>> builderMappa(File file) {
        List<List<PointedObjects>> buildedmap = new ArrayList<>();
        try {

            Scanner sc = new Scanner(file);
            int y = 0;
            while (sc.hasNextLine()) {
                List<PointedObjects> riga = new ArrayList<>();
                Scanner scannerline = new Scanner(sc.nextLine());
                int x = 0;
                while (scannerline.hasNext()) {
                    String punto = scannerline.next();
                    EmptyPlace emptyplace = new EmptyPlace(x, y);
                    switch (punto) {
                        case "#":
                            StaticObject staticobject = new StaticObject(x, y);
                            riga.add(staticobject);
                            break;

                        case "V":
                            this.guard = new Guard(x, y, "D");
                            riga.add(emptyplace);
                            break;

                        case "^":
                            this.guard = new Guard(x, y, "U");
                            riga.add(emptyplace);
                            break;

                        case "<":
                            this.guard = new Guard(x, y, "L");
                            riga.add(emptyplace);
                            break;

                        case ">":
                            this.guard = new Guard(x, y, "R");
                            riga.add(emptyplace);
                            break;

                        default:
                            riga.add(emptyplace);
                            break;
                    }
                    x++;
                }
                buildedmap.add(riga);
                y++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file added");
        }
        return buildedmap;
    }

    public boolean checkStep(EmptyPlace nextstep) {
        int riga = nextstep.getRiga();
        int colonna = nextstep.getColonna();
        return mappa.get(riga).get(colonna).isStatic();
    }

    public void moveGuard() {
        if (checkStep(guard.nextMove())) {
            guard.changeDirection();
            moveGuard();
        } else {
            int riga=guard.nextMove().getRiga();
            int colonna=guard.nextMove().getColonna();
            guard.setRiga(riga);
            guard.setColonna(colonna);
            mappa.get(riga).get(colonna).onPassed();
        }

    }

    public boolean endGame(){

        return guard.getColonna() == mappa.size() || guard.getColonna() == 0 || guard.getRiga() == 0 || guard.getRiga() == mappa.getFirst().size();
    }
    public Integer calcTotPassed(){
        int totalpassed=0;
        for(int i =0;i<mappa.size();i++){
            for(int j = 0; j<mappa.get(i).size();j++){
                if(mappa.get(i).get(j).getPassed()){
                    totalpassed++;
                }
            }
        }
        return totalpassed;
    }

}

