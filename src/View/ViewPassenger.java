package View;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;

/**
 * Created by hugo on 1/12/2017.
 */
public class ViewPassenger<T extends Shape> {

    double xCenter,yCenter;

    T t;

    public T getType(){ return this.t;}
    public void setType(T t){ this.t = t;}

    public double getCenterX(){
        return xCenter;
    }
    public double getCenterY(){
        return yCenter;
    }

    private int id;
    public int getId(){ return this.id;}

    public ViewPassenger(T t, int id){
        this.t = t;
        this.id = id;
        this.t.setFill(Color.TRANSPARENT);
        this.t.setStroke(Color.BLACK);
        this.t.setStrokeWidth(1);

        xCenter = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
        yCenter = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;
    }

    public boolean equals(ViewPassenger vP){
        return (this.id == vP.id);
    }

}
