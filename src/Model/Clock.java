package Model;

import Controller.GameController;
import View.Day;
import javafx.scene.text.Font;

/**
 * Created by hugo on 12/20/2016.
 */
public class Clock extends Thread {

    private int hours = 0;

    private boolean running = true;

    public int getHours() {
        return hours;
    }

    private Day day = Day.Monday;

    public Day getDay() {
        return day;
    }

    private int millisSpeed = 1000;

    private GameController controller;

    public void run() {
        while(running){
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
            controller.updateClock();
        }
    }

    public Clock(GameController controller){

        this.controller = controller;
    }

    public Clock(int hours){
        this.hours = hours;

        this.setDaemon(true);
    }

    public void setValue(int time){
        hours = time;
    }


    public boolean status(){
        return true;
    }

    public void setPosition(double x, double y){

    }

    public void setSpeed(int millis){
        if(millis > 0 )  this.millisSpeed = millis;
    }

    public void exitLoop(){
        running = false;
    }

}
