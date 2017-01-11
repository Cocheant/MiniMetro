package View;

import Controller.GameController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Created by hugo on 1/6/2017.
 */
public class View implements Runnable {

    private GameController controller;

    private double width;
    private double height;

    private Stage stage;

    static Group root ;

    public View(GameController controller, Group root, double width, double height, Stage stage){
        this.controller = controller;
        this.root = root;
        this.width = width;
        this.height = height;
        this.stage = stage;
    }


    /**
     * TODO
     */
    public void refresh(){

    }

    public void run() {
        stage.setTitle( "Mini Metro" );
        Canvas canvas = new Canvas( width, height );

        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        stage.show();
    }
}
