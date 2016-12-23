package View;

/**
 * Created by Toinecoch on 23/12/16.
 */
public enum Day {

    Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);

    private final int number;
    Day(int number){
        this.number = number;
    }
    private static Day[] vals = values();
    public Day next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }

}
