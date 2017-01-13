package Model;

import java.util.ArrayList;

/**
 * Created by hugo on 12/23/2016.
 */
public class Train extends TransportObject {

    private boolean direction;

    Station nextStation;

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

    public Train(Station next){
        super();
        direction = true;
        this.nextStation = next;
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

    public Train(Coordinates co, int id, boolean direction, Station next){
        super(co, id);
        this.direction = direction;
        this.nextStation = next;
    }


    public void addWagon(Wagon wa){
        this.wagons.add(wa);
    }

    /**
     * Moves the train & all wagons attached to the train
     * @param x the amount of movement in the X direction
     * @param y the amount of movement in the Y direction
     */
    @Override
    public void move(int x, int y) {
        super.move(x, y);
        for(Wagon w : wagons){
            w.move(x,y);
        }
    }

    public boolean getDiection(){
        return direction;
    }

    public Station getNextStation(){
        return nextStation;
    }

}
