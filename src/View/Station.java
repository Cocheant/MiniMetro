package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javafx.scene.shape.Shape;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Station {

    // TODO create methods that stations have to implement, static args like defaut station size

    Model.Shape shape;
    Object view;

    public Station(EquilateralTriangle t){

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


}
