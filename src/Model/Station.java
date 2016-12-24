package Model;

/**
 * Created by hugo on 12/24/2016.
 */
public class Station extends PassengerContainer {

    private Shape sh;

    private static int capacity = 20;

    public Station(){
        super();
        sh = Shape.Circle;
    }

    public Station(Coordinates co, int id, Shape sh){
        super(co, id);
        this.sh = sh;
    }
}
