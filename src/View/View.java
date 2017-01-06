package View;

import Controller.GameController;
import javafx.scene.Group;

/**
 * Created by hugo on 1/6/2017.
 */
public class View {

    private GameController controller;

    static Group root ;

    public View(GameController controller, Group root){
        this.controller = controller;
        this.root = root;
    }


    /**
     * TODO
     */
    public void refresh(){

    }
}
