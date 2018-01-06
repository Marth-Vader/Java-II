/**
 * GeneralValue class
 * 
 * Captures both a double value and whether or not it is valid
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */ 

public class GeneralValue 
{
    /** Is the value valid? */
    private boolean valid = true;
    /** Double value stored.  */
    private double doubleValue;
    
    /**
     * Constructor
     */
    public GeneralValue() 
    {
        valid = false;
    }
    
    
    /**
     * Valid constructor
     * @param strg input
     */
    public GeneralValue(String strg)
    {
        if (strg.equals("NaN"))
        {
            valid = false;
        }
        else
        {
            doubleValue = Double.parseDouble(strg);
        }
    }
    
    
    /**
     * Determines if value is valid or not
     * @return valid boolean
     */
    public boolean isValid()
    {
        return valid;
    }
    
    
    
    /**
     * converts GeneralValue to double
     * @return doubleValue of GeneralValue
     */
    public double getDoubleValue()
    {
        return doubleValue; 
    }
    
    
    
    /**
     * converts doubleValue to string
     * @return string of GeneralValues
     */
    public String toString()
    {
        String out = "";
        if (!valid)
        {
            out = "invalid";
        }
        else
        {
            out = String.format("%.3f", doubleValue);
        }
        
        return out;
    }
    
   
    
}
