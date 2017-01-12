/**
 * Created by hugo on 1/6/2017.
 */

import Model.StationGenerator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import View.View;
import Model.Game;
import Controller.GameController;
import javafx.stage.WindowEvent;

public class main extends Application {


    Group root = new Group();

    private final double widthWindow = 1024.0;
    private final double heightWindow = 600.0;
    private final double stationSize = 15 ;

    StationGenerator stationGenerator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GameController controller = new GameController();

        Game game = new Game(controller);
        controller.setGame(game);
        View view = new View(controller, widthWindow, heightWindow, root, primaryStage);
        controller.setView(view);

        game.run();
        controller.run();
        view.run();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {

                //stationGenerator.exitLoop();
                //stationGenerator.interrupt();
                Platform.exit();

            }
        });

    }
}
