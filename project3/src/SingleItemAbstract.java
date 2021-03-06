
/**
 * SingleItemAbstract contains abstract methods 
 * which are implemented in State class
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 */
public abstract class SingleItemAbstract
{


    /**
     * abstract method, implemented in State class
     * @param dim 0 for x, 1 for y, 2 for z
     * @return max left wrist of type GeneralValue
     */
    public abstract State getMaxState(String fieldName, String subFieldName);
    

    /**
     * abstract method, implemented in State class
     * @param dim 0 for x, 1 for y, 2 for z
     * @return min left wrist of type GeneralValue
     */
    public abstract State getMinState(String fieldName, String subFieldName);
    
    
    /**
     * abstract method, implemented in State class
     * @param dim 0 for x, 1 for y, 2 for z
     * @return avg of left wrist of type GeneralValue
     */
    public abstract GeneralValue getAverageValue(String fieldName, String subFieldName);
    
    
}


