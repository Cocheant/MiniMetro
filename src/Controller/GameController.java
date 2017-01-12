
package Controller;

import Model.Coordinates;
import Model.Game;
import View.*;
import javafx.scene.shape.Circle;


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

        Coordinates c = station.getCo();

        switch(station.getShape()){

            case Circle:
                Station<Circle> circleStation = new Station(new Circle(c.getX(),c.getY() , stationSize/2));
                view.addStation(circleStation);
                break;

            case Square:
                Station<Square> squareStation = new Station(new Square(c.getX(),c.getY() , stationSize));
                view.addStation(squareStation);
                break;

            case Triangle:
                Station<EquilateralTriangle> triangleStation = new Station(new EquilateralTriangle(c.getX(),c.getY() , stationSize));
                view.addStation(triangleStation);
               break;

            case Diamond:
                Station<Diamond> diamondStation = new Station(new Diamond(c.getX(),c.getY() , stationSize));
                view.addStation(diamondStation);
                break;

            case Cross:
                Station<Cross> crossStation = new Station(new Cross(c.getX(),c.getY() , stationSize));
                view.addStation(crossStation);
                break;

            case Lozenge:
                Station<Lozenge> lozengeStation = new Station(new Lozenge(c.getX(),c.getY() , stationSize));
                view.addStation(lozengeStation);
                break;
        }

    }

    public void updateClock(){
        Model.Clock mCl = this.game.getCl();

        Clock vCl = this.view.getClock();

        vCl.update(mCl.getHours(), mCl.getDay());
    }

    public void addViewPassengerToStation(Model.Passenger p, Model.Station s){

        Passenger pa = null;
        switch(p.getSh()){
            case Triangle:
                pa = new Passenger(new EquilateralTriangle(0,0,2), p.getId());
                break;
            case Circle:
                pa = new Passenger(new Circle(0,0,2), p.getId());
                break;
            case Cross:
                pa = new Passenger(new Cross(0,0,0), p.getId());
                break;
            case Diamond:
                pa = new Passenger(new Diamond(0,0,0), p.getId());
                break;
            case Lozenge:
                pa = new Passenger(new Lozenge(0,0,0), p.getId());
                break;
            case Square:
                pa = new Passenger(new Square(0,0,0), p.getId());
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

