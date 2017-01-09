package Model;

import java.lang.Math;

/**
 * Created by hugo on 12/23/2016.
 */
public class Coordinates<T> {

    private double x;
    private double y;



    /**
     * Default constructor
     */
    public Coordinates() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Initialising constructor
     * @param x
     * @param y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(double x, double y ){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move(double x, double y){
        setX(this.x + x);
        setY(this.y + y);
    }

    public double distance(Coordinates co){
        int res = 0;
        res += Math.pow((this.x - co.getX()),2);
        res += Math.pow((this.y - co.getY()), 2);
        return (Math.sqrt(res));
    }
}
