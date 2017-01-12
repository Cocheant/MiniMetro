package View;

import Controller.GameController;
import Model.Coordinates;
import Model.Game;
import Model.Shape;
import Model.StationGenerator;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hugo on 1/6/2017.
 */
public class View implements Runnable {

    private static GameController controller;

    private static Color selectedLine;

    private Clock clock = new Clock();

    public Clock getClock() {
        return clock;
    }

    Scene scene;

    private static Group root ;

    static private ArrayList<Station> stations;

    ArrayList<MetroLine> lines;


    private double widthWindow = 1024;
    private double heightWindow = 600;
    final double stationSize = 15 ;
    final double radiusStation = 15;
    double vitesseTrain = 0.13;

    Color red = Color.TOMATO;
    Color blue = Color.DEEPSKYBLUE;
    Color green = Color.LIMEGREEN;

    Stage primaryStage;

    //(widthWindow /2) - 6 *radiusStation ,heightWindow - 4 * radiusStation ,



    final Circle redStation = new Circle(0,0,radiusStation,red);
    final Circle blueStation = new Circle(radiusStation *3 , 0, radiusStation,blue);
    final Circle greenStation = new Circle(radiusStation *6 , 0, radiusStation,green);



    Group stationsHud = new Group(redStation,blueStation,greenStation);


    boolean running = true;

    EventHandler<MouseEvent> deleteLineHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            deleteElement(event.getSource());
            //controller.deleteLine(event.getSource());
        }
    };


    public View(GameController controller, double width, double height, Group root, Stage primaryStage ){

        this.controller = controller;
        this.widthWindow = width;
        this.heightWindow = height;
        this.root = root;
        this.primaryStage = primaryStage;
        this.stations = new ArrayList<Station>();
        lines = new ArrayList<MetroLine>();

        stationsHud.setTranslateX(widthWindow/2 - stationsHud.getBoundsInParent().getMaxX()/2);
        stationsHud.setTranslateY(heightWindow - stationsHud.getBoundsInParent().getMaxY() * 2);



        setEvents();

    }


    public void run() {

        Line red = new Line();



        Canvas canvas = new Canvas(widthWindow,heightWindow);


        clock = new Clock();
        clock.setPosition(widthWindow - 150, 5);




        /*clock.setPosition(widthWindow - 150, 5);
        clock.start();
        */

        //stations = new ArrayList<Station>();

        final Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(400,400 , stationSize));
        stations.add(triangleStation);

        final Station<Circle> circleStation = new Station(new Circle(randCoord().getX(),randCoord().getY() , stationSize/2));
        stations.add(circleStation);

        final Station<Square> squareStation = new Station(new Square(randCoord().getX(),randCoord().getY() , stationSize));
        stations.add(squareStation);


        triangleStation.getType().toBack();
        circleStation.getType().toBack();

        /*final Train train = new Train(triangleStation.getCenterX(),triangleStation.getCenterY(),15);
        train.setColor(Color.BLUE);

        final Path path = new Path();
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(2);
        path.getElements().add(new MoveTo(triangleStation.getCenterX(),triangleStation.getCenterY()));
        final PathTransition pathTransition = new PathTransition();
        path.getElements().add(new LineTo(triangleStation.getCenterX(),triangleStation.getCenterY()));
        path.getElements().add(new LineTo(circleStation.getCenterX(), circleStation.getCenterY()));
        pathTransition.setDuration(Duration.millis(Math.sqrt((triangleStation.getCenterX() - circleStation.getCenterX())*(triangleStation.getCenterX() - circleStation.getCenterX())
                +(triangleStation.getCenterY() - circleStation.getCenterY()) *(triangleStation.getCenterY() - circleStation.getCenterY()))/ vitesseTrain));

        pathTransition.setNode(train);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        final Path path2 = new Path();


        path2.getElements().add(new MoveTo(circleStation.getCenterX(),circleStation.getCenterY()));
        path2.getElements().add(new LineTo(circleStation.getCenterX(), circleStation.getCenterY()));
        path2.getElements().add(new LineTo(squareStation.getCenterX(), squareStation.getCenterY()));


        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pathTransition.setNode(train);
                pathTransition.setPath(path2);
                pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                //pathTransition.setCycleCount(Timeline.INDEFINITE);
                //pathTransition.setAutoReverse(true);
                pathTransition.play();

            }
        });*/





        root.getChildren().addAll( canvas, stationsHud,clock,triangleStation.getType(), circleStation.getType(), squareStation.getType());

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

    public static void deleteElement(Object n){
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

    private void setEvents(){

        redStation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selectedLine == red){
                    selectedLine = Color.TRANSPARENT;
                    redStation.setStroke(Color.TRANSPARENT);
                }
                else if(selectedLine == Color.TRANSPARENT){
                    selectedLine = red;
                    redStation.setStroke(Color.BLACK);
                    redStation.setStrokeWidth(4);
                }
                else {
                    selectedLine = red;
                    redStation.setStroke(Color.BLACK);
                    redStation.setStrokeWidth(4);
                    blueStation.setStroke(Color.TRANSPARENT);
                    greenStation.setStroke(Color.TRANSPARENT);
                }

            }
        });

        blueStation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selectedLine == blue){
                    selectedLine = Color.TRANSPARENT;
                    blueStation.setStroke(Color.TRANSPARENT);
                }
                else if(selectedLine == Color.TRANSPARENT){
                    selectedLine = blue;
                    blueStation.setStroke(Color.BLACK);
                    blueStation.setStrokeWidth(4);
                }
                else {
                    selectedLine = blue;
                    blueStation.setStroke(Color.BLACK);
                    blueStation.setStrokeWidth(4);
                    redStation.setStroke(Color.TRANSPARENT);
                    greenStation.setStroke(Color.TRANSPARENT);
                }
            }
        });

        greenStation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selectedLine == green){
                    selectedLine = Color.TRANSPARENT;
                    greenStation.setStroke(Color.TRANSPARENT);
                }
                else if(selectedLine == Color.TRANSPARENT){
                    selectedLine = green;
                    greenStation.setStroke(Color.BLACK);
                    greenStation.setStrokeWidth(4);
                }
                else {
                    selectedLine = green;
                    greenStation.setStroke(Color.BLACK);
                    greenStation.setStrokeWidth(4);
                    redStation.setStroke(Color.TRANSPARENT);
                    blueStation.setStroke(Color.TRANSPARENT);
                }
            }
        });




    }

    public void printLine(Station first, Station second, Color color){
        MetroLine ml = new MetroLine(first.getCenterX(),first.getCenterY(),second.getCenterX(),second.getCenterY(),color);
        ml.setOnMouseClicked(deleteLineHandler);
        addElement(ml);
        lines.add(ml);
    }

    private void deleteLine(MetroLine ml){
        lines.remove(ml);
    }

    public static Color getSelectedLine() {
        return selectedLine;
    }

    public static void addStationToLine(double x,double y, Shape shape){
        controller.addStationToLine(new Coordinates(x,y), shape , selectedLine);
    }



}
