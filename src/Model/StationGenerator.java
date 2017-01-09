package Model;

import Controller.GameController;
import Model.Coordinates;
import View.*;
import View.Station;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.shape.Circle;

import java.lang.management.GarbageCollectorMXBean;

/**
 * Created by Toinecoch on 9/1/17.
 */
public class StationGenerator extends Thread{

    final double widthWindow = 1024;
    final double heightWindow = 600;
    final double stationSize = 15 ;
    final int stationRate = 30000;
    private GameController app;
    boolean running;

    public StationGenerator(GameController a){
        app = a;
        running = true;
    }

    @Override
    public void run(){
        while(running){

            final Station s = RandomStation();

            Platform.runLater(new Runnable() {
                public void run() {
                    GameController.addElement(s.getType());
                    app.addStation(s);
                }
            });
            try {
                Thread.sleep(stationRate);
            } catch (InterruptedException e) {}
        }
    }
    private boolean isMisplaced(double x, double y ){
        boolean misplaced = false;
        double distance = 0.;

        for(Station s: app.getStations()){
            distance = new Coordinates(s.getCenterX(),s.getCenterY()).distance(new Coordinates(x,y));
            if((distance < stationSize * 10)||((x < stationSize)&&(y< stationSize))){
                misplaced = true;
            }
        }
        return misplaced;
    }

    private Coordinates randCoord(){

        double x = 0;
        double y = 0;


        do {
            x =  stationSize + (Math.random() * (widthWindow -  stationSize));
            y = stationSize + (Math.random() * (heightWindow -  stationSize));
        } while(isMisplaced(x,y));

        return new Coordinates((int)x,(int)y);
    }

    private Station RandomStation(){

        int rand =  (int) (1 + (Math.random() * 8)  );
        Shape s = Shape.set(rand);

        Coordinates c = randCoord();

        switch(s){

            case Circle:
                Station<Circle> circleStation = new Station(new Circle(c.getX(),c.getY() , stationSize/2));
                return circleStation;

            case Square:
                Station<Square> squareStation = new Station(new Square(c.getX(),c.getY() , stationSize));
                return squareStation;

            case Triangle:
                Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(c.getX(),c.getY() , stationSize));
                return triangleStation;

            case Diamond:
                Station<Diamond> diamondStation = new Station(new Diamond(c.getX(),c.getY() , stationSize));
                return diamondStation;

            case Cross:
                Station<Cross> crossStation = new Station(new Cross(c.getX(),c.getY() , stationSize));
                return crossStation;

            case Lozenge:
                Station<Lozenge> lozengeStation = new Station(new Lozenge(c.getX(),c.getY() , stationSize));
                return lozengeStation;

        }

        return null;
    }

    public void exitLoop(){
        running = false;
    }


}
