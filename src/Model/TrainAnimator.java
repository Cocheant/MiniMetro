package Model;

import View.View;
import com.sun.javafx.collections.ArrayListenerHelper;
import sun.plugin2.message.CookieOpMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Toinecoch on 13/1/17.
 */
public class TrainAnimator extends Thread {

    ArrayList<Line> lines;


    public TrainAnimator(ArrayList<Line> lines){
        this.lines = lines;
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

                    /**
                     * IF THE TRAIN ARRIVES AT A STATION, CHECKS FOR THE NEXT STATION & UNLOADS/LOADS PASSENGERS
                     */
                    Coordinates co = new Coordinates(nextX, nextY);
                    if(co.equals(t.getNextStation())){
                        Iterator<Station> it = stops.iterator();

                        while(!(it.equals(t.getNextStation()))){
                            it.next();
                        }

                        if(it.hasNext()){
                            Station temp = it.next();
                            t.setCurrentStation(t.getNextStation());
                            t.setNextStation(temp);
                        }else{
                            Station temp = t.getCurrentStation();
                            t.setCurrentStation(t.getNextStation());
                            t.setNextStation(temp);
                            t.changeDirection();

                        }

                        t.unload();
                        t.load();


                    }

                }
            }

        }

    }



}
