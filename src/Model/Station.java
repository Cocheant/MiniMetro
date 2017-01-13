package Model;

/**
 * Created by hugo on 12/24/2016.
 */
public class Station extends PassengerContainer {


    private Shape sh;

    private static int capacity = 20;

    private static int compteur = 0 ;

    
    /**
     * Default constructor
     */
    public Station(){
        super();
        sh = Shape.Circle;
    }

    /**
     * Initialising constructor
     * @param co the coordinates of the station
     * @param id the id of the station
     * @param sh the shape of the station
     */

    public Station(Coordinates co, int id, Shape sh){
        super(co, id);
        this.sh = sh;
        compteur ++;
    }
    public Station(Coordinates co, Shape sh){
        super(co, compteur +1);
        compteur ++;
        this.sh = sh;
    }

    public void addPassenger(Passenger p) throws Exception {

        passengers.add(p);

        if(capacity<passengers.size()){
            throw new Exception("Station Full");
        }
    }

    public Shape getShape(){
        return sh;
    }

}
