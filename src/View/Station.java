
package View;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;


/**
 * Created by Toinecoch on 20/12/16.
 */

public class Station<T extends Shape> {

    T t;

    private Group group;
    public Group getGroup(){ return this.group;}

    static double lastCoordX,lastCoordY;

    double xCenter,yCenter;

    Model.Shape shape;

    private ArrayList<ViewPassenger> passengers;
    public ArrayList<ViewPassenger> getPassengers(){ return this.passengers;}



    EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            View.addStationToLine(xCenter,yCenter, shape);
        }
    };

    public Station(T t){
        this.group = new Group();

        this.t = t;
        xCenter = (t.getLayoutBounds().getMaxX() + t.getLayoutBounds().getMinX())/2;
        yCenter = (t.getLayoutBounds().getMaxY() + t.getLayoutBounds().getMinY())/2;
        this.t.setFill(Color.TRANSPARENT);
        this.t.setStroke(Color.BLACK);
        this.t.setStrokeWidth(2);

        this.t.setOnMouseClicked(clickHandler);

        if(t.getClass().equals(Circle.class)){
            shape = Model.Shape.Circle;
        }
        else if(t.getClass().equals(Square.class)){
            shape = Model.Shape.Square;
        }
        else if(t.getClass().equals(EquilateralTriangle.class)){
            shape = Model.Shape.Triangle;
        }

        if(t.getClass().equals(Diamond.class)){
            shape = Model.Shape.Diamond;
        }
        if(t.getClass().equals(Cross.class)){
            shape = Model.Shape.Cross;
        }
        if(t.getClass().equals(Lozenge.class)){
            shape = Model.Shape.Lozenge;
        }

        this.passengers = new ArrayList<ViewPassenger>();
        group.setTranslateY(this.yCenter + 20);
        group.setTranslateX(this.xCenter);
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
    public Model.Shape getShape() {
        return shape;
    }

    public void addPassenger(final ViewPassenger p){

        //p.getType().setTranslateX(5);
        this.passengers.add(p);



        p.getType().setTranslateX(this.xCenter + passengers.size() * 10);
        p.getType().setTranslateY(this.yCenter + 20);
        System.out.println(passengers.size());

        //group.getChildren().add(p.getType());
        synchronized (View.class) {
            Platform.runLater(new Runnable() {
                public void run() {
                    View.addElement(p.getType());
                }
            });
        }

    }

    public void removePassenger(ViewPassenger p){

        this.passengers.remove(p);
        this.group.getChildren().remove(p.getType());

        for(ViewPassenger temp : this.passengers){
            this.group.getChildren().add(temp.getType());
        }
    }
}

    /*EventHandler<DragEvent> dragHandler = new EventHandler<DragEvent>()
    {
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
               //View.addElement(new MetroLine(Double.valueOf(s1),Double.valueOf(s2),xCenter,yCenter,Color.BLUEVIOLET));
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
    };*/