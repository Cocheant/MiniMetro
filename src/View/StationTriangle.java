package View;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

/**
 * Created by Toinecoch on 19/12/16.
 */
public class StationTriangle extends EquilateralTriangle {

    private double xCenter, yCenter, size;
    private Color color;

    public StationTriangle(double xCenter, double yCenter, double size){
        super(xCenter,yCenter,size);
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.size = size;
    }


    public StationTriangle(EquilateralTriangle t){
        super(t.getCenterX(),t.getCenterY(),t.getSize());
        this.xCenter = t.getCenterX();
        this.yCenter = t.getCenterY();
        this.size = t.getSize();
        setEvents(Color.ORANGE);


    }
    public StationTriangle(StationTriangle st){
           super(st.getCenterX(),st.getCenterY(),st.getSize());
            this.xCenter = st.getCenterX();
            this.yCenter = st.getCenterY();
            this.size = st.getSize();
            setEvents(st.getColor());
        }


    public void setColor(Color c){
        color = c;
        this.setStroke(color);
        this.setFill(Color.TRANSPARENT);
        this.setStrokeWidth(4);
    }
    public void setEvents(Color c){
        this.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != this &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
        this.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != this &&
                        event.getDragboard().hasString()) {
                    setFill(color);
                }

                event.consume();
            }
        });
        this.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                setFill(Color.TRANSPARENT);
                event.consume();
            }
        });


    }


    public double getCenterX(){
        return xCenter;
    }
    public double getCenterY(){
        return yCenter;
    }
    public double getSize(){return size;}
    public Color getColor(){ return this.color; }

}
