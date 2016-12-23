package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Wagon extends Polygon {

    private double xCenter,yCenter,length,width;
    private Color color;


    public Wagon(double xCenter, double yCenter, double length){

        super(xCenter-(length/2),yCenter-(length/3),
                xCenter+(length/2),yCenter-(length/3),
                xCenter+(length/2),yCenter+(length/3),
                xCenter-(length/2),yCenter+(length/3)
        );
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.length = length;
        this.width = length/2;

    }

    public void setColor(Color color){

        this.color = color;
        super.setFill(color);
        super.setStroke(color);
    }


}

