package Model;

import View.View;
import com.sun.javafx.collections.ArrayListenerHelper;

import java.util.ArrayList;

/**
 * Created by Toinecoch on 13/1/17.
 */
public class TrainAnimator extends Thread {

    ArrayList<Line> lines;
    ArrayList<Train> trains;


    public TrainAnimator(ArrayList<Line> lines){
        this.lines = lines;
        this.trains = trains;

    }

    public void run(){

        try {
            Thread.sleep(100);
        }catch(InterruptedException ie){}



        for(Line l : lines) {

                if (!l.getTrains().isEmpty()){
                for (Train t : l.getTrains()) {

                    double nextX,nextY;
                    double a;

                    Coordinates nextStCo = t.getNextStation().getCo();
                    Coordinates trainCo= t.getCo();
                    a = (nextStCo.getY() - trainCo.getY() )/(nextStCo.getX() - trainCo.getX());

                    if (t.getDiection()) {

                        nextX = trainCo.getX() + 3;
                        nextY = trainCo.getY() + a* 3;


                    } else {

                        nextX = trainCo.getX() - 3;
                        nextY = trainCo.getY() - a* 3;

                    }

                }
            }

        }

    }


}
