package View;

import javafx.scene.shape.Polygon;

/**
 * Created by Toinecoch on 23/12/16.
 */

public class Diamond extends Polygon {

    double xCenter, yCenter, size ;

    public Diamond(double xCenter, double yCenter, double size){

        super(xCenter,yCenter-size/3,
                xCenter+size/2,yCenter,
                xCenter, yCenter+2*size/3,
                xCenter - size/2, yCenter );

        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.size = size;

    }

    public double getCenterX(){
        return xCenter;
    }
    public double getCenterY(){
        return yCenter;
    }
    public double getSize(){
        return size;
    }

}

