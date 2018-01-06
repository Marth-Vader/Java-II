
/**
 * SingleItemAbstract contains abstract methods 
 * which are implemented in State class
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/2017
 */
public abstract class SingleItemAbstract
{
 


    /**
     * method to get the max state of a state, implemented in State class
     * @param fieldName ie first part of column name excluding subfield
     * @param subFieldName of the Field being accessed (like x/y/z)
     * @return the max State object
     */
    public abstract State getMaxState(String fieldName, String subFieldName);
    


    /**
     * method to get the min state of a state, implemented in State class
     * @param fieldName ie first part of column name excluding subfield
     * @param subFieldName of the Field being accessed (like x/y/z)
     * @return the min State object
     */
    public abstract State getMinState(String fieldName, String subFieldName);
    
    
    
    /**
     * method to get the average value in a state, implemented in State class
     * @param fieldName ie first part of column name excluding subfield
     * @param subFieldName of the Field being accessed (like x/y/z)
     * @return the average value as a GeneralValue object
     */
    public abstract GeneralValue getAverageValue(String fieldName, String subFieldName);
    
    
}


