package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public abstract class TransportObject extends PassengerContainer {

    private static int capacity = 6;

    /**
     * Default constructor
     */
    public TransportObject(){

    }

    /**
     * Initialising constructor
     * @param co the coordinates of the tranport object
     * @param id the id of the object
     */
    public TransportObject(Coordinates co, int id){
        super(co, id);
    }
}
