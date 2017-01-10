/**
 * Created by hugo on 1/6/2017.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import View.View;
import Model.Game;
import Controller.GameController;

public class main extends Application {

    static Group root = new Group();

    private final double widthWindow = 1024.0;
    private final double heightWindow = 600.0;
    private final double stationSize = 15 ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GameController controller = new GameController();
        Game game = new Game(controller);
        controller.setGame(game);
        View view = new View(controller, root, widthWindow, heightWindow, primaryStage);
        controller.setView(view);

        game.run();
        controller.run();
        view.run();

    }
}
