package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hugo on 12/24/2016.
 */
public class Line {

    private int id;

    private Color col;

    /**
     * all the stops the line contains
     */
    private ArrayList<Station> stops;

    /**
     * all the trains that run on this line
     */
    private ArrayList<Train> trains;

    /**
     * Default constructor
     */
    public Line(){
        this.id = 0;
        this.col = Color.blue;
        trains = new ArrayList<Train>();
        stops = new ArrayList<Station>();
    }

    /**
     * initialising constructor
     * @param id the id of the line
     * @param col the color of the line
     */
    public Line(int id, Color col, ArrayList<Station> stops){
        this.id = id;
        this.col = col;
        this.stops = stops;
        this.trains = new ArrayList<Train>();
    }

    /**
     * add a train to the list of trains running on the line
     * @param tr the train to add
     */
    public void addTrain(Train tr){
        this.trains.add(tr);
    }

    /**
     * adds a wagon to the first train of the line
     * @param wa the wagon to add
     */
    public void addWagon(Wagon wa){
        if(this.trains.get(0)!= null){
            this.trains.get(0).addWagon(wa);
        }
    }

    public void addStation(Station s){
        stops.add(s);
    }
}
