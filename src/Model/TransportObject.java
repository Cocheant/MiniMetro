package Model;

/**
 * Created by hugo on 12/23/2016.
 */
public abstract class TransportObject extends PassengerContainer {

    private static final int capacity = 6;

    /**
     * Default constructor
     */
    public TransportObject(){
        super();
    }

    /**
     * Initialising constructor
     * @param co the coordinates of the tranport object
     * @param id the id of the object
     */
    public TransportObject(Coordinates co, int id){
        super(co, id);
    }

    @Override
    public void addPassenger(Passenger p) throws Exception {
        if(this.passengers.size()<capacity){
            passengers.add(p);
        }else{
            throw new Exception("Transport Object Full");
        }
    }
}
