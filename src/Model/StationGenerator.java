
package Model;

import Controller.GameController;
import View.*;
import View.Station;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import View.View;

import static com.sun.tools.doclint.Entity.or;

/**
 * Created by Toinecoch on 9/1/17.
 */

public class StationGenerator extends Thread{

    final double widthWindow = 1024;
    final double heightWindow = 600;
    final double stationSize = 15;
    final int stationRate = 10000;
    private ArrayList<Model.Station> stations;

    Game game;
    GameController gameController;
    boolean running;

    public StationGenerator(Game game, GameController gameController){

        this.game = game ;
        this.gameController = gameController;

        stations = game.getStations();

        running = true;
    }

    @Override
    public void run(){
        while(running) {

            final Model.Station s = RandomStation();
            stations.add(s);
            gameController.addViewStation(s);

            try {
                Thread.sleep(stationRate);
            } catch (InterruptedException e) {}

        }
    }

    private boolean isMisplaced(double x, double y ){
        boolean misplaced = false;
        double distance = 0.;

        for(Model.Station s: stations){
            distance = s.getCo().distance(new Coordinates(x,y));
            if((distance < stationSize * 10)||((x < stationSize*2)&&(y< stationSize*2))){
                misplaced = true;
            }
        }
        return misplaced;
    }

    private Coordinates randCoord(){

        double x;
        double y;

        do {
            x =  stationSize + (Math.random() * (widthWindow -  stationSize));
            y = stationSize + (Math.random() * (heightWindow -  stationSize));
        } while(isMisplaced(x,y) && running );

        return new Coordinates((int)x,(int)y);
    }

    private Model.Station RandomStation(){

        Shape s = Shape.randomShape();
        Coordinates c = randCoord();
        return (new Model.Station(c,s));
    }

    public void exitLoop(){
        running = false;
    }

}
