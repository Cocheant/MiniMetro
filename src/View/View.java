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
    ArrayList<Train> trains ;

    static Path redPath = new Path();
    static Path bluePath = new Path();
    static Path greenPath = new Path();


    private double widthWindow = 1024;
    private double heightWindow = 600;
    final double stationSize = 15 ;
    final double radiusStation = 15;
    double vitesseTrain = 0.13;

    static Color red = Color.TOMATO;
    static Color blue = Color.DEEPSKYBLUE;
    static Color green = Color.LIMEGREEN;
    static Color grey = Color.DARKGREY;

    Stage primaryStage;


    final Train trainHud = new Train(radiusStation * 6 + 40,0,20 );


    final Circle redLine = new Circle(0,0,radiusStation,red);
    final Circle blueLine = new Circle(radiusStation *3 , 0, radiusStation,blue);
    final Circle greenLine = new Circle(radiusStation *6 , 0, radiusStation,green);


    Group Hud = new Group(redLine,blueLine,greenLine, trainHud);



    boolean running = true;




    public View(GameController controller, double width, double height, Group root, Stage primaryStage ){

        this.controller = controller;
        this.widthWindow = width;
        this.heightWindow = height;
        this.root = root;
        this.primaryStage = primaryStage;
        this.stations = new ArrayList<Station>();
        lines = new ArrayList<MetroLine>();
        trains = new ArrayList<Train>();
        trainHud.setColor(Color.DARKGREY);
        Hud.setTranslateX(widthWindow - Hud.getBoundsInParent().getMaxX()/2);
        Hud.setTranslateY(heightWindow - Hud.getBoundsInParent().getMaxY() * 2);

        redPath.setStroke(red);
        bluePath.setStroke(blue);
        greenPath.setStroke(green);

        setEvents();

    }


    public void run() {

        Line red = new Line();



        Canvas canvas = new Canvas(widthWindow,heightWindow);


        clock = new Clock();
        clock.setPosition(widthWindow - 150, 5);

        root.getChildren().addAll( canvas, Hud,clock,redPath,bluePath,greenPath);

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

        final MetroLine ml = new MetroLine(first.getCenterX(),first.getCenterY(),second.getCenterX(),second.getCenterY(),color);

        EventHandler<MouseEvent> LineHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if((selected == red)||(selected == green)||(selected == blue) ) {
                    deleteElement(event.getSource());

                }
                else if((selected == grey)){

                    Train newTrain = new Train(ml.getStartX(),ml.getStartY(),15);
                    addTrainToStation(newTrain,ml);
                    newTrain.setColor(ml.getColor());
                    trains.add(newTrain);
                    addElement(newTrain);



                    if(ml.getColor() == red){

                        System.out.println("hello");
                        PathTransition pathTransition;
                        pathTransition = new PathTransition(Duration.millis(2000), redPath,newTrain);
                        pathTransition.setNode(newTrain);
                        pathTransition.setPath(redPath);
                        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        pathTransition.setCycleCount(Timeline.INDEFINITE);
                        pathTransition.setAutoReverse(true);

                        pathTransition.play();

                    }
                    else if(ml.getColor() == green){


                        PathTransition pathTransition;
                        pathTransition = new PathTransition(Duration.millis(2000), greenPath,newTrain);
                        pathTransition.setNode(newTrain);
                        pathTransition.setPath(greenPath);
                        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        pathTransition.setCycleCount(Timeline.INDEFINITE);
                        pathTransition.setAutoReverse(true);
                        pathTransition.play();
                    }
                    else if(ml.getColor() == blue) {
                        PathTransition pathTransition;
                        pathTransition = new PathTransition(Duration.millis(2000), bluePath,newTrain);
                        pathTransition.setNode(newTrain);
                        pathTransition.setPath(bluePath);
                        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        pathTransition.setCycleCount(Timeline.INDEFINITE);
                        pathTransition.setAutoReverse(true);
                        pathTransition.play();

                    }



                }
            }
        };

        ml.setOnMouseClicked(LineHandler);

        addElement(ml);
        lines.add(ml);

    }

    private void deleteLine(MetroLine ml){
        lines.remove(ml);
    }

    public static Color getselected() {
        return selected;
    }

    private void addTrainToStation(Train t, Line l){

    }

    public static void addStationToLine(double x,double y, Shape shape){

        if((selected != null)&&(selected!=Color.GREY) ) {
            //System.out.println(shape);
            controller.addStationToLine(new Coordinates(x, y), shape, selected);

            if(selected == red){
                if(redPath.getElements().size()==0)
                    redPath.getElements().add(new MoveTo(x,y));

                redPath.getElements().add(new LineTo(x,y));

            }
            else if(selected == green){
                if(greenPath.getElements().size()==0)
                    greenPath.getElements().add(new MoveTo(x,y));


                greenPath.getElements().add(new LineTo(x,y));
            }
            else if(selected == blue) {
                if(bluePath.getElements().size()==0)
                    bluePath.getElements().add(new MoveTo(x,y));

                bluePath.getElements().add(new LineTo(x,y));
            }



        }
    }







}
