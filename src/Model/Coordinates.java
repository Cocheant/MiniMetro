package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public class Coordinates {

    private int x;

    private int y;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y){
        setX(this.x + x);
        setY(this.y + y);
    }
}
