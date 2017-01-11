
package Controller;

import Model.Game;
import Model.StationGenerator;
import Model.*;
import View.*;
import View.Clock;
import View.Station;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.util.ArrayList;


public class GameController implements Runnable {



    private Game game;

    private View view;


    public GameController(){

    }

    public void run() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setView(View view) {
        this.view = view;
    }
}

