package View;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;

/**
 * Created by hugo on 12/20/2016.
 */
public class Clock extends Label{

    private int hours = 0;
    boolean running=true;
    private Thread clockThread;


    Task <Void> task = new Task<Void>() {
        @Override public Void call() throws InterruptedException {
            // "message2" time consuming method (this message will be seen).
            while(running) {
                updateMessage(hours+":00");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ie) {
                    System.out.print(ie);
                }
                if (hours < 23) {
                    hours++;
                } else if (hours >= 23) {
                    hours = 0;
                }

                // this will never be actually be seen because we also set a message
                // in the task::setOnSucceeded handler.

            }

            return null;
        }
    };

    Runnable update = new Runnable() {
        @Override
        public void run() {


        }
    };


    public Clock(){
        super(""+0);

        this.setFont(new Font(30));


        this.textProperty().bind(task.messageProperty());

        clockThread = new Thread(task);
        clockThread.setDaemon(true);    }

    public Clock(int hours){
        super(""+hours);
        this.hours = hours;

        this.textProperty().bind(task.messageProperty());


        clockThread = new Thread(task);
        clockThread.setDaemon(true);
    }

    public void setValue(int time){
        hours = time;
    }


    public void start(){
        clockThread.start();
    }
    public void stop(){
        running = false;
        clockThread.interrupt();
        this.textProperty().unbind();
    }

    public boolean status(){
        return clockThread.isAlive()&&this.textProperty().isBound();

    }

    public void setPosition(double x, double y){
        this.setTranslateX(x);
        this.setTranslateY(y);
    }


}
