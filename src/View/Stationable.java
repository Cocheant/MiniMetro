package View;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

/**
 * Created by Toinecoch on 23/12/16.
 */
public interface Stationable {

    EventHandler<DragEvent> dragHandler = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {



        }
    };

}
