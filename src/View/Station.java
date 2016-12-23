package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javafx.scene.shape.Shape;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Station extends Canvas{

    // TODO create methods that stations have to implement, static args like defaut station size

    Model.Shape shape;
    Object view;

    public Station(EquilateralTriangle t){
            super(t.getCenterX()+t.getSize(),t.getCenterY()+t.getSize());
            view = t;
            shape = Model.Shape.Triangle;

    }
    public Station(Square s){
        view = s;
        shape = Model.Shape.Square;
    }

    public Station(Circle c){
        view = c;
        shape = Model.Shape.Circle;

    }

    public Object getShape(){
        return view;
    }


}
