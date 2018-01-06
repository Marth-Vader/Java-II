//import java.util.Iterator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PointND implements Iterable<String> {
    
    private TreeMap<String, GeneralValue> values = new TreeMap<>();
    
    
    public PointND() {
        //create new Treemap
        values = new TreeMap<String, GeneralValue>();
    }
    
    
    public void add(String subFieldName, GeneralValue value) {
        //add value to values Treemap
        values.put(subFieldName, value);
    } 
    
    
    public GeneralValue getValue(String subFieldName) {
        
        
        GeneralValue out = new GeneralValue("NaN");
        
        if (values.containsKey(subFieldName)) { 
            
            if (values.get(subFieldName).isValid()) {
                
            out = new GeneralValue(values.get(subFieldName).getDoubleValue()); 
            
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
    
    
    public int size() {
        
        return values.size(); 
    }
    
    
    //uhh i think this is how you return an iterator???
    public Iterator<String> iterator() {
        
        //return the iterator for the keys in the values treeMap
        return values.keySet().iterator();    
    }
     
     
    
    public String toString() { 
        
        String out = "";     
        
        Iterator<String> it = values.keySet().iterator();
 
         
        while(it.hasNext()) { 
            
            String key = it.next().toString(); 
            //what object is this supposed to get a value from
            out += (key + " = " + values.get(key) + "; ");
        } 
        
        
        return out;  
    }

}
