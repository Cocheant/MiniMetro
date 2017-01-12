
package Controller;


import Model.Coordinates;
import Model.Game;
import Model.Passenger;
import Model.Shape;
import View.Station;
import View.View;
import View.Cross;
import View.Diamond;
import View.EquilateralTriangle;
import View.Lozenge;
import View.Square;
import View.Clock;
import View.ViewPassenger;


import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public class GameController implements Runnable {


    final double stationSize = 15 ;

    private Game game;

    private View view;

    public GameController(){

    }

    public void run() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void addViewStation(Model.Station station){

        view.addStation(toViewStation(station));

    }

    public void addStationToLine(Coordinates co, Shape sh , Color selected){

        game.addStationToLine(new Model.Station(co, sh), selected);
    }

    public void addLineView(Model.Station first, Model.Station second , Color color){

        Station firstView = toViewStation(first);
        Station secondView = toViewStation(second);

        view.printLine(firstView,secondView,color);
    }

    public void updateClock(){
        Model.Clock mCl = this.game.getCl();

        Clock vCl = this.view.getClock();

        vCl.update(mCl.getHours(), mCl.getDay());
    }

    private Station toViewStation (Model.Station station){

        Coordinates c = station.getCo();
        switch(station.getShape()){

            case Circle:
                Station<Circle> circleStation = new Station(new Circle(c.getX(),c.getY() , stationSize/2));
                return circleStation;

            case Square:
                Station<Square> squareStation = new Station(new Square(c.getX(),c.getY() , stationSize));
                return(squareStation);

            case Triangle:
                Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(c.getX(),c.getY() , stationSize));
                return(triangleStation);

            case Diamond:
                Station<Diamond> diamondStation = new Station(new Diamond(c.getX(),c.getY() , stationSize));
                return(diamondStation);

            case Cross:
                Station<Cross> crossStation = new Station(new Cross(c.getX(),c.getY() , stationSize));
                return(crossStation);

            case Lozenge:
                Station<Lozenge> lozengeStation = new Station(new Lozenge(c.getX(),c.getY() , stationSize));
                return(lozengeStation);

        }
        return null;
    }

    /*public deleteLine(Object object){
        game.removeStationFromLine
    }*/

    public void addViewPassengerToStation(Model.Passenger p, Model.Station s){

        ViewPassenger pa = null;
        switch(p.getSh()){
            case Triangle:
                pa = new ViewPassenger(new EquilateralTriangle(0,0,2), p.getId());
                break;
            case Circle:
                pa = new ViewPassenger(new Circle(0,0,2), p.getId());
                break;
            case Cross:
                pa = new ViewPassenger(new Cross(0,0,0), p.getId());
                break;
            case Diamond:
                pa = new ViewPassenger(new Diamond(0,0,0), p.getId());
                break;
            case Lozenge:
                pa = new ViewPassenger(new Lozenge(0,0,0), p.getId());
                break;
            case Square:
                pa = new ViewPassenger(new Square(0,0,0), p.getId());
                break;
        }

        Coordinates co = s.getCo();

        for(Station st : view.getStations()){
            Coordinates co2 = new Coordinates(st.getCenterX(), st.getCenterY());
            if(co.equals(co2)){
                st.addPassenger(pa);
            }
        }

    }
}

