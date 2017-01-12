package Model;

import View.Day;
import javafx.scene.text.Font;

/**
 * Created by hugo on 12/20/2016.
 */
public class Clock implements Runnable {

    private int hours = 0;

    public int getHours() {
        return hours;
    }

    private Day day = Day.Monday;

    public Day getDay() {
        return day;
    }

    //private boolean running = true;
    private Thread clockThread;
    private int millisSpeed = 1000;


    public void run() {
        try{
            Thread.sleep(millisSpeed);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (hours < 23){
            hours ++;
        }else{
            hours = 0;

            if(day.ordinal() < 7){
                day = day.next();
            }else{
                day = Day.Monday;
            }
        }

    }

    public Clock(){
        //super(""+0);

        //this.setFont(new Font(30));


        //this.textProperty().bind(task.messageProperty());

        //clockThread = new Thread(task);
        clockThread.setDaemon(true);    }

    public Clock(int hours){
        //super(""+hours);
        this.hours = hours;

        //this.textProperty().bind(task.messageProperty());


        //clockThread = new Thread(task);
        clockThread.setDaemon(true);
    }

    public void setValue(int time){
        hours = time;
    }

    public void start(){
        //running = true;
        //this.textProperty().bind(task.messageProperty());
        clockThread.start();
    }

    public void stop(){
        //running = false;
        clockThread.interrupt();
        //this.textProperty().unbind();
    }

    public boolean status(){
        //return clockThread.isAlive()&&this.textProperty().isBound();
        return true;
    }

    public void setPosition(double x, double y){
        //this.setTranslateX(x);
        //this.setTranslateY(y);
    }

    public void setSpeed(int millis){
        if(millis > 0 )  this.millisSpeed = millis;
    }

}
