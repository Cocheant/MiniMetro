package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;

import java.util.List;

/**
 * Created by Toinecoch on 19/12/16.
 */
public class MetroLine extends Line{

    private double xStart, xEnd, yStart, yEnd;
    private Color color;
    private Path path;



    public MetroLine(){
        super(0,0,0,0);
        this.setStrokeWidth(4);

        xStart = 0;
        yStart = 0;
        xEnd = 0;
        yEnd = 0;

    }

    public MetroLine (Color color){
        super(0,0,0,0);
        this.setStroke(color);
        this.setStrokeWidth(4);
        xStart = 0;
        yStart = 0;
        xEnd = 0;
        yEnd = 0;
        this.color = color;
    }

    public MetroLine(double StartX, double StartY,double EndX,double EndY, Color color){
        super(StartX,StartY,EndX,EndY);
        this.setStroke(color);
        this.setStrokeWidth(4);
        xStart = StartX;
        yStart = StartY;
        xEnd = EndX;
        yEnd = EndY;
        Object station;

        this.color = color;

    }

    public void setStart(double x, double y){
        this.xStart = x;
        this.yStart = y;

    }
    public void setEnd(double x, double y){
        this.xEnd = x;
        this.yEnd = y;

    }

    public Color getColor(){
        return color;
    }



}
