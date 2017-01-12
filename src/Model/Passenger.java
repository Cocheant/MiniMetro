package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public class Passenger {

    private Shape sh;
    public Shape getSh(){
        return sh;
    }

    private int id;
    public int getId(){return this.id;}

    private static int counter = 1;
    /**
     * Default constructor
     */
    public Passenger(){
        this.sh = Shape.Circle;
        this.id = this.counter;
        this.counter ++;
    }

    /**
     * Initialising constructor
     * @param sh the shape of the passenger
     */
    public Passenger(Shape sh){
        this.sh = sh;
        this.id = this.counter;
        this.counter ++;
    }
}
