//import java.io.IOException;

/**
 * Representation of the state of a single time step 
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class State
{
    /** Position of the left Wrist */
    private Point3D leftWrist;
    /** Position of the right Wrist */
    private Point3D rightWrist;
    /** Timestamp of the current sample. */
    private double time;


    /**
     * State constructor
     * @param strg of values
     */
    public State(String strg)   
    {
        // Array created to store the 7 String components from strg separately
        String[] list = strg.split(",");

        // Assign time to the value at index 0
        time = Double.parseDouble(list[0]);

        // Creating a GeneralValue Array to store the General Values of the left wrist
        GeneralValue[] left = new GeneralValue[3];
      
        //assigning x,y,z values to the left wrist array
        left[0] = new GeneralValue(list[1]);
        left[1] = new GeneralValue(list[2]);
        left[2] = new GeneralValue(list[3]);
        
        // Assign values to leftWrist from the Array "left"
        leftWrist = new Point3D(left[0], left[1], left[2]);

        // Creating a GeneralValue Array to store the general values of the right wrist
        GeneralValue[] right = new GeneralValue[3];
    
        //Assigning x,y,z values to the right wrist array
        right[0] = new GeneralValue(list[4]);
        right[1] = new GeneralValue(list[5]);
        right[2] = new GeneralValue(list[6]);

        // Assign values to rightWrist from the Array "right"
        rightWrist = new Point3D(right[0], right[1], right[2]);
    }

    
    /**
     * Gets the left wrist coordinates
     * @return leftWrist value of type Point3D
     */
    public Point3D getLeftWrist()
    {
        return leftWrist;
    }

    
    /**
     * Gets the right wrist coordinates
     * @return rightWrist value of type Point3D
     */
    public Point3D getRightWrist()
    {
        return rightWrist;
    }

    
    /**
     * Gets the time of state
     * @return time of state
     */
    public double getTime()
    {
        return time;
    }

    
    /**
     * Prints the time along with left and right wrist coordinates
     * @return string values of left and right wrists
     */
    public String toString() 
    {
        String out;
        out = String.format("%.2f: left_wrist=<%s>, right_wrist=<%s>", 
                time, leftWrist.toString(), rightWrist.toString());

        
        return out;
    }
}
