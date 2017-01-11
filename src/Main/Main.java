package Main;

/**
 * Created by hugo on 1/6/2017.
 */

import Controller.GameController;
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

public class Main extends Application {


    static Group root = new Group();
    public ArrayList<Station> stations;
    final double widthWindow = 1024;
    final double heightWindow = 600;
    final double stationSize = 15 ;

    StationGenerator stationGenerator;

    boolean running = true;

    private Game game;

    private View view;

    public static void Main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage)
    {
        Line red = new Line();

        double vitesseTrain = 0.13;

        theStage.setTitle( "Mini Metro" );
        Canvas canvas = new Canvas( widthWindow, heightWindow );

        Clock clock = new Clock();
        clock.setPosition(widthWindow - 150, 5);
        clock.start();

        stations = new ArrayList<Station>();

        Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(400,400 , stationSize));
        stations.add(triangleStation);

        Station<Circle> circleStation = new Station(new Circle(randCoord().getX(),randCoord().getY() , stationSize/2));
        stations.add(circleStation);

        Station<Square> squareStation = new Station(new Square(randCoord().getX(),randCoord().getY() , stationSize));
        stations.add(squareStation);


        stationGenerator = new StationGenerator(stations);

        Rectangle transport = new Rectangle(triangleStation.getCenterX(),triangleStation.getCenterY(),15,8);

        triangleStation.getType().toBack();
        circleStation.getType().toBack();

        /*
        Station<Square> squareStation = new Station(new Square(randCoord().getY(), randCoord().getY(), stationSize));
        stations.add(squareStation);


        Station<Diamond> diamondStation = new Station(new Diamond(randCoord().getX() , randCoord().getY(), stationSize));
        stations.add(diamondStation);

        Station<Cross> crossStation = new Station(new Cross(randCoord().getX() , randCoord().getY() , stationSize));
        stations.add(crossStation);

        Station<Lozenge> lozengeStation = new Station(new Lozenge(randCoord().getX()  , randCoord().getY(), stationSize));
        stations.add(lozengeStation);*/


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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        theStage.show();
        stationGenerator.start();

        theStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent t) {
                stationGenerator.exitLoop();
                stationGenerator.interrupt();
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public static void addElement(Node n){
        root.getChildren().add(n);
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

    public void setGame(Game game) {
        this.game = game;
    }

    public void setView(View view) {
        this.view = view;
    }

    public  ArrayList<Station> getStations(){
        return stations;
    }

    public void addStation(Station s){
        stations.add(s);
    }

    /**
     * TODO
     */

    public void refreshDisplay(){
    }

}

