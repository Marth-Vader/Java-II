import java.util.Iterator;
import java.util.TreeMap;


/**
 * Field class, creates treemap that contains subFields for each Field
 * contains methods for Field objects
 * @author Lucas Black, Martha Nguyen 
 * @version 11/13/17
 *
 */
public class Field implements Iterable<String> {
    
    /**
     * treemap of subFields for each Field
     */
    private TreeMap<String, Integer> subFields = new TreeMap<String, Integer>(); 
    
    
    /**
     * Field constructor, makes treemap of subFields
     */
    public Field() {
        
        //creates new treemap containing subfields of a Field
        subFields = new TreeMap<String, Integer>();      
    }
    
    
    /**
     * Adds subField to field
     * @param subFieldName is sub of Field, like x/y/z
     * @param columnIndex the index of the subfield, where it's added
     */
    public void addSubField(String subFieldName, int columnIndex) {
        
        //put key subFieldName and value columnIndex into subFields treemap
        subFields.put(subFieldName, columnIndex);     
    }
     
     
    /**
     * Finds and returns the index of subfield 
     * @param subFieldName sub of Field
     * @return integer of index, where the subfield was created
     */
    public Integer getIndex(String subFieldName) {
        
        Integer index = null; 
        
        //if the subField exists
        if (subFields.containsKey(subFieldName)) {
            //reassign the index of the subField to index variable
            index = subFields.get(subFieldName);
        }
        //return the index for a specific key subFieldName
        return index;
    } 

    
    
    /**
     * get the size of a field, how many subfields it has
     * @return int size of subfield
     */
    public int size() {

        //calls the size method for treemap object
        return subFields.size(); 
    }
    
    
    
    /**
     * implements iterator interface, gets iterator of keys for Field object
     * @return Iterator<String> iterator object for Field keys
     */
    public Iterator<String> iterator() {
        
        //returns an iterator of Strings containing keys of Field
        return subFields.keySet().iterator(); 
    }
    
    
     
    /**
     * the toString method for Field objects
     * @return a string of format subField(index)
     */
    public String toString() { 
        
        //create string to be appended and returned
        String out = "";  
        //SUBFIELD(INDEX)
        
        //create iterator to iterate through keyset of subFields
        Iterator<String> it = subFields.keySet().iterator(); 
        
        //while the iterator has a next string
        while (it.hasNext()) {
            
            //the string is assigned to a key
            String key = it.next().toString();
            
            //append the string, use the key to get the index of subfield
            out += (key + "(" + subFields.get(key) + "); ");
             
        }   
        
        //return the string
        return out;  
    }
}
