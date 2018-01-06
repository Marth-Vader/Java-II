
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Point ND holds the values associated with a Field's subFields
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/17
 */
public class PointND implements Iterable<String> {
    
    /**
     * Treemap of values with key to access GeneralValue
     */
    private TreeMap<String, GeneralValue> values = new TreeMap<>();
    
    
    
    /**
     * PointND constructor creates a new PointND object
     * 
     */
    public PointND() { 
        //create new Treemap
        values = new TreeMap<String, GeneralValue>();
    }
    
    
    
    /**
     * adds the key subFieldName with value value to values treemap
     * @param subFieldName of the Field
     * @param value at the subField
     */
    public void add(String subFieldName, GeneralValue value) {
        //add value to values Treemap
        values.put(subFieldName, value);
    } 
    
    
    
    /**
     * gets the GeneralValue of the key subFieldName
     * @param subFieldName of the Field
     * @return GeneralValue corresponding to the subFieldName
     */
    public GeneralValue getValue(String subFieldName) {
        
        //create object to the returned
        GeneralValue out = new GeneralValue("NaN");
         
        //if the values treemap contains the subFieldName
        if (values.containsKey(subFieldName)) { 

                
            //the object to be returned is a GeneralValue created using the value of the subfield
            out = values.get(subFieldName); 

        }
                
             
           
        
        else {
            //object returned is invalid
            out = new GeneralValue(); 
        }
            
        
        //return the value of given key
        return out; 
    }
        
    
    
    
    
    /**
     * return the size of the PointND object ie how many 
     * subfields a field has
     * @return size of the object
     */
    public int size() {
        
        //return the size of the values treemap
        return values.size(); 
    }
    
    
    
    
    /**
     * create an iterator with the keys of the PointND object
     * @return Iterator<String> iterator with String keys
     */
    public Iterator<String> iterator() {
        
        //return the iterator for the keys in the values treeMap
        return values.keySet().iterator();    
    }
     
     
    
    /**
     * toString method for PointND objects
     * @return String containing the PointND object in format of
     * key = value; 
     */
    public String toString() { 
        
        //create a string object to be appended and returned
        String out = "";     
        
        //create an iterator of String keys
        Iterator<String> it = values.keySet().iterator(); 
 
        //while the iterator has a next
        while (it.hasNext()) { 
            //assign next key to a string
            String key = it.next().toString(); 
            
            //append the string using the key, use the key to get the value
            out += (key + " = " + values.get(key) + "; ");
        } 
        
        //return the appended string
        return out;  
    }

}
