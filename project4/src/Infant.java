import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains constructor for Infant (created from Trial objects)
 * Contains methods for Infant objects
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/17
 */
public class Infant extends MultipleItemAbstract
{

    /** Sequence of Trials */
    private ArrayList<Trial> trialList;
    
    /** ID for the infant */
    private String infantID;
    
    /** Maximum week */
    private static final int MAX_WEEK = 16;
    
      
    /**
     * Constructor for the Infant class; takes in a String of directory and 
     * infant ID
     * Then goes into the directory and adds the files (weeks) for that infant into
     * an arrayList
     * @param directory where data files stored ("data" in this case)
     * @param infantID of baby we want data for
     * @throws IOException happens when input/output bad/doesn't exist
     */
    public Infant(String directory, String infantID) throws IOException
    {
        
        //assign infantID 
        this.infantID = infantID; 
        
        //create trialList object to be populated
        this.trialList = new ArrayList<Trial>();


        //iterate from weeks 1-MAX_WEEK for an infantID in a certain directory
        for (int i = 1; i < MAX_WEEK; i++) 
        {
            
            //try to create trial objects and add them to trialList
            try
            {
        
                //how do i input Infant object as first parameter???
                //i think this is right???
                Trial week = new Trial(this, directory, infantID, i);
            
                //adds the Trial week to trialList
                trialList.add(week);
            
            }
            
            //go here if week doesn't exist for infant
            catch (IOException e)
            {
                //Do nothing if certain week for infant does not exist
            }
            
            //go onto the next week

        }

            
    }
    
    
    
    /**
     * Another Infant constructor
     * creates an infant object using the infant and the selected trials
     * @param infant that is used to create this subinfant
     * @param indices of the trials selected for infant
     */
    public Infant(Infant infant, int[] indices) {
        
        //assign infantID
        this.infantID = infant.getInfantID();
        
        //create trialList object to be populated
        this.trialList = new ArrayList<Trial>();
        
        //for every index in the indices array
        for (int i = 0; i <= indices.length; i++) {           
               
            //get the trial corresponding to week
            Trial week = infant.getItem(i);
            //add that week to the trialList
            this.trialList.add(week);    

        } 
        
        
        
    }
        

    
    
    /**
     * Method that returns the item of type Trial
     * @param index wanted for item 
     * @return the item of type Trial
     */
    public Trial getItem(int index)
    {
        //return the specified index of trial in trialList for Infant
        return trialList.get(index); 
    }
    
    
    
    
    /**
     * Method that returns the number of trials for given Infant
     * @return in size of infant
     */
    public int getSize()
    {
        //return number of trials for the infant
        return trialList.size();
    }
    
    

    /**
     * Gets the ID of the infant
     * @return string of infant ID
     */
    public String getInfantID()
    {
        //return the infant
        return infantID;
    }
    
    
    
    /**
     * get the iterator for trialList
     * @return iterator object
     */
    public Iterator<Trial> iterator() {
        
        //return the iterator for trialList
        return trialList.iterator();
    }
    
}
