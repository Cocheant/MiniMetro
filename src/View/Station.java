package View;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javafx.scene.shape.Shape;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Station<T extends Shape>{

    T t;

    double xCenter,yCenter;


    EventHandler<DragEvent> dragHandler = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

        }
    };


    public Station(T t){
        this.t = t;
        xCenter = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
        yCenter = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;

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

}
