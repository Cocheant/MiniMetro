package Model;


import java.util.*;

/**
 * Created by hugo on 12/23/2016.
 */
public enum Shape {
    Circle(1),
    Square(2),
    Triangle(3),
    Diamond(4),
    Cross(5),
    Lozenge(6);
    //Star(7),
    //Pentagon(8),


    private final int number;

    Shape(int number){
        this.number = number;
    }

    private static List<Shape> vals = Collections.unmodifiableList(Arrays.asList(values()));

    private static final int SIZE = vals.size();

    private static final Random RANDOM = new Random();


    public static Shape randomShape(){
        return vals.get(RANDOM.nextInt(SIZE));
    }


}
