
/**
 * Point3D class
 * 
 * Captures both a double value and whether or not it is valid
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 *
 */
public class Point3D
{
    
    /**
     * GeneralValue array containing x, y, z points
     */
    private GeneralValue[] values = new GeneralValue[3]; 
    
    
    /**
     * Point 3D constructor
     * @param x dimension
     * @param y dimension
     * @param z dimension 
     */
    public Point3D(GeneralValue x, GeneralValue y, GeneralValue z)
    {
        //assigns x value to first index of GeneralValue array
        values[0] = x;
        //assigns y value to second index of GeneralValue array
        values[1] = y; 
        //assigns z value to third index of GeneralValue array
        values[2] = z;  
    }
    
    
    /**
     * gets the value at a specified dimension
     * @param dimension of GeneralValue
     * @return GeneralValue array value at dimension
     */
    public GeneralValue getDimValue(int dimension)
    {
        //return the GeneralValue for given dimension
        return values[dimension];
    }
    
    
    /**
     * gets the array of GeneralValue values
     * @return values array of GeneralValues
     */
    public GeneralValue[] getValues() 
    {
        //returns the values array with x, y, z
        return values; 
    }
    
    
    /**
     * outputs x, y, z coordinates for Point3D object
     * @return string of GeneralValue array values
     */
    public String toString()
    {
        //formats the string to be returned
        String out = String.format("%s,%s,%s", values[0].toString(), values[1].toString(), values[2].toString());
        
        //return the formatted string
        return out;
    }
    
    

}
