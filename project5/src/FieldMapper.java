import java.util.Iterator;

import java.util.TreeMap;
/**
 * Fieldmapper maps the column names of a file and subfields
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/17
 * 
 */
public class FieldMapper implements Iterable<String>
{ 
    
     
    /**
     * Treemap containing fields
     */
    private TreeMap<String, Field> fieldMap = new TreeMap<>(); 
     
     
    
    /**
     * fieldMapper constructor, creates a treemap containing Fields, then each Field
     * has a treemap of subFields
     * @param columnNames the names of the columns at the top of a file
     */
    public FieldMapper(String[] columnNames) {    
          
        fieldMap = new TreeMap<String, Field>();
          
        //index of the columnNames array to be used in the for each loop
        int index = 0;
          
        //current field object to be use in the treeMap
        Field currentField = new Field();
        
        //loops through all the variables in the columnNames array
        for (String name: columnNames) {
            //fieldName to be set
            String fieldName;
              
            //checks if the column name has a subfield
            if (name.charAt(name.length() - 2) == '_') {
                //sets the name of the Field
                fieldName = name.substring(0, (name.length() - 2));
                //sets the name of the subfield
                String subFieldName = name.substring(name.length() - 1);
                  
                //if the column index is at 0
                if (index == 0) {
                    //adds the subfield to the first field (in this case, currentfield
                    currentField.addSubField(subFieldName, index);
                    //puts the field into the tree map
                    fieldMap.put(fieldName, currentField);
                }
                    
                  
                else if (!fieldMap.containsKey(fieldName)) {
                    //creates new field if the keys doesnt exist match
                    Field newField = new Field();
                    currentField = newField;
                    //adds the subfield (last character of the string) to the field
                    currentField.addSubField(subFieldName, index);
                    //add key and value to the fieldMap
                    fieldMap.put(fieldName, currentField); 
                } 
                    
                  
                //if the fieldName already exists in the fieldMap
                else {
                    //sets the currentField to the field corresponding to the fieldName
                    currentField = fieldMap.get(fieldName);
                    //adds subfield to existing field
                    currentField.addSubField(subFieldName, index);
                } 
                    
                    
                  
            }
                
              
              //if there is not an underscore, aka no subfield, then just put the name and the field into the treeMap
            else {
                  
                fieldName = name;
                  
                if (index == 0) {
                    //adds the no character subfield
                    currentField.addSubField("", index);
                    //puts the field into the tree map
                    fieldMap.put(fieldName, currentField);
                }
                    
                  
                else {
                      
                    //creates new field if the keys doesn't exist match 
                    Field newField = new Field();
                    //sets the newField to the currentField
                    currentField = newField;
                    //doesn't add subField to field that has no subfields
                    currentField.addSubField("", index);
                    //add key and value to the fieldMap 
                    fieldMap.put(fieldName, currentField);
                }
            }
                    
                
              
            index++; 
        }
    }
            
        
    
    

    
    /**
     * gets the Field object for a given String fieldName
     * @param fieldName is the key, gets the Field for that key
     * @return Field corresponding to String fieldName
     */
    public Field getField(String fieldName) {
        
        //get the Field at a specific key fieldName
        return fieldMap.get(fieldName);
    }
    
    

    
    /**
     * Populates the points of each subField in Field in fieldMapper
     * Maps values to given fieldName's subfields
     * @param stringValues String of values read in to be mapped
     * @param fieldName key to access the Field
     * @return PointND object containing GeneralValues mapped to it
     * based on stringValues fed into method
     */
    public PointND extractPointND(String[] stringValues, String fieldName) {
        
        if (!fieldMap.containsKey(fieldName)) {
            return null;
        }
        
        //this is what we return
        PointND out = new PointND(); 
        
        //iterator to go through the subFields of a given fieldName key
        Iterator<String> fieldit = fieldMap.get(fieldName).iterator(); 
 
        //for loop goes on for as long as there are subFields in a field
        for (int i = 0; i < fieldMap.get(fieldName).size(); i++) { 
            
            //assign the next String in the iterator to use as a key
            String fieldnem = fieldit.next(); 
            
            //get the index of the subField we want to add the value to
            int inti = this.getField(fieldName).getIndex(fieldnem);
            
            //create a GeneralValue using the index inti
            GeneralValue point = new GeneralValue(stringValues[inti]);
            
            //add to the PointND object to be returned
            out.add(fieldnem, point);        

        } 

        //return the final PointND object
        return out;    
    }
    
    
    
    /**
     * gets the size of the fieldMap
     * @return int size of fieldMap 
     */
    public int size() {
        
        //call the size method for treemap object
        return fieldMap.size(); 
    }
    
    
    
    /**
     * implements iterator interface, gets iterator of keys for FieldMapper object
     * @return Iterator<String> iterator object for FieldMapper keys
     */
    public Iterator<String> iterator() {
        
        //creates iterator object containing keys of fieldMap
        return fieldMap.keySet().iterator(); 
    }
     
    
}
