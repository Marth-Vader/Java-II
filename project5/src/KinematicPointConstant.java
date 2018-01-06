import java.awt.Color;
import java.awt.Point;

/**
 * Represents a point, coordinates don't change with state
 * @author Lucas Black, Martha Nguyen
 * @version 11/20/17
 */
public class KinematicPointConstant extends KinematicPointAbstract
{

    /**
     * Point containing constant x/y/z values
     */
    private PointND point;

    /**
     * Renders a constant point, doesn't change with state++++++++
     * @param color of the segment being drawn
     * @param width of the segment being drawn
     * @param x coordinate at constant point
     * @param y coordinate at constant point
     * @param z coordinate at constant point
     */
    public KinematicPointConstant(Color color, float width, double x, double y, double z)
    {

        //call the super constructor
        super(color, width);

        //create a new point and add the x/y/z points to point
        point = new PointND();
        point.add("x", new GeneralValue(x));
        point.add("y", new GeneralValue(y));
        point.add("z", new GeneralValue(z));


    }

    /**
     * 
     * @param state at which infant is in
     * @param screenSubfield corresponds to value we want
     * @return GeneralValue object corresponding to screenSubfield
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {

        //return the GeneralValue at this PointND
        return point.getValue(screenSubfield);

    }
}
