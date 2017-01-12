package View;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

/**
 * Created by hugo on 1/12/2017.
 */
public class Passenger<T extends Shape> {
    T t;
    public T getType(){ return this.t;}
    public void setType(T t){ this.t = t;}

    private int id;

    public Passenger(T t, int id){
        this.t = t;
        this.id = id;

        this.t.setFill(Color.TRANSPARENT);
        this.t.setStroke(Color.BLACK);
        this.t.setStrokeWidth(1);
    }

}
