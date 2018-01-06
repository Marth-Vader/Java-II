import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains constructor for Infant (created from Trial objects)
 * Contains methods for Infant objects
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
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
        
            //creates new Trial object for each week
            Trial week = new Trial(directory, infantID, i);
            
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
     * Method that returns the String infantID
     * @return the infantID
     */
    public String getInfantID()
    {
        //return the infant ID
        return infantID;
    }
    
}
