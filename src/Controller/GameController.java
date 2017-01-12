
package Controller;

import Model.Game;
import Model.Shape;
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

import static Model.Shape.Triangle;


public class GameController implements Runnable {


    final double stationSize = 15 ;

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

    public void addViewStation(Model.Station station){

        Coordinates c = station.getCo();

        switch(station.getShape()){

            case Circle:
                Station<Circle> circleStation = new Station(new Circle(c.getX(),c.getY() , stationSize/2));
                view.addStation(circleStation);
                break;

            case Square:
                Station<Square> squareStation = new Station(new Square(c.getX(),c.getY() , stationSize));
                view.addStation(squareStation);
                break;

            case Triangle:
                Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(c.getX(),c.getY() , stationSize));
                view.addStation(triangleStation);
               break;

            case Diamond:
                Station<Diamond> diamondStation = new Station(new Diamond(c.getX(),c.getY() , stationSize));
                view.addStation(diamondStation);
                break;

            case Cross:
                Station<Cross> crossStation = new Station(new Cross(c.getX(),c.getY() , stationSize));
                view.addStation(crossStation);
                break;

            case Lozenge:
                Station<Lozenge> lozengeStation = new Station(new Lozenge(c.getX(),c.getY() , stationSize));
                view.addStation(lozengeStation);
                break;
        }

    }

}

