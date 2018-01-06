import java.util.Iterator;

/**
 * MultipleItemAbstract class contains methods that can be used for Infant/Trial objects
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
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
    
    
    
    public State getMaxState(String fieldName, String subFieldName) { 
         
        State maxState = new State();
        
        
        
        for (int i = 0; i < this.getSize (); i++) {
            if (this.getItem(0).getMaxState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) {
                maxState = this.getItem(0).getMaxState(fieldName, subFieldName);
                continue;
            }
        }
 
        
        
        for (int i = 0; i < this.getSize(); i++) {  
             
            if (this.getItem(i).getMaxState(fieldName, subFieldName).getValue (fieldName, subFieldName).isValid ()) {
                
                if (maxState.getValue(fieldName, subFieldName).isValid() &&  
                        maxState.getValue(fieldName, subFieldName).isLessThan((this.getItem(i).getMaxState(fieldName, subFieldName).
                                getValue(fieldName, subFieldName)))) {   
                     
                    maxState = this.getItem(i).getMaxState(fieldName, subFieldName);  
                }  
                
                
                else {
                    
                    continue;
                }

            }
            
             

        }
        
        return maxState;      
        
    } 
    
    
    public State getMinState(String fieldName, String subFieldName) {
        
        
       State minState = new State();
       
       
       
       for (int i = 0; i < this.getSize (); i++) {
           if (this.getItem(0).getMinState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid()) {
               minState = this.getItem(0).getMinState(fieldName, subFieldName);
               continue;
           }
       }

       
       
       for (int i = 0; i < this.getSize(); i++) {  
            
           if (this.getItem(i).getMinState(fieldName, subFieldName).getValue(fieldName, subFieldName).isValid ()) {
               
               if (minState.getValue(fieldName, subFieldName).isValid() &&  
                       minState.getValue(fieldName, subFieldName).isGreaterThan((this.getItem(i).getMinState(fieldName, subFieldName).
                               getValue(fieldName, subFieldName)))) {   
                    
                   minState = this.getItem(i).getMinState(fieldName, subFieldName);  
               }  
               
               
               else {
                   
                   continue;
               }

           }
           
            

       }
       
       return minState;  
    }
    
    
    public GeneralValue getAverageValue(String fieldName, String subFieldName) {
        
        double num = 0; 
        double den = 0;
        
        for (int i = 0; i < this.getSize(); i++) { 
            
            if (this.getItem(i).getAverageValue(fieldName, subFieldName).isValid()) {
                
                num += this.getItem(i).getAverageValue(fieldName, subFieldName).getDoubleValue();
                den++;
            }
            
        }
        
        
        GeneralValue avg = new GeneralValue(num / den);
        
        return avg;
    }
    
    
}