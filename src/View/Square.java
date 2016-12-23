package View;

import Model.Shape;
import javafx.scene.shape.Polygon;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Square extends Polygon {

    double xCenter, yCenter, size;



    public Square(double xCenter, double yCenter, double size){

        super(xCenter+size/2,yCenter+size/2,
                xCenter+size/2,yCenter-size/2,
                xCenter-size/2, yCenter-size/2,
                xCenter-size/2, yCenter+size/2);

        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.size = size;

    }


}
