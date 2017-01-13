package Model;

import Controller.GameController;
import View.View;
import com.sun.javafx.collections.ArrayListenerHelper;
import sun.plugin2.message.CookieOpMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Toinecoch on 13/1/17.
 *
 * Not used in the final version
 *
 */
public class TrainAnimator extends Thread {

    private ArrayList<Line> lines;
    private GameController controller;


    public TrainAnimator(ArrayList<Line> lines, GameController controller){
        this.lines = lines;
        this.controller = controller;

    }

    public void run(){


        try {
            Thread.sleep(50);
        }catch(InterruptedException ie){}



        for(Line l : lines) {
            ArrayList<Station> stops = l.getStops();
            if (!l.getTrains().isEmpty()){
                for (Train t : l.getTrains()) {
                    double nextX, nextY;

                    if(t.getNeedNewVector()){
                        t.calculateVector();
                    }

                    nextX = (t.getCo().getX() + t.getSpeedX());
                    nextY = (t.getCo().getY() + t.getSpeedX()*t.getVector());

                    t.moveTo(nextX, nextY);


                    Coordinates co = new Coordinates(nextX, nextY);
                    if(co.equals(t.getNextStation())){
                        Iterator<Station> it = stops.iterator();
                        Station temp = null;

                        while(!(it.equals(t.getNextStation())) && it.hasNext()){
                            temp = it.next();
                        }

                        if(temp.equals(t.getNextStation())){
                            if(it.hasNext()){
                                temp = it.next();
                                t.setCurrentStation(t.getNextStation());
                                t.setNextStation(temp);
                            }else{
                                temp = t.getCurrentStation();
                                t.setCurrentStation(t.getNextStation());
                                t.setNextStation(temp);
                                t.changeDirection();

                            }
                        }else{
                            //ERREUR
                        }


                        unload(t);
                        load(t);


                    }

                }
            }

        }

    }


    public void load(Train t){


    }

    public void unload(Train t){
        Station s = t.getCurrentStation();
        for(Passenger p : t.getPassengers()){
            if(unloadPassenger(s, p)){
                t.removePassenger(p);
                this.controller.removePassengerFromTrain(p);
            }
        }
        for(Wagon w : t.getWagons()){
            for(Passenger p : w.getPassengers()){
                if(unloadPassenger(s, p)){
                    w.removePassenger(p);
                    this.controller.removePassengerFromTrain(p);
                }
            }
        }

    }

    public boolean unloadPassenger(Station s, Passenger p){
        boolean res = false;
        if(s.getShape().equals(p.getSh())){
            res= true;
        }
        return res;
    }

}
