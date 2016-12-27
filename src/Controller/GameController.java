package Controller;

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

        //TODO Create a class for each station (square, circle, etc) and put their events inside

        // Test

        final double widthWindow = 1024;
        final double heightWindow = 800;

        double vitesseTrain = 0.13;
        /*double xCenterTri =800;
        double yCenterTri = 400;
        double tailleTri = 40;

        double xCenterCir = 120;
        double yCenterCir = 120;
        double diameter = 30;*/


        theStage.setTitle( "Mini Metro" );
        Canvas canvas = new Canvas( widthWindow, heightWindow );

        Clock clock = new Clock();
        clock.setPosition(widthWindow - 150, 5);
        clock.start();
        /*


        final Diamond diamond = new Diamond(300 , 300, 30);
        diamond.setFill(Color.TRANSPARENT);
        diamond.setStroke(Color.BLACK);
        diamond.setStrokeWidth(3);

        final Lozenge lozenge = new Lozenge(300 , 500, 30);
        lozenge.setFill(Color.TRANSPARENT);
        lozenge.setStroke(Color.BLACK);
        lozenge.setStrokeWidth(3);

        final Square square = new Square(600 , 500, 30);
        square.setFill(Color.TRANSPARENT);
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(3);

        final StationTriangle st= new StationTriangle(400,100 , 30);
        st.setEvents(Color.ORANGE);
        st.setColor(Color.BLACK);

        final StationTriangle st2= new StationTriangle(xCenterTri,yCenterTri , 30);
        st2.setEvents(Color.BLUE);
        st2.setColor(Color.BLACK);

        Color colorLine = Color.ORANGE;

        final Cross cross = new Cross(800, 600 , 30);
        cross.setFill(Color.TRANSPARENT);
        cross.setStroke(Color.BLACK);
        cross.setStrokeWidth(3);

        final Circle circle = new Circle(xCenterCir,yCenterCir,diameter/2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(3);



        triangle.setStroke(Color.BLACK);
        triangle.setFill(Color.TRANSPARENT);
        triangle.setStrokeWidth(4);
        final View.Train train1 = new View.Train(200,200, 30);
        train1.setColor(Color.ORANGE);


        MetroLine l1 = new MetroLine(station1.getCenterX(),station1.getCenterY(),station2.getCenterX(),station2.getCenterY(),Color.BLUE);
        MetroLine l2 = new MetroLine(station2.getCenterX(),station2.getCenterY(),station3.getCenterX(),station3.getCenterY(),Color.BLUE);

        */


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
    }

    public static void addElement(Node n){
        root.getChildren().add(n);
    }
}

