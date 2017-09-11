package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Model {
    Map simpleMap;

    ArrayList<Integer> kot = new ArrayList<>();
    ArrayList<Integer> som = new ArrayList<>();
    ArrayList<Integer> mir = new ArrayList<>();



    public int check(ArrayList<Integer> current_nums, Map map){
        ArrayList<ArrayList<Integer>> wordsInOurMap = map.getMap();
        for (ArrayList<Integer> x : wordsInOurMap)
            if (x.equals(current_nums))
                return 1;
        return 0;
    }


    public void loadMap(){
        StringBuffer file = new StringBuffer();
        File fileDir = new File("C:\\Users\\Samsung\\IdeaProjects\\DreamTeamGame\\src\\Game\\1.txt");
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8")))
        //try(FileInputStream fin=new FileInputStream("C:\\Users\\Samsung\\IdeaProjects\\DreamTeamGame\\src\\Game\\1.txt"))
        {
            int i=-1;
            while((i=in.read())!=-1){

                file.append((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        //System.out.println(file);
        int position;
        for (position = 0; file.charAt(position)!='\n'; position++) {
        }
        char[] fieldsize = new char[position];
        file.getChars(0, position, fieldsize, 0);
        int size = Character.getNumericValue(fieldsize[0]);
        ArrayList<String> letters = new ArrayList<>();
        for (position = position+1; file.charAt(position)!='\n'; position++)
        {
            letters.add(String.valueOf(file.charAt(position)));
        }
        //for (int i = 0; i< letters.size(); i++)
            //System.out.println(letters.get(i));
        ArrayList<ArrayList<Integer>> cor_combinations = new ArrayList<>();
        while (position < file.length()-1)  //XYN знает почему -1
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for (position = position +1; (file.charAt(position+1)!='\n'); position++)
            {
                temp.add(Character.getNumericValue(file.charAt(position)));
            }
            cor_combinations.add(temp);
            position++;
        }
        simpleMap = new Map(size, cor_combinations, letters, this);
    }
}