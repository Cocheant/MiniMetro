package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public class Passenger {

    private Shape sh;

    private int id;

    /**
     * Default constructor
     */
    public Passenger(){
        this.sh = Shape.Circle;
        this.id = 0;
    }

    /**
     * Initialising constructor
     * @param sh the shape of the passenger
     * @param id the id of the passenger
     */
    public Passenger(Shape sh, int id){
        this.sh = sh;
        this.id = id;
    }
}
