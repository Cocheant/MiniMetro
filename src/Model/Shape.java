package Model;



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

    private static Shape[] vals = values();
    public Shape next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }

    static public Shape set(int i)
    {
        return vals[i % vals.length];
    }


}
