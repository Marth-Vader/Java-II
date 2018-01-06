/**
 * GeneralValue class
 * 
 * Captures both a double value and whether or not it is valid
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/2017
 *
 */  

public class GeneralValue 
{
    /** Is the value valid? */
    private boolean valid;
    /** Double value stored.  */
    private double doubleValue;
    
    /**
     * default constructor, value is invalid
     */
    public GeneralValue()  
    { 
        //returns false, no value
        valid = false;

    }
    
     
    /**
     * GeneralValue constructor, takes in a string
     * @param strg input of either a number or NaN
     */
    public GeneralValue(String strg)
    {
        //if the parameter for GeneralValue constructor is "NaN"
        if (strg.equals("NaN"))
        {

            //invalid value for string of "NaN"
            valid = false;

        }
        
        //if the parameter for GeneralValue constructor is a number
        else
        {
            valid = true; 
            //parse the string of number into a double value
            doubleValue = Double.parseDouble(strg);
        }
    }
    
    
    /**
     * GeneralValue constructor to take in a double as a parameter,
     * checks if the value is NaN or not and returns variables accordingly
     * @param doubleValue the double value passed from a single cell of from the data
     */
    public GeneralValue(double doubleValue)
    {
        //if the parameter for constructor is "NaN"
        if (String.valueOf(doubleValue).equals("NaN"))
        {
            //this is like returning null for a string
            //this.doubleValue = Double.NaN;
            //value is invalid
            valid = false; 

        } 
        
        //if the parameter for constructor is a number
        else
        {
            valid = true;
            //assign the doubleValue
            this.doubleValue = doubleValue;
        }
    }
    
    
    /**
     * Determines if value is valid or not
     * @return valid boolean, true if valid, false if invalid
     */
    public boolean isValid()
    {
        //returns validity       
        return valid;
    }
    
    
    /**
     * converts GeneralValue to double
     * @return doubleValue of GeneralValue
     * @throws InvalidValueException which is extension of RuntimeErrorException
     */
    public double getDoubleValue() throws InvalidValueException
    {
        
        //if value is invalid
        if (!valid)
        {
            //throw exception if try to parse invalid value
            throw new InvalidValueException("Oh no");
            
        }
        
        //return the double value
        return doubleValue;  
    }
    
    
    /**
     * Checks if this value is less than another general value (invalid or not), note: 
     * if the passed value is invalid, this value is considered to be less, and if both 
     * values are invalid, false is returned
     * @param value the GeneralValue that is either a double or invalid
     * @return true or false depending on if this value is less than the passed value
     * if the passed value is invalid, this value is considered to be less
     */
    public boolean isLessThan(GeneralValue value) throws InvalidValueException
    {

        //if the value of object for which method is being called is valid
        if (this.isValid()) 
        {
 
            //if value of object for which method is being called is less than the
            //value being compared to or if the value being compared to is invalid
            if (!value.isValid()) { 
                
                return true;
            }
            
            else if ((this.getDoubleValue() < value.getDoubleValue()))
            {   
                //return true 
                return (this.getDoubleValue() < value.getDoubleValue());     
            }  
            
            //any other cases for if value of object for which method is being called is valid
            else 
            {
                //return false
                return false;       
            }  
  
        }
        
        //if object for which method being called is invalid
        else
        {
            //return false
            return false;
        }
        
        

    
    }
     
    
    /**
     * Checks if this value is greater than another general value (invalid or not), note: 
     * if the passed value is invalid, this value is considered to be greater, and if both 
     * values are invalid, false is returned
     * @param value the GeneralValue that is either a double or invalid
     * @return true or false depending on if this value is evaluated to be less than the passed value
     */
    public boolean isGreaterThan(GeneralValue value)
    {
        //if the value of object for which method is being called is valid
        if (valid)
        { 
            //if value being compared to is invalid
            if (!value.isValid())
            {
                //return true
                return true; 
            }  
            
            //if value being compared to is greater than
            if (doubleValue > value.getDoubleValue()) 
            {
                //return true
                return doubleValue > value.getDoubleValue();
            }
            
            //any other cases
            else 
            {
                //return false
                return false;      
            }
        }
        
        //all other cases are caught here, where value of object for which method is
        //being called is invalid
        else
        {
            return false; 
        }
    }
     
     
    
    /**
     * converts doubleValue to string
     * @return string of GeneralValues
     */
    public String toString()
    { 
        //create and initiate empty string
        String out = "";
        
        //if the GeneralValue object is invalid
        if (!valid)
        {
            //return this string
            out = "invalid";
        }
        
        //if the GeneralValue object is valid
        else
        {
            //return this formatted string
            out = String.format("%.3f", doubleValue); 
        }
        
        //return the string
        return out;
    }
    
   
    
}
