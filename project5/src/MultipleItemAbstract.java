/**
 * MultipleItemAbstract class contains methods that can be used for Infant/Trial objects
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/2017
 *
 */
public abstract class MultipleItemAbstract extends SingleItemAbstract
{

    /**
     * abstract getSize method
     * not implemented in this class; implemented in Infant/Trial class
     * @return int, which is size of either Trial or Infant item, depending on what it was called for
     */
    public abstract int getSize(); 
    

    /** 
     * abstract getItem method
     * not implemented in this class; implemented in Infant/Trial class
     * @param index of either Infant or Trial object
     * @return item (either Trial for Infant object or State for Trial object) for a certain index
     */
    public abstract SingleItemAbstract getItem(int index); 
    
    
    /**
     * Gets the max state for Trial or Infant objects
     * @param fieldName is the name of the field (column)
     * @param subFieldName is the subField of Field (like x/y/z)
     * @return max State object
     */
    public State getMaxState(String fieldName, String subFieldName) { 
         
        //create empty State object, object will be returned
        State maxState = new State();     
        
        //this for loop finds the first State object with valid values at given fieldNAme and subFieldName
        for (int i = 0; i < this.getSize(); i++) {
            //if the value at given fieldNAme and subFieldName is valid 
            if (this.getItem(0).getMaxState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) { 
                //use that as the State object to compare
                maxState = this.getItem(0).getMaxState(fieldName, subFieldName);
                //and then exit the for loop and go onto find the max State
                continue;
            }
        }
 
        
        //loop through the States
        for (int i = 0; i < this.getSize(); i++) {  
            //if the value of the subfield of the field is valid
            if (this.getItem(i).getMaxState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) {
                //if maxState generalvalue is less than this 
                if (maxState.getValue(fieldName, subFieldName).isValid() &&  
                        maxState.getValue(fieldName, subFieldName).isLessThan((this.getItem(i).getMaxState(fieldName, 
                                subFieldName).getValue(fieldName, subFieldName)))) {   
                    //then resassign the maxState
                    maxState = this.getItem(i).getMaxState(fieldName, subFieldName);  
                }  
                
                
                else {
                    
                    continue;
                }

            }
            
             

        }
        //return the max State
        return maxState;      
        
    } 
    
    
    
    /**
     * Gets the min state for Trial or Infant objects
     * @param fieldName is the name of the field (column)
     * @param subFieldName is the subField of Field (like x/y/z)
     * @return min State object
     */
    public State getMinState(String fieldName, String subFieldName) {
        
        //create empty State object, object will be returned
        State minState = new State();
       
        //this for loop finds the first State object with valid values at given fieldNAme and subFieldName
        for (int i = 0; i < this.getSize(); i++) {
            //if the value at given fieldNAme and subFieldName is valid 
            if (this.getItem(0).getMinState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) {
                //use that as the State object to compare
                minState = this.getItem(0).getMinState(fieldName, subFieldName);
                //and then exit the for loop and go onto find the max State
                continue;
            }
        }
                 
            

       
        //loop through the States
        for (int i = 0; i < this.getSize(); i++) {  
            //if the value of the subfield of the field is valid
            if (this.getItem(i).getMinState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) {
                //if minState generalvalue is greater than this 
                if (minState.getValue(fieldName, subFieldName).isValid() &&  
                        minState.getValue(fieldName, 
                                subFieldName).isGreaterThan((this.getItem(i).getMinState(fieldName, 
                                        subFieldName).getValue(fieldName, subFieldName)))) {   
                    //then resassign the minState
                    minState = this.getItem(i).getMinState(fieldName, subFieldName);  
                }
                    
                    
               
               
                else {
                   
                    continue;
                }
            }
        }
                    

                
           
            

            
       
        //return the min State
        return minState; 
    }
        
        
    
    
    
    
    /**
     * get the average value for an Infant or Trial object
     * @param fieldName is the name of the field (column)
     * @param subFieldName is the subField of Field (like x/y/z)
     * @return avg which is the GeneralValue object created after
     * the average is calculated
     */
    public GeneralValue getAverageValue(String fieldName, String subFieldName) {
        
        //declare and initialize a numerator 
        double num = 0; 
        //declare and initialize a denominator
        double den = 0;
        
        //loop through the object
        for (int i = 0; i < this.getSize(); i++) { 
            
            //if the value at the subField of the field is valid
            if (this.getItem(i).getAverageValue(fieldName, subFieldName).isValid()) {
                
                //then convert the GeneralValue to a double and add to the numerator
                num += this.getItem(i).getAverageValue(fieldName, subFieldName).getDoubleValue();
                //increase the numerator
                den++;
            }
            
        }
        
        //create a GeneralValue object using the numerator and denominator
        GeneralValue avg = new GeneralValue(num / den);
        //return the average as a GeneralValue object
        return avg;
    }
    
    
}