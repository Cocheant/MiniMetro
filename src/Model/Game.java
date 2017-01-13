package Model;

import Controller.GameController;
import View.EquilateralTriangle;
import View.Square;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import sun.reflect.annotation.ExceptionProxy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by hugo on 12/26/2016.
 */

public class Game implements Runnable {

    private Clock cl;

    public Clock getCl() {
        return cl;
    }

    private Map map;

    private int score;

    final int widthWindow = 1024;
    final int heightWindow = 800;

    final int stationSize = 30;

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

    private Random randCoordinateX = new Random(10);
    private Random randCoordinateY = new Random(20);

    private Random rand = new Random(50);

    private GameController controller;

    private StationGenerator stationGenerator;
    private PassengerGenerator passengerGenerator;
    private TrainAnimator trainAnimator;

    Line blueLine ;
    Line redLine;
    Line greenLine;

    /**
     * Initializing constructor
     */
    public Game(GameController controller){

        this.controller = controller;
        this.cl = new Clock(controller);

        countPassengers = 0;
        availableLines = 3;
        countLines = 0;
        availableTrains = 3;
        countTrains = 0;
        availableWagons = 0;
        countWagons = 0;
        score = 0;
        //map = new Map();

        stations = new ArrayList<Station>();
        lines = new ArrayList<Line>();

        blueLine = new Line();
        redLine = new Line();
        greenLine = new Line();

        lines.add(blueLine);
        lines.add(redLine);
        lines.add(greenLine);

        //addStation(new Station(randomStationCoordinates(),Shape.Circle));

        stationGenerator = new StationGenerator(this,controller);
        passengerGenerator = new PassengerGenerator(this, controller);
        trainAnimator = new TrainAnimator(this.lines);

    }

    public void run() {

        cl.start();
        stationGenerator.start();
        passengerGenerator.start();
        trainAnimator.start();

        Station triangleStation = new Station(new Coordinates(400,400),Shape.Triangle);
        addStation(triangleStation);
        controller.addViewStation(triangleStation);

        Station circleStation = new Station(new Coordinates(400, 600), Shape.Circle);
        addStation(circleStation);
        controller.addViewStation(circleStation);

        Station squareStation = new Station(new Coordinates(200, 200), Shape.Circle);
        addStation(squareStation);
        controller.addViewStation(squareStation);

    }

    public void start(){

    }

    public void onClose(){

        stationGenerator.interrupt();

        stationGenerator.exitLoop();

        passengerGenerator.interrupt();

        passengerGenerator.exitLoop();

    }

    public Station getStationByCoord(Coordinates co){

        for(Station s : stations){
            if(s.getCo() == co){
                return s;
            }

        }
        return null;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    /**
     * Creates a new station
     * @param station the station to be added.
     */
    private void addStation(Station station){
        this.stations.add(station);
    }

    /**
     * Adds a passenger to a random station
     * @param p the passenger to add
     */
     public void randomStationAdd(Passenger p) {

         int randomNum = rand.nextInt(stations.size());
         Station s = this.stations.get(randomNum);

         try{
            s.addPassenger(p);
         }catch (Exception e){
            // TODO : START FILLED STATION TIMER
         } //finally {

         controller.addViewPassengerToStation(p, s);


    }

    private Coordinates randomStationCoordinates(){

        Coordinates RandCoord = new Coordinates(0,0);

        while(isMisplaced(RandCoord)) {
            RandCoord.setX( stationSize + (int)(Math.random() * (widthWindow - stationSize)) );
            RandCoord.setY( stationSize + (int)(Math.random() * (heightWindow - stationSize)) );
        }
        return RandCoord;
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

    /**
     * Sees whether or not a station is too close to another station
     * @param co the coordinates to be tested
     * @return true if misplaced
     */
    private boolean isMisplaced(Coordinates co){
        boolean misplaced = false;
        double distance = 0.;

        for(Station s:stations){
            distance = s.getCo().distance(co);
            if((distance < stationSize * 3)||((co.getX() < stationSize)&&(co.getY() < stationSize))){
                misplaced = true;
            }
        }
        return misplaced;
    }
    /**
     * Adds a train to a specific line
     * @param l the line on which the train will run
     * @param co the coordinates where the user leaves the train (used to find the nearest station on the line)
     * @throws Exception
     */
    private void addTrainToLine(Line l, Coordinates co) throws Exception{
        if(countTrains < availableTrains){
            countTrains ++;
            int id = countTrains;
            Coordinates newCo = nearestCoordinatesOnLine(l, co);
            l.addTrain(new Train(co, id, true));
        }else{
            throw new Exception("No available train");
        }
    }

    /**
     * Adds a wagon at the end of the nearest train on the line
     * @param l the line on which the wagon will be used
     * @param w the wagon to add
     * @param co the coordinates where the user leaves the wagon (used to find the nearest train)
     */
    private void addWagonToLine(Line l, Wagon w, Coordinates co) throws Exception {
        if(countWagons < availableWagons){
            countWagons ++;
            int id = countWagons;
            nearestTrainOnLine(l, co).addWagon(w);
        }else{
            throw new Exception("No available wagon");
        }
    }

    /**
     * Finds the nearest station on the line from the coordinates
     * @param l the line
     * @param co the start coordinates
     * @return the coordinates of the nearest station
     */
    public Coordinates nearestCoordinatesOnLine(Line l, Coordinates co){
        Coordinates res = null;
        Double distance = 1000.0;
        ArrayList<Station> stations = l.getStops();

        for(Station s : stations){
            if(s.getCo().distance(co) < distance){
                distance = s.getCo().distance(co);
                res = s.getCo();
            }
        }
        return res;
    }

    public Station nearestStationOnLine(Line l, Coordinates co){
        //Coordinates res = null;
        Station res = null;
        Double distance = 1000.0;
        ArrayList<Station> stations = l.getStops();

        for(Station s : stations){
            if(s.getCo().distance(co) < distance){
                distance = s.getCo().distance(co);
                res = s;
                //res = s.getCo();
            }
        }
        return res;
    }

    /**
     * finds the nearest train on a line from the given coordinates
     * @param l the line on which the trains are
     * @param co the coordinates given
     * @return the closest train
     */
    private Train nearestTrainOnLine(Line l, Coordinates co){
        Train res = null;
        double distance = 1000.0;
        ArrayList<Train> trains = l.getTrains();

        for(Train t : trains){
            if(t.getCo().distance(co) < distance){
                distance = t.getCo().distance(co);
                res = t;
            }
        }

        return res;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void addStationToLine(Station station, Color color){

        if(color == Color.TOMATO){

            redLine.addStation(station);
            int Id = redLine.getId(station);
            if((redLine.getStopById(Id-1))!=null){
                controller.addLineView((redLine.getStopById(Id-1)),station,color);
            }
        }

        else if(color == Color.DEEPSKYBLUE){

            blueLine.addStation(station);
            int Id = blueLine.getId(station);
            if((blueLine.getStopById(Id-1))!=null){
                controller.addLineView((blueLine.getStopById(Id-1)),station,color);
            }

        }

        else if(color == Color.LIMEGREEN){

            greenLine.addStation(station);
            int Id = greenLine.getId(station);
            if((greenLine.getStopById(Id-1))!=null){
                controller.addLineView((greenLine.getStopById(Id-1)),station,color);
            }
        }

    }


}
