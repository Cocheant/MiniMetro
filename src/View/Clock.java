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


}
