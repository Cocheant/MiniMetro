
package View;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;


/**
 * Created by Toinecoch on 20/12/16.
 */

public class Station<T extends Shape> {

    T t;

    private Group group;
    public Group getGroup(){ return this.group;}

    static double lastCoordX,lastCoordY;

    double xCenter,yCenter;

    Model.Shape shape;

    private ArrayList<ViewPassenger> passengers;



    EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            View.addStationToLine(xCenter,yCenter, shape);
        }
    };

    public Station(T t){
        this.group = new Group();

        this.t = t;
        xCenter = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
        yCenter = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;
        this.t.setFill(Color.TRANSPARENT);
        this.t.setStroke(Color.BLACK);
        this.t.setStrokeWidth(2);

        this.t.setOnMouseClicked(clickHandler);

        if(t.getClass().equals(Circle.class)){
            shape = Model.Shape.Circle;
        }
        else if(t.getClass().equals(Square.class)){
            shape = Model.Shape.Square;
        }
        else if(t.getClass().equals(EquilateralTriangle.class)){
            shape = Model.Shape.Triangle;
        }

        if(t.getClass().equals(Diamond.class)){
            shape = Model.Shape.Diamond;
        }
        if(t.getClass().equals(Cross.class)){
            shape = Model.Shape.Cross;
        }
        if(t.getClass().equals(Lozenge.class)){
            shape = Model.Shape.Lozenge;
        }

        this.passengers = new ArrayList<ViewPassenger>();
        group.setTranslateY(this.yCenter + 20);
        group.setTranslateX(this.xCenter);
    }



    public void setType(T t) { this.t = t; }
    public T getType() { return t; }
    public double getCenterX(){
        return xCenter;
    }
    public double getCenterY(){
        return yCenter;
    }
    public String toString(){
        return t.toString();
    }
    public Model.Shape getShape() {
        return shape;
    }

    public void addPassenger(final ViewPassenger p){

        //p.getType().setTranslateX(5);
        this.passengers.add(p);



        p.getType().setTranslateX(this.xCenter + passengers.size() * 10);
        p.getType().setTranslateY(this.yCenter + 20);
        System.out.println(passengers.size());

        synchronized (View.class) {
            Platform.runLater(new Runnable() {
                public void run() {
                    View.addElement(p.getType());
                }
            });
        }

    }

    public void removePassenger(ViewPassenger p){

        this.passengers.remove(p);
        this.group.getChildren().remove(p.getType());

        for(ViewPassenger temp : this.passengers){
            this.group.getChildren().add(temp.getType());
        }
    }
}

