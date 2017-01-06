package Controller;
import Model.Game;
import Model.*;
import View.*;
import View.Clock;
import View.Station;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.StrictMath.sqrt;
import static java.lang.Thread.sleep;


public class GameController extends Application {

     static Group root = new Group();
     ArrayList<Station> stations;
    final double widthWindow = 1024;
    final double heightWindow = 600;
    final double stationSize = 30 ;

    boolean running = true;

    Task <Void> task = new Task<Void>() {

        @Override
        public Void call() throws InterruptedException {

            while(running) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                Square s = new Square(randCoord().getX(), randCoord().getY(), stationSize);
                root.getChildren().add((new Station<Square>(s)).getType());
            }
            return null;

        }
    };


     private Game game;

     private View view;

    public static void main(String[] args) {
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

        Station<Square> squareStation = new Station(new Square(randCoord().getY(), randCoord().getY(), stationSize));
        stations.add(squareStation);

        /*
        Station<Diamond> diamondStation = new Station(new Diamond(randCoord().getX() , randCoord().getY(), stationSize));
        stations.add(diamondStation);

        Station<Cross> crossStation = new Station(new Cross(randCoord().getX() , randCoord().getY() , stationSize));
        stations.add(crossStation);

        Station<Lozenge> lozengeStation = new Station(new Lozenge(randCoord().getX()  , randCoord().getY(), stationSize));
        stations.add(lozengeStation);*/

        /*
        Path path = new Path();
        path.getElements().add(new MoveTo(xCenterTri,yCenterTri));
        PathTransition pathTransition = new PathTransition();
        path.getElements().add(new LineTo(xCenterTri,yCenterTri));
        path.getElements().add(new LineTo(xCenterCir, yCenterCir));
        pathTransition.setDuration(Duration.millis(sqrt((xCenterTri - xCenterCir)*(xCenterTri - xCenterCir)
                                                    +(yCenterTri - yCenterCir) *(yCenterTri - yCenterCir))/
                                                    vitesseTrain));
        pathTransition.setPath(path);
        pathTransition.setNode(transport);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        //pathTransition.play();

        */





        root.getChildren().addAll( canvas, clock,triangleStation.getType(), circleStation.getType(),squareStation.getType());

                //,diamondStation.getType(),crossStation.getType(),lozengeStation.getType());

        GraphicsContext gc = canvas.getGraphicsContext2D();

        /*Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();*/

        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        theStage.show();


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

    /**
     * TODO
     */
    public void refreshDisplay(){

    }
}

