package Game;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Integer>> koords;
    private Model model;
    private int size;
    private ArrayList<String> letters;

    public Map(int size, ArrayList<ArrayList<Integer>> map, ArrayList<String> letters, Model model){
        this.size = size;
        this.koords = map;
        this.letters = letters;
        this.model = model;
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<ArrayList<Integer>> getMap(){
        return this.koords;
    }
    public ArrayList<String> getLetters(){
        return this.letters;
    }

    public Model gatModel(){
        return this.model;
    }

}
