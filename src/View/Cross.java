package View;

import javafx.scene.shape.Polygon;

/**
 * Created by Toinecoch on 23/12/16.
 */
public class Cross extends Polygon {

    double xCenter, yCenter, size;



    public Cross(double xCenter, double yCenter, double size){

        super(xCenter-size/8,yCenter-size/2,
                xCenter+size/8,yCenter-size/2,
                xCenter+size/8, yCenter-size/8,
                xCenter+size/2, yCenter-size/8,
                xCenter+size/2, yCenter+size/8,
                xCenter+size/8, yCenter+size/8,
                xCenter+size/8, yCenter+size/2,
                xCenter-size/8, yCenter+size/2,
                xCenter-size/8, yCenter+size/8,
                xCenter-size/2, yCenter+size/8,
                xCenter-size/2, yCenter-size/8,
                xCenter-size/8, yCenter-size/8
                );

        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.size = size;

    }
}
