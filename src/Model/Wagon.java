package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public class Wagon extends TransportObject {

    /**
     * Default constructor
     */
    public Wagon(){
        super();
    }

    /**
     * Initialising constructor
     * @param co the coordinates of the wagon
     * @param id the id of the wagon
     */
    public Wagon(Coordinates co, int id){
        super(co, id);
    }
}
