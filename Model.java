package Game;
import java.util.ArrayList;

public class Model {
    Map simpleMap;

    ArrayList<Integer> kot = new ArrayList<>();
    ArrayList<Integer> som = new ArrayList<>();
    ArrayList<Integer> mir = new ArrayList<>();

    public void createMap(){
        kot.add(1);
        kot.add(2);
        kot.add(5);

        som.add(3);
        som.add(6);
        som.add(9);

        mir.add(4);
        mir.add(7);
        mir.add(8);

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        temp.add(kot);
        temp.add(som);
        temp.add(mir);

        int k = 3;

        ArrayList<String> alph = new ArrayList<>();
        alph.add("К");
        alph.add("О");
        alph.add("С");
        alph.add("М");
        alph.add("Т");
        alph.add("О");
        alph.add("И");
        alph.add("Р");
        alph.add("М");

        simpleMap = new Map(k, temp, alph);
    }

    public int check(ArrayList<Integer> current_nums, Map map){
        ArrayList<ArrayList<Integer>> wordsInOurMap = map.getMap();
        for (ArrayList<Integer> x : wordsInOurMap)
            if (x.equals(current_nums))
                return 1;
        return 0;
    }

}