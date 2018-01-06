import java.util.Iterator;
import java.util.TreeMap;

/**
 * Representation of the state of a single time step 
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class State extends SingleItemAbstract implements Iterable<String> {
    
    private TreeMap<String, PointND> variables = new TreeMap<String, PointND>(); 
    
    private Trial trial;

 
 
 
    /**
     * State constructor
     *  
     */
    public State() {    
         
        //empty default constructor
    } 
    
    public State(Trial trial, FieldMapper fieldMapper, String values) {
        
        
        variables = new TreeMap<String, PointND>();
        
        this.trial = trial;
         

        //create iterator object to go through fieldMapper
        Iterator<String> fieldMapIt = fieldMapper.iterator();
        
        while (fieldMapIt.hasNext()) { 
            
            String key = fieldMapIt.next(); 
            PointND keyVal = fieldMapper.extractPointND(values.split(","), key);
            variables.put(key, keyVal); 
            
        }

    }
    
    
    public Trial getTrial() {
        
        return this.trial; 
    }
    
    
    public PointND getPoint(String fieldName) {
        
        //get the point for given key fieldName
        return variables.get(fieldName);
    }
    
    
    //not sure about this
    public GeneralValue getValue(String fieldName, String subFieldName) {
        
         
        String key = "NaN";
        
        GeneralValue out = new GeneralValue(key); 
        
        if (variables.containsKey(fieldName)) {
            
            if (variables.containsValue(variables.get(fieldName))) { 
                
                if(variables.get (fieldName).getValue (subFieldName).isValid ()) {
                
            out = new GeneralValue(variables.get(fieldName).getValue(subFieldName).getDoubleValue());
                }
            
            }   
            
            else { 
                out = new GeneralValue(); 
            }
        }
        
        else {
            out = new GeneralValue();
        }
        //return the value of given key
        
        
        
        return out; 
      
    }
    
     
    public State getMaxState(String fieldName, String subFieldName) {
         
        State maxState = new State(); 
        if (this.getValue(fieldName, subFieldName).toString().equals(variables.get(fieldName).getValue(subFieldName).toString())) {
            maxState = this;
        }
        
        return maxState;

    }
    
    
    public State getMinState(String fieldName, String subFieldName) {
        
        State minState = new State(); 
        if (this.getValue(fieldName, subFieldName).toString().equals(variables.get(fieldName).getValue(subFieldName).toString())) {
            minState = this;
        }
        
        return minState;
    }   
    
    
    public GeneralValue getAverageValue(String fieldName, String subFieldName) {

       
       GeneralValue out = new GeneralValue("NaN");  
       
       if (variables.containsKey(fieldName)) {
           
           if(variables.containsValue(variables.get(fieldName)) && 
                   variables.get(fieldName).getValue(subFieldName).isValid()) {
               
           out = new GeneralValue(variables.get(fieldName).getValue(subFieldName).getDoubleValue()); 
           
           } 
       }
       
       else { 
           
           out = new GeneralValue();
           
       }
       
       //return the value of given key          
       return out;
    }
    
    
    public Iterator<String> iterator() {
        
        return variables.keySet().iterator();   
    }
    
    
    public String toString() { 
        
        String out = "";   
        
        //be explicit about type 
        Iterator<String> it = variables.keySet().iterator();
        
        
        while (it.hasNext()) {
            String key = it.next();
            out += (key + "(" + this.getPoint(key) + ")\n");   
        }
        

        return out; 
    }

}