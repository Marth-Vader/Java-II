import java.util.Iterator;
import java.util.TreeMap;

/**
 * Representation of the state of a single time step 
 * 
 * @author Lucas Black, Martha Nguyen 
 * @version 11/13/17
 *
 */
public class State extends SingleItemAbstract implements Iterable<String> {
    
    /**
     * treemap containing PointND objects with a String key
     */
    private TreeMap<String, PointND> variables = new TreeMap<String, PointND>(); 
    
    /**
     * trial associated with the State
     */
    private Trial trial;

 
 
 
    /**
     * State constructor
     *  
     */
    public State() {     
         
        //empty default constructor
    } 
    
    
    /**
     * State object constructor
     * @param trial associated with the State
     * @param fieldMapper used to 
     * @param values contains the comma separated values of a state
     */
    public State(Trial trial, FieldMapper fieldMapper, String values) {
        
        //create a treemap 
        variables = new TreeMap<String, PointND>();
        
        //assign the trial parameter
        this.trial = trial;
         

        //create iterator object to go through fieldMapper
        Iterator<String> fieldMapIt = fieldMapper.iterator(); 
        
        //while the iterator has a next
        while (fieldMapIt.hasNext()) { 
            
            //assign the next item in the iterator to a String key
            String key = fieldMapIt.next(); 
            //get the value associated at that key by calling extractPointND
            PointND keyVal = fieldMapper.extractPointND(values.split(","), key);
            //put the key and keyVal into the variables treemap
            variables.put(key, keyVal); 
            
        }

    }
    
    
    
    
    /**
     * get the trial associated with the State
     * @return Trial object associated with the State
     */
    public Trial getTrial() {
        
        //return the trial
        return this.trial; 
    }
    
    
    
    
    /**
     * get the PointND object in the treemap associated with the fieldName
     * @param fieldName ie the column name
     * @return PointNd associated with the fieldName
     */
    public PointND getPoint(String fieldName) {
        
        //get the point for given key fieldName
        return variables.get(fieldName);
    }
    
    
    
     
    /**
     * Gets the GeneralValue associated with the subFieldName of the fieldNAme
     * @param fieldName ie the column name
     * @param subFieldName of the fieldName (likex/y/z)
     * @return GeneralValue associated with the subFieldName of the fieldName
     */
    public GeneralValue getValue(String fieldName, String subFieldName) {
        

        //GeneralValue object to get edited and returned
        GeneralValue out = new GeneralValue();  
        
        //if the variables treemap contains the fieldName
        if (variables.containsKey(fieldName)) {
            //if the variables treemap contains the value of the fieldName
            //check if the subfields exist in the fieldname******
            //if (variables.get(fieldName).containsKey(subFieldName)) { 
                //if the value of the subFieldName of the Field is value
            if (variables.get(fieldName).getValue(subFieldName).isValid()) {
                    //GeneralValue object is created using the value of the subFieldName of the fieldName
                out = new GeneralValue(variables.get(fieldName).getValue(subFieldName).getDoubleValue());
            }
                
                

            else { 
                //invalid GeneralValue will be returned
                out = new GeneralValue(); 
            }
        
                
            
        }
        
        else {
//            //invalid GeneralValue will be returned
            out = new GeneralValue();
        }
            

        //return the GeneralValue object associated with the fieldName and subFieldName
        return out; 
      
    }
    
     
    
    
    /**
     * get the max State
     * @param fieldName ie the column name
     * @param subFieldName associated with the Field (like x/y/z)
     * @return the max state
     */
    public State getMaxState(String fieldName, String subFieldName) {
         

        //return the state
        return this;

    }
    
    
    
    /**
     * get the min State
     * @param fieldName ie the column name
     * @param subFieldName associated with the Field (like x/y/z)
     * @return the min State
     */
    public State getMinState(String fieldName, String subFieldName) {
        

        //return the state
        return this;
    }   
    
    
    
    
    /**
     * get the average generalValue in the state associated with the 
     * fieldName and subFieldNap
     * @param fieldName ie the column name
     * @param subFieldName associated with the Field
     * @return the average value as a GeneralValue object
     */
    public GeneralValue getAverageValue(String fieldName, String subFieldName) {
          
       
        //return the GeneralValue associated with the fieldName and subFieldName      
        return this.getValue(fieldName, subFieldName);
    }
        
    
    
    
    /**
     * creates an iterator object containing the keys of the variables treemap
     * @return Iterator<String> iterator contains keys as Strings
     */
    public Iterator<String> iterator() {
        //return the iterator object containg the keys of the variables treemap
        return variables.keySet().iterator();   
    }
    
    
    
    
    /**
     * the toString method for a State object
     * @return the string of a State in the format of
     * fieldName(PointND)\n
     */
    public String toString() { 
        
        //create string to be appended and returned
        String out = "";   
        
        //create iterator containing the keyset of the variables
        Iterator<String> it = variables.keySet().iterator();
        
        //while the iterator has a next object
        while (it.hasNext()) {
            //use the next object in the iterator as a key
            String key = it.next();
            //use the key to get value and append the string
            out += (key + "(" + this.getPoint(key) + ")\n");   
        }
        
        //return the appended string
        return out; 
    }

}