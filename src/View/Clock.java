package View;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import javafx.scene.text.Font;



/**
 * Created by Antoine on 12/20/2016.
 *
 * Class to define the view of the clock.
 */

public class Clock extends Label{

    public Clock(){
        this.setFont(new Font (30));
        update(0,Day.Monday);
    }

    public void update(final int hours, final Day day){
        Platform.runLater(new Runnable() {
            public void run() {
                setText(hours+":00"+"\n"  + day );
            }
        });
    }

    public void setPosition(double x, double y){
        this.setTranslateX(x);
        this.setTranslateY(y);
    }


/*
    private int hours = 0;
    private Day day = Day.Monday;
    boolean running=true;
    private Thread clockThread;
    private int millisSpeed = 1000;


    Task <Void> task = new Task<Void>() {

        @Override
        public Void call() throws InterruptedException {

            while(running) {

                updateMessage(hours+":00"+"\n"  + day );
                try {
                    Thread.sleep(millisSpeed);
                } catch (InterruptedException ie) {
                    System.out.print(ie);
                }
                if (hours < 23) {
                    hours++;
                } else if (hours >= 23) {
                    hours = 0;
                    if(day.ordinal()<7) {
                        day = day.next();
                    }
                    else if(day.ordinal()>=7){
                        day = Day.Monday;
                    }
                }

            }

            return null;
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
        running = true;
        this.textProperty().bind(task.messageProperty());
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

    public void setSpeed(int millis){
        if(millis > 0 )  this.millisSpeed = millis;
    }
*/
}
