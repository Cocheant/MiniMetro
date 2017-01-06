package Model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hugo on 12/26/2016.
 */
public class Game implements Runnable {

    private Clock cl;

    private Map map;

    private int score;

    private int countStations;
    private ArrayList<Station> stations;

    private int countPassengers;
    private ArrayList<Passenger> passengers;

    private int availableLines;
    private int countLines;
    private ArrayList<Line> lines;

    private int availableTrains;
    private int countTrains;
    private ArrayList<Train> trains;

    private int availableWagons;
    private int countWagons;
    private ArrayList<Wagon> wagons;

    /**
     * Default constructor
     */
    public Game(){

        countStations = 0;
        countPassengers = 0;
        availableLines = 3;
        countLines = 0;
        availableTrains = 3;
        countTrains = 0;
        availableWagons = 0;
        countWagons = 0;
        score = 0;
        map = new Map();

        addStation(new Coordinates(10,10), Shape.Circle);
        addStation(new Coordinates(20,20), Shape.Circle);
        addStation(new Coordinates(30,30), Shape.Circle);
    }

    public void run() {

    }

    public void start(){

    }

    private void addStation(Coordinates co, Shape sh){
        int id = countStations;
        this.stations.add(new Station(co,id,sh));
        this.countStations ++;
    }

    private void addPassenger(Shape sh){
        int id = countPassengers;
        this.passengers.add(new Passenger(sh, id));
        this.countPassengers ++;
    }

    private void addLine(Color col, ArrayList<Station> stops) throws Exception{
        if(countLines < availableLines){
            int id = countLines;
            this.lines.add(new Line(id, col, stops));
        }else{
            throw new Exception("No available lines");
        }
    }

    private void modifyLine(int id, ArrayList<Station> stops){

    }


}
