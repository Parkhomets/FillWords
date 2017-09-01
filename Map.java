package Game;
import javafx.util.Pair;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Integer>> koords;

    private int size;
    private ArrayList<String> alph;

    public Map(int size, ArrayList<ArrayList<Integer>> map, ArrayList<String> alph){
        this.size = size;
        this.koords = map;
        this.alph = alph;
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<ArrayList<Integer>> getMap(){
        return this.koords;
    }
    public ArrayList<String> getAlph(){
        return this.alph;
    }

}
