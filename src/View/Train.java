package View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by Toinecoch on 20/12/16.
 */
public class Train extends Polygon {

    private double xCenter,yCenter,length;
    private Color color;
    private Group wagons;


    public Train(double xCenter, double yCenter, double length){

        super(xCenter-(length/2),yCenter-(length/3),
                xCenter+(length/2),yCenter-(length/3),
                //xCenter+length,yCenter,
                xCenter+(length/2),yCenter+(length/3),
                xCenter-(length/2),yCenter+(length/3)
                );

        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.length = length;
        wagons = new Group(this);

    }

    public void setColor(Color color){

        this.color = color;
        super.setFill(color);
        super.setStroke(color);
    }

    public void addWagon(Wagon wagon){

        wagon.setColor(this.color);
        wagons.getChildren().add(wagon);

    }

    public Group getWagons(){
        return wagons;
    }

    public double getCenterX(){
        return xCenter;
    }

    public double getCenterY(){
        return yCenter;
    }

    public Color getColor(){ return color; }

    public double getLength(){ return length;}

    public double getWidth(){ return length/3;}

}
