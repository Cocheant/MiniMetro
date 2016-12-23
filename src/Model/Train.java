package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public class Train extends TransportObject {

    /**
     * Default Constructor
     */
    public Train(){
        super();
    }

    /**
     * Initialising constructor
     * @param co the coordinates of the train
     * @param id the id of the train
     */
    public Train(Coordinates co, int id){
        super(co, id);
    }
}
