package Model;

import java.util.ArrayList;

/**
 * Created by hugo on 12/23/2016.
 */
public class Train extends TransportObject {

    private boolean direction;

    /**
     * all the wagons attached to the end of the train
     */
    private ArrayList<Wagon> wagons;

    /**
     * Default Constructor
     */
    public Train(){
        super();
        direction = true;
    }

    /**
     * Initialising constructor
     * @param co the coordinates of the train
     * @param id the id of the train
     */
    public Train(Coordinates co, int id, boolean direction){
        super(co, id);
        this.direction = direction;
    }

    public void addWagon(Wagon wa){
        this.wagons.add(wa);
    }

}
