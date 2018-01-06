import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Abstract class responsible for rendering the infant in the panels
 * @author Lucas Black, Martha Nguyen
 * @version 11/21/17
 */
public abstract class KinematicPointAbstract { 

    /**
     * Arraylist containing children for a point
     */
    private ArrayList<KinematicPointAbstract> children;

    /**
     * Color of the segment
     */
    private Color color;

    /**
     * Stroke used to draw
     */
    private BasicStroke stroke;

    /**
     * 
     */
    private static double scale = 1.0;

    /**
     * KinematicPointAbstract constructor
     * 
     * @param color of the segment being drawn
     * @param width of the segment being drawn
     */
    public KinematicPointAbstract(Color color, float width) {

        // new children
        this.children = new ArrayList<KinematicPointAbstract>();
        // assign color to color for this object
        this.color = color;
        // extract dimensions that correspond to the x and y screen coordinates
        
        // if all four values are valid, then transform the values into pixel
        // coordinates and draw a line
        // between the two points

        // use BasicStroke to draw line segment
        this.stroke = new BasicStroke(width);

        // recursively draw the child point

    }

    /**
     * adds child to children arraylist, like the left wrist to the left shoulder
     * @param child to be added to the children arraylist
     */
    public void addChild(KinematicPointAbstract child) {
        
        // adds child to the children arrayList
        children.add(child);

    }

    /**
     * Recursive method used to draw the model
     * @param g graphic
     * @param state at which infant is in aka the time step
     * @param screenXSubfield x coordinate
     * @param screenYSubfield y coordinate
     */
    public void draw(Graphics2D g, State state, String screenXSubfield, String screenYSubfield) {
        
        //starting x and y coordinates
        GeneralValue x1 = this.getScreenCoordinate(state, screenXSubfield);
        GeneralValue y1 = this.getScreenCoordinate(state, screenYSubfield);

        
        //iterate through all of the children
        for (KinematicPointAbstract child : children) {
            
            //extract coordinates to draw to
            GeneralValue x2 = child.getScreenCoordinate(state,  screenXSubfield);
            GeneralValue y2 = child.getScreenCoordinate(state,  screenYSubfield);
            
            //check if points are valid
            if ((x1.isValid()) && (x2.isValid()) && (y1.isValid()) && (y2.isValid()) ) {
                
                //create a line from (x1, y1) to (x2, y2)
                Shape segment = new Line2D.Double(x1.getDoubleValue() * scale, 
                        y1.getDoubleValue() * scale, 
                        x2.getDoubleValue() * scale, 
                        y2.getDoubleValue() * scale);
                
                //set the color
                g.setColor(this.color);
                //set the stroke
                g.setStroke(this.stroke); 
                
                //draw the segment
                g.draw(segment); 
                
            }
            
            //recursively draw the child
            child.draw(g,  state, screenXSubfield , screenYSubfield);
        }
        
        
    }

    /**
     * Sets the scale
     * @param scale from meters to pixels
     */
    public static void setScale(double scale) {
        
        KinematicPointAbstract.scale = scale;

    }

    /**
     * Abstract method implemented in the KinematicPointConstant and
     * KinematicPointState classes
     * Gets the value corresponding to screenSubfield
     * @param state at which infant is in
     * @param screenSubfield value we want
     * @return GeneralValue type object
     */
    public abstract GeneralValue getScreenCoordinate(State state, String screenSubfield);
}
