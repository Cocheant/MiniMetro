
package View;

import Controller.GameController;
import com.sun.prism.Image;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javafx.scene.shape.Shape;

import static javafx.scene.input.DragEvent.DRAG_ENTERED;


/**
 * Created by Toinecoch on 20/12/16.
 */

public class Station<T extends Shape>{

    T t;

    static double lastCoordX,lastCoordY;

    double xCenter,yCenter;


    EventHandler<DragEvent> dragHandler = new EventHandler<DragEvent>() {

        public void handle(final DragEvent event) {

           if(event.getEventType() == DragEvent.DRAG_ENTERED) {
               System.out.println("DRAG_ENTERED");
           }
           else if (event.getEventType() == (DragEvent.DRAG_EXITED)) {
               System.out.println("DRAG_EXITED");
               t.setFill(Color.TRANSPARENT);
               Dragboard db = event.getDragboard();

               event.consume();
           }
           else if (event.getEventType()==(DragEvent.DRAG_OVER)) {
               System.out.println("DRAG_OVER");
               if (event.getGestureSource() != t &&
                       event.getDragboard().hasString()) {
                   event.acceptTransferModes(TransferMode.COPY);
                   t.setFill(Color.LIGHTGREY);
                   Dragboard db = event.getDragboard();
                   boolean success = false;
                   if (db.hasString()) {
                       success = true;
                   }

                   View.addElement(new MetroLine(lastCoordX,lastCoordY,getCenterX(),getCenterY(),Color.BLUEVIOLET));

                   lastCoordX = getCenterX();

                   lastCoordY = getCenterY();

               }
           }
           else if (event.getEventType()==(DragEvent.DRAG_DONE)) {
               System.out.println("DRAG_DONE");
               // if the data was successfully moved, clear it
               if (event.getTransferMode() == TransferMode.LINK) {

               }
               event.consume();
           }
           else if (event.getEventType()==(DragEvent.DRAG_DROPPED)) {
               System.out.println("DRAG_DROPPED");
               Dragboard db = event.getDragboard();
               boolean success = false;
               if (db.hasString()) {
                   success = true;
               }
               String s = db.getString();
               int index = s.indexOf("/");
               String s1 = s.substring(0,index-1);
               String s2 = s.substring(index+1);
               System.out.println(s1 +"/"+s2);
               // let the source know whether the string was successfully
               // transferred and used
               View.addElement(new MetroLine(Double.valueOf(s1),Double.valueOf(s2),xCenter,yCenter,Color.BLUEVIOLET));
               event.setDropCompleted(success);
           }
           event.consume();
        }
    };

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            System.out.println("onDragDetected");
            Dragboard db = t.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            lastCoordX = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
            lastCoordY = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;
            content.putString(""+String.valueOf(lastCoordX)+"/" + String.valueOf(lastCoordY)+"");
            db.setContent(content);

            event.consume();

        }
    };


    public Station(T t){
        this.t = t;
        xCenter = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
        yCenter = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;
        this.t.setFill(Color.TRANSPARENT);
        this.t.setStroke(Color.BLACK);
        this.t.setStrokeWidth(2);

        this.t.setOnDragDetected(mouseHandler);
        this.t.setOnDragDone(dragHandler);
        this.t.setOnDragDropped(dragHandler);
        this.t.setOnDragEntered(dragHandler);
        this.t.setOnDragExited(dragHandler);
        this.t.setOnDragOver(dragHandler);
    }

    public void setType(T t) { this.t = t; }
    public T getType() { return t; }
    public double getCenterX(){
        return xCenter;
    }
    public double getCenterY(){
        return yCenter;
    }
    public String toString(){
        return t.toString();
    }
}
