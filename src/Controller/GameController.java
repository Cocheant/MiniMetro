package Controller;

import Model.Game;
import View.*;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.StrictMath.sqrt;


public class GameController extends Application {

     static Group root = new Group();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage)
    {

        final double widthWindow = 1024;
        final double heightWindow = 800;

        Line red = new Line();

        double vitesseTrain = 0.13;

        theStage.setTitle( "Mini Metro" );
        Canvas canvas = new Canvas( widthWindow, heightWindow );

        Clock clock = new Clock();
        clock.setPosition(widthWindow - 150, 5);
        clock.start();

        Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(400,400 , 30));
        Station<Circle> circleStation = new Station(new Circle(600,600 , 15));
        Station<Square> squareStation = new Station(new Square(400, 600, 30));
        Station<Diamond> diamondStation = new Station(new Diamond(300 , 300, 30));
        Station<Cross> crossStation = new Station(new Cross(800, 600 , 30));
        Station<Lozenge> lozengeStation = new Station(new Lozenge(300 , 500, 30));

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

        root.getChildren().addAll( canvas, clock,triangleStation.getType(), circleStation.getType(),squareStation.getType(),
                diamondStation.getType(),crossStation.getType(),lozengeStation.getType());


        GraphicsContext gc = canvas.getGraphicsContext2D();


        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        theStage.show();

        Game game = new Game();
    }

    public static void addElement(Node n){
        root.getChildren().add(n);
    }
}

