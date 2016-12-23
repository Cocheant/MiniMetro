package View;

import javafx.scene.shape.Polygon;

import static java.lang.StrictMath.sqrt;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class EquilateralTriangle extends Polygon {

    private double xCenter,  yCenter,  size;

    public EquilateralTriangle(double xCenter, double yCenter, double size){

        super(xCenter, yCenter- (sqrt(3/2)*size/2),
                xCenter- (size/2), yCenter+ (sqrt(3/2)*size/2),
                xCenter+ (size/2), yCenter+ (sqrt(3/2)*size/2));
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
