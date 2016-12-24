package Model;

import java.awt.*;

/**
 * Created by hugo on 12/24/2016.
 */
public class Line {

    private int id;

    private Color col;

    /**
     * Default constructor
     */
    public Line(){
        this.id = 0;
        this.col = Color.blue;
    }

    /**
     * initialising constructor
     * @param id the id of the line
     * @param col the color of the line
     */
    public Line(int id, Color col){
        this.id = id;
        this.col = col;
    }
}
