package Model;

/**
 * Created by hugo on 12/24/2016.
 */
public class Station extends PassengerContainer {

    private Shape sh;

    private static int capacity = 20;

    /**
     * Default constructor
     */
    public Station(){
        super();
        sh = Shape.Circle;
    }

    /**
     * Initialising constructo
     * @param co the coordinates of the station
     * @param id the id of the station
     * @param sh the shape of the station
     */
    public Station(Coordinates co, int id, Shape sh){
        super(co, id);
        this.sh = sh;
    }
}
