package Model;

import Controller.GameController;

/**
 * Created by hugo on 1/12/2017.
 */
public class PassengerGenerator extends Thread {

    private Game game;
    private GameController controller;
    private boolean running;
    private int passengerRate = 10000;

    public PassengerGenerator(Game game, GameController controller){
        this.game = game;
        this.controller = controller;
        running = true;
    }

    public void run(){
        while(running){
            try{
                Thread.sleep(passengerRate);
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
            Passenger p = createPassenger();
            game.randomStationAdd(p);

        }
    }

    public Passenger createPassenger(){
        Passenger p = null;
        p = new Passenger(Shape.randomShape());
        return p;
    }
}
