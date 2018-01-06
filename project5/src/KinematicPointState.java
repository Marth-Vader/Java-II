import java.awt.Color;

/**
 * Represents a point, coordinates change with state
 * @author Lucas Black, Martha Nguyen
 * @version 11/21/17
 *
 */
public class KinematicPointState extends KinematicPointAbstract {

    /**
     * fieldName corresponding to the point
     */
    private String fieldName;

    /**
     * Renders a point that changes with state
     * @param color of the segment being drawn
     * @param width of the segment being drawn
     * @param fieldName corresponding to point
     */
    public KinematicPointState(Color color, float width, String fieldName) {
        
        //call the super constructor
        super(color, width);
        //assign fieldName for this kinematic point state object
        this.fieldName = fieldName;

    }

    /**
     * Gets the coordinate corresponding to the fieldName and screenSubfield
     * @param state at which infant is in
     * @param screenSubfield corresponds to value we want to extract
     * @return GeneralValue corresponding to object's fieldName and screenSubfield
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield) {

        //return the GeneralValue corresponding to this object's
        //fieldName and screenSubfield
        return state.getValue(fieldName, screenSubfield);
    }

}
