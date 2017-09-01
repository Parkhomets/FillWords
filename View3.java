package Game;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class View3 {
    public JPanel mainPanel;
    JFrame theFrame;
    Map map;
    ArrayList<JButton> buttons = new ArrayList<>();
    //MouseListener mL = new View3.Listener();

    public View3(Map map) {
        for (int i = 0; i < map.getSize() * map.getSize(); i++) {
            buttons.add(new JButton());
        }
        this.map = map;
        int size = map.getSize();
        for (int i = 0; i < size * size; i++) {
            buttons.get(i).setText(map.getAlph().get(i));
        }
    }

    public void go(Model M) {
        MouseListener mL = new View3.Listener(map, M);
        theFrame = new JFrame("FillWord by CoolTeam");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel background = new JPanel(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        theFrame.getContentPane().add(background);
        background.add(BorderLayout.NORTH, new JLabel("Тема: 3 слова"));

        GridLayout grid = new GridLayout(map.getSize(), map.getSize());
        mainPanel = new JPanel(grid);
        mainPanel.setBackground(Color.BLUE);
        background.add(BorderLayout.CENTER, mainPanel);

        //создание поля и сразу добавление слушателей к кнопкам
        for (JButton x : buttons){
            x.addMouseListener(mL);
            mainPanel.add(x);
        }
        for (int i = 0; i< map.getSize() * map.getSize(); i++)
            buttons.get(i).setBackground(Color.GRAY);
        theFrame.setResizable(true);
        theFrame.pack();
        theFrame.setVisible(true);
    }


    class Listener implements MouseListener {
        int click;
        int X;
        int Y;
        Map map;
        ArrayList<Integer> current_b_nums = new ArrayList<>();  //массив для выделенных кнопок
        ArrayList<Integer> blocked_b_nums = new ArrayList<>();  //для заблокированных
        Model M;

        Listener(Map m, Model model){
            click = 0;
            X = 0;
            Y = 0;
            map = m;
            M = model;
        }

        public void mouseEntered(MouseEvent e) {
            //курсор мыши вошел в область наблюдаемого объекта
            if (click==1)
            {
                int newX, newY;    //не всегда нужно - внести в if
                JButton button = (JButton)e.getSource();   //не всегда нужно - внести в if
                int n = 0;
                for (int i = 0; button != buttons.get(i); i++)
                    n = i+1;
                if (blocked_b_nums.contains(n + 1)) {   //если кнопка заблочена, то выпрыгиваем
                    return;
                }
                newX = n% map.getSize()+1;            //дублирование, надо убрать   //не всегда нужно внести в if
                newY = n/map.getSize()+1;           //не всегда нужно внести в if
                if (current_b_nums.contains(n+1))
                {
                    ArrayList<Integer> temp_b_nums = new ArrayList<>();
                    for (int i = 0; i < current_b_nums.size(); i++)
                    {
                        temp_b_nums.add(current_b_nums.get(i));
                    }
                    for (int i = current_b_nums.indexOf(n+1)+1; i < current_b_nums.size(); i++)
                    {
                        buttons.get(current_b_nums.get(i)-1).setBackground(Color.GRAY);
                    }
                    current_b_nums.clear();
                    for (int i = 0; i <= temp_b_nums.indexOf(n+1); i++)
                        current_b_nums.add(temp_b_nums.get(i));
                    temp_b_nums.clear();
                    X = newX;
                    Y = newY;
                }
                else{
                    if (Math.abs(newX-X)+Math.abs(newY-Y)==1)
                    {
                        X=newX;
                        Y=newY;
                        current_b_nums.add(n+1);
                        button.setBackground(Color.yellow);
                    }
                }
            }


        }
        public void mouseClicked(MouseEvent e) {
            //выполнен щелчок мышкой на наблюдаеом объекте
        }

        public void mouseExited(MouseEvent e) {
            //курсор мыши вышел из области наблюдаемого объекта

        }

        public void mousePressed(MouseEvent e) {
            //кнопка мышши нажата в момент, когда курсор
            //находится над наблюдаемым объектом
            click = 1;
            int n = 0;
            JButton button = (JButton)e.getSource();
            for (int i = 0; button != buttons.get(i); i++)
                n = i+1;
            if (blocked_b_nums.contains(n + 1)) {   //если заблочена, то выпрыгиваем
                return;
            }
            X = n%map.getSize()+1;      //дублирование, надо убрать
            Y = n/map.getSize()+1;
            current_b_nums.add(n+1);
            button.setBackground(Color.yellow);

        }

        public void mouseReleased(MouseEvent e) {
            //кнопка мыши отпущена в момент, когда курсор
            //находится над наблюдаемым объектом
            //for (Integer x : current_b_nums){ System.out.println(x);}
            int answer = 0;
            answer = M.check(current_b_nums, map);
            Random rand = new Random();
            click = 0;
            if (answer == 1)
            {
                Color c = new Color(rand.nextInt(700)*100000);  //задаем случайный цвет - точно не желтый
                for (int i = 0; i < current_b_nums.size(); i++)
                {
                    buttons.get(current_b_nums.get(i)-1).setBackground(c); //закрышваем текущие кнопки как правильные
                    blocked_b_nums.add(current_b_nums.get(i));
                }
            }
            else{
                for (int i = 0; i< current_b_nums.size(); i++)
                    buttons.get(current_b_nums.get(i)-1).setBackground(Color.GRAY); //закрышваем текущие кнопки обратно в серый
            }
            current_b_nums.clear();
        }
    }
}