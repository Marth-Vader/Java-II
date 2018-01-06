
/**
 * Point3D class
 * 
 * Captures both a double value and whether or not it is valid
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class Point3D
{
    private GeneralValue[] values = new GeneralValue[3]; 
    
    /**
     * Point 3D constructor
     * @param x dimension
     * @param y dimension
     * @param z dimension 
     */
    public Point3D(GeneralValue x, GeneralValue y, GeneralValue z)
    {
        values[0] = x;
        values[1] = y; 
        values[2] = z;  
    }
    
    
    /**
     * gets the value at a specified dimension
     * @param dimension of GeneralValue
     * @return GeneralValue array value at dimension
     */
    public GeneralValue getDimValue(int dimension)
    {
        return values[dimension];
    }
    
    
    /**
     * gets the array of GeneralValue values
     * @return values array of GeneralValues
     */
    public GeneralValue[] getValues() 
    {
        return values; 
    }
    
    
    /**
     * outputs x, y, z coordinates for Point3D object
     * @return string of GeneralValue array values
     */
    public String toString()
    {
        String out = String.format("%s,%s,%s", values[0].toString(), values[1].toString(), values[2].toString());
        return out;
    }
    
    

}
