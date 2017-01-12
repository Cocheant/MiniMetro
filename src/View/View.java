package View;

import Controller.GameController;
import Model.Coordinates;
import Model.Game;
import Model.StationGenerator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by hugo on 1/6/2017.
 */
public class View implements Runnable {

    private GameController controller;

    private Clock clock;

    public Clock getClock() {
        return clock;
    }

    Scene scene;

    private static Group root ;

    static private ArrayList<Station> stations;
    private double widthWindow = 1024;
    private double heightWindow = 600;
    final double stationSize = 15 ;

    Stage primaryStage;



    boolean running = true;

    public View(GameController controller, double width, double height, Group root, Stage primaryStage ){

        this.controller = controller;
        this.widthWindow = width;
        this.heightWindow = height;
        this.root = root;
        this.primaryStage = primaryStage;

    }


    public void run() {

        Line red = new Line();

        double vitesseTrain = 0.5;

        Canvas canvas = new Canvas(widthWindow,heightWindow);



        clock = new Clock();
        /*clock.setPosition(widthWindow - 150, 5);
        clock.start();
        */

        stations = new ArrayList<Station>();

        Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(400,400 , stationSize));
        stations.add(triangleStation);

        Station<Circle> circleStation = new Station(new Circle(randCoord().getX(),randCoord().getY() , stationSize/2));
        stations.add(circleStation);

        Station<Square> squareStation = new Station(new Square(randCoord().getX(),randCoord().getY() , stationSize));
        stations.add(squareStation);




        Rectangle transport = new Rectangle(triangleStation.getCenterX(),triangleStation.getCenterY(),15,8);

        triangleStation.getType().toBack();
        circleStation.getType().toBack();



        Path path = new Path();
        path.getElements().add(new MoveTo(triangleStation.getCenterX(),triangleStation.getCenterY()));
        PathTransition pathTransition = new PathTransition();
        path.getElements().add(new LineTo(triangleStation.getCenterX(),triangleStation.getCenterY()));
        path.getElements().add(new LineTo(circleStation.getCenterX(), circleStation.getCenterY()));
        path.getElements().add(new LineTo(squareStation.getCenterX(),squareStation.getCenterY()));
        pathTransition.setDuration(Duration.millis(Math.sqrt((triangleStation.getCenterX() - circleStation.getCenterX())*(triangleStation.getCenterX() - circleStation.getCenterX())
                +(triangleStation.getCenterY() - circleStation.getCenterY()) *(triangleStation.getCenterY() - circleStation.getCenterY()))/ vitesseTrain));


        pathTransition.setPath(path);
        pathTransition.setNode(transport);

        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        root.getChildren().addAll( canvas, clock,triangleStation.getType(), circleStation.getType(),transport,path, squareStation.getType());

        scene = new Scene(root, widthWindow, heightWindow);
        primaryStage.setTitle("Mini Metro!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public  ArrayList<Station> getStations(){
        return stations;
    }

    public static void addStation(Station s){
        stations.add(s);
        addElement(s.getType());
    }

    static void addElement(final Node n){
        Platform.runLater(new Runnable() {
            public void run() {
                root.getChildren().add(n);
            }
        });
    }

    public static void deleteElement(Node n){
        root.getChildren().remove(n);
    }

    private boolean isMisplaced(double x, double y ){
        boolean misplaced = false;
        double distance = 0.;

        for(Station s:stations){
            distance = Math.sqrt(Math.abs(s.getCenterX()-x)*Math.abs(s.getCenterX()-x)+ Math.abs(s.getCenterY()-y)*Math.abs(s.getCenterY()-y));
            if((distance < stationSize * 5)||((x < stationSize)&&(y< stationSize))){
                misplaced = true;
            }
        }
        return misplaced;
    }

    private Coordinates randCoord(){

        double x = 0;
        double y = 0;


        while(isMisplaced(x,y)) {
            x =  stationSize + (Math.random() * (widthWindow - stationSize));
            y = stationSize + (Math.random() * (heightWindow - stationSize));
        }

        return new Coordinates((int)x,(int)y);
    }
    /**
     * TODO
     */
    public void refresh(){

    }

}
