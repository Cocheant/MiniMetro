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

    private static Color selected;

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
    Color grey = Color.DARKGREY;

    Stage primaryStage;

    //(widthWindow /2) - 6 *radiusStation ,heightWindow - 4 * radiusStation ,

    final Train trainHud = new Train(radiusStation * 6 + 40,0,20 );


    final Circle redLine = new Circle(0,0,radiusStation,red);
    final Circle blueLine = new Circle(radiusStation *3 , 0, radiusStation,blue);
    final Circle greenLine = new Circle(radiusStation *6 , 0, radiusStation,green);



    Group Hud = new Group(redLine,blueLine,greenLine, trainHud);



    boolean running = true;

    EventHandler<MouseEvent> deleteLineHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            if((selected == red)||(selected == green)||(selected == blue) ) {
                deleteElement(event.getSource());
            }
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

        trainHud.setColor(Color.DARKGREY);
        Hud.setTranslateX(widthWindow/2 - Hud.getBoundsInParent().getMaxX()/2);
        Hud.setTranslateY(heightWindow - Hud.getBoundsInParent().getMaxY() * 2);




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





        root.getChildren().addAll( canvas, Hud,clock);

        scene = new Scene(root, widthWindow, heightWindow);
        primaryStage.setTitle("Mini Metro!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public  ArrayList<Station> getStations(){
        return stations;
    }

    public void addStation(Station s){
        stations.add(s);
        addElement(s.getGroup());
        //addElement(s.getType());
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

        redLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selected == red){
                    selected = null;
                    selected = Color.TRANSPARENT;
                    redLine.setStroke(Color.TRANSPARENT);
                }
                else if(selected == Color.TRANSPARENT){
                    selected = red;
                    redLine.setStroke(Color.BLACK);
                    redLine.setStrokeWidth(4);
                }
                else {
                    selected = red;
                    redLine.setStroke(Color.BLACK);
                    redLine.setStrokeWidth(4);
                    blueLine.setStroke(Color.TRANSPARENT);
                    greenLine.setStroke(Color.TRANSPARENT);
                    trainHud.setStroke(Color.TRANSPARENT);
                }

            }
        });

        blueLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selected == blue){
                    selected = null;
                    selected = Color.TRANSPARENT;
                    blueLine.setStroke(Color.TRANSPARENT);
                }
                else if(selected == Color.TRANSPARENT){
                    selected = blue;
                    blueLine.setStroke(Color.BLACK);
                    blueLine.setStrokeWidth(4);
                }
                else {
                    selected = blue;
                    blueLine.setStroke(Color.BLACK);
                    blueLine.setStrokeWidth(4);
                    redLine.setStroke(Color.TRANSPARENT);
                    greenLine.setStroke(Color.TRANSPARENT);
                    trainHud.setStroke(Color.TRANSPARENT);
                }
            }
        });

        greenLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(selected == green){
                    selected = null;
                    selected = Color.TRANSPARENT;
                    greenLine.setStroke(Color.TRANSPARENT);
                }
                else if(selected == Color.TRANSPARENT){
                    selected = green;
                    greenLine.setStroke(Color.BLACK);
                    greenLine.setStrokeWidth(4);
                }
                else {
                    selected = green;
                    greenLine.setStroke(Color.BLACK);
                    greenLine.setStrokeWidth(4);
                    redLine.setStroke(Color.TRANSPARENT);
                    blueLine.setStroke(Color.TRANSPARENT);
                    trainHud.setStroke(Color.TRANSPARENT);
                }
            }
        });

        trainHud.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                
                if((selected != null)&&(selected!=grey)){
                    greenLine.setStroke(Color.TRANSPARENT);
                    redLine.setStroke(Color.TRANSPARENT);
                    blueLine.setStroke(Color.TRANSPARENT);
                    trainHud.setStroke(Color.BLACK);
                    trainHud.setStrokeWidth(4);
                    selected = grey;
                }
                else if(selected == grey){
                    trainHud.setStroke(Color.TRANSPARENT);
                    selected = null;
                }
                else {
                    selected = grey;
                    greenLine.setStroke(Color.TRANSPARENT);
                    redLine.setStroke(Color.TRANSPARENT);
                    blueLine.setStroke(Color.TRANSPARENT);
                    trainHud.setStroke(Color.BLACK);
                    trainHud.setStrokeWidth(4);
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

    public static Color getselected() {
        return selected;
    }

    public static void addStationToLine(double x,double y, Shape shape){
        if((selected != null)&&(selected!=Color.GREY) ) {
            controller.addStationToLine(new Coordinates(x, y), shape, selected);
        }
    }



}
