package Model;

import java.util.ArrayList;
/**
 * Created by hugo on 12/23/2016.
 */
public abstract class PassengerContainer {

    protected Coordinates co;

    private int id;

    protected ArrayList<Passenger> passengers;

    public ArrayList<Passenger> getPassengers(){
        return passengers;
    }

    /**
     * Default constructor
     */
    public PassengerContainer(){
        this.co = new Coordinates();
        this.id = 0;
        this.passengers = new ArrayList<Passenger>();
    }

    /**
     * Initialising constructor
     * @param co the x,y coordinates of the container
     * @param id the id of the passenger container
     */
    public PassengerContainer(Coordinates co, int id){
        this.co = co;
        this.id = id;
        this.passengers = new ArrayList<Passenger>();
    }

    public abstract void addPassenger(Passenger p) throws Exception;
    public void removePassenger(Passenger p){
        this.passengers.remove(p);
    }

    public Coordinates getCo(){
        return co;
    }
}
