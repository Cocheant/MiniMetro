package Model;

import sun.reflect.annotation.ExceptionProxy;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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

    private Random rand = new Random(50);

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

    /**
     * Creates a new station
     * @param co the coordinates of the station
     * @param sh the shape of the station
     */
    private void addStation(Coordinates co, Shape sh){
        this.countStations ++;
        int id = countStations;
        this.stations.add(new Station(co,id,sh));
    }

    /**
     * Adds a passenger to a random station
     * @param p the passenger to add
     */
    private void randomStationAdd(Passenger p){
        int randomNum = rand.nextInt(countStations);
        try{
            this.stations.get(randomNum).addPassenger(p);
        }catch (Exception e){
            // TODO : START FILLED STATION TIMER
        }

    }

    /**
     * Creates a new passenger
     * @param sh the shape of the passenger
     */
    private void addPassenger(Shape sh){
        int id = countPassengers;
        Passenger p = new Passenger(sh, id);
        this.passengers.add(p);
        randomStationAdd(p);
        this.countPassengers ++;
    }

    /**
     * Removes a passenger from the list. This is used when the passenger arrives at it's destination
     * @param p the passenger to remove
     * @throws Exception if the passenger is not found on the list for some reason
     */
    private void removePassenger(Passenger p) throws Exception {
        if(this.passengers.remove(p)){
            this.countPassengers --;
        }else{
            throw new Exception("Passenger not found");
        }

    }

    /**
     * Adds a line and it's corresponding train to the available line & trains
     */
    private void addLine(){
        this.availableLines ++;
        this.availableTrains ++;
    }

    /**
     * Creates a line between all the stops
     * @param col the color of the line
     * @param stops the different stations included in the line
     * @throws Exception throws exception if player is out of free lines
     */
    private void createLine(Color col, ArrayList<Station> stops) throws Exception{
        if(countLines < availableLines){
            countLines ++;
            int id = countLines;
            this.lines.add(new Line(id, col, stops));
        }else{
            throw new Exception("No available lines");
        }
    }

    /**
     * TODO modify an existing line
     * @param id
     * @param stops
     */
    private void modifyLine(int id, ArrayList<Station> stops){

    }

    private void addTrainToLine(Line l, Coordinates co){
        if(countTrains < availableTrains){
            countTrains ++;
            int id = countTrains;
            Coordinates newCo = nearestStationOnLine(l, co);
            l.addTrain(new Train(co, id, true));
        }
    }


    private Coordinates nearestStationOnLine(Line l, Coordinates co){
        Coordinates res = null;
        Double distance = 1000.0;

        for(Station s : Line.getStops()){
            if(s.getCo().distance(co) < distance){
                distance = s.getCo().distance(co);
                res = s.getCo();
            }
        }
        return res;
    }
}
