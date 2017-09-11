package Game;


public class Presenter {
    public static Model mainModel;
    public static void main(String[] args) {
        mainModel = new Model();
        mainModel.loadMap();
        Map mainMap = mainModel.simpleMap;
        View3 view = new View3(mainMap);
        view.go();


    }
}
