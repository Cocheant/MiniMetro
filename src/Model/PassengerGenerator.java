package Model;

import Controller.GameController;

/**
 * Created by hugo on 1/12/2017.
 */
public class PassengerGenerator extends Thread {

    private Game game;
    private GameController controller;
    private boolean running;

    public void setRunning(boolean running) {
        this.running = running;
    }

    private int passengerRate = 1000;

    /**
     * Constructor by initialization
     * @param game the game which is linked to the passenger generator
     * @param controller the controller which is linked to the game & the generator
     */
    public PassengerGenerator(Game game, GameController controller){
        this.game = game;
        this.controller = controller;
        running = true;
    }

    /**
     * Thread logic
     */
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

    /**
     * Creates a new Passenger &  returns it
     * @return Passenger
     */
    public Passenger createPassenger(){
        return (new Passenger(Shape.randomShape()));
    }

    public void exitLoop(){
        running = false;
    }

}
