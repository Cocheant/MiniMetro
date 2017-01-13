package Model;

import java.util.ArrayList;

/**
 * Created by hugo on 12/23/2016.
 */
public class Train extends TransportObject {

    private boolean direction;

    private Station nextStation;
    private Station currentStation;
    private boolean needNewVector;
    private double vector;
    private double speedX;

    private static final double speed = 50;


    /**
     * all the wagons attached to the end of the train
     */
    private ArrayList<Wagon> wagons;
    public ArrayList<Wagon> getWagons(){ return this.wagons;}

    /**
     * Default Constructor
     */
    public Train(){
        super();
        this.direction = true;
        this.nextStation = null;
        this.currentStation = null;
        this.needNewVector = true;
        this.vector = 0;
        this.speedX = 0;
    }

    public Train(Station next, Station curr){
        super();
        direction = true;
        this.nextStation = next;
        this.currentStation = curr;
        this.calculateVector();
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
    public void move(double x, double y) {
        super.move(x, y);
        for(Wagon w : wagons){
            w.move(x,y);
        }
    }

    /**
     * Moves the train & all it's wagons TO a coordinate
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    @Override
    public void moveTo(double x, double y){
        super.moveTo(x, y);
        for(Wagon w : wagons){
            w.moveTo(x, y);
        }


    }

    /**
     * Calculates the directional vector the train will follow in between currentStation & nextStation
     */
    public void calculateVector(){
        double res = 0;

        double nextX,nextY;
        double a;

        Coordinates nextStCo = this.getNextStation().getCo();
        Coordinates trainCo= this.getCo();
        res = (nextStCo.getY() - trainCo.getY() )/(nextStCo.getX() - trainCo.getX());

        double diff = this.currentStation.getCo().getX() - this.nextStation.getCo().getX();

        double distance = this.currentStation.getCo().distance(this.nextStation.getCo());
        this.speedX = distance/diff;

        this.needNewVector = false;
        this.vector = res;
    }

    public void changeDirection(){
        this.direction = !this.direction;
    }

    public boolean getDirection(){
        return direction;
    }

    public Station getNextStation(){
        return nextStation;
    }

    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
        this.needNewVector = true;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
        this.needNewVector = true;
    }

    public boolean getNeedNewVector(){
        return this.needNewVector;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getVector(){
        return this.vector;
    }

}
