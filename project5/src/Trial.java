import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * Representation of a single trial (file), States make up a trial
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/2017
 *
 */
public class Trial extends MultipleItemAbstract
{
    /** Sequence of states.   */
    private ArrayList<State> stateList;
    /** ID for the infant  */
    private Infant infant; 
    /** Week index.  */
    private int week;
    /** File name that was loaded. */
    private String fileName;  
    /** fieldMapper for this trial */
    private FieldMapper fieldMapper; 
      
    /** 
     * Trial constructor  
     * @param directory String representing the directory containing the data files
     * @param infantID String representing the infant ID
     * @param week int week for the data file.
     * @param infant baby
     * @throws IOException If there is an error finding or loading the data file.
     */
    public Trial(Infant infant, String directory, String infantID, int week) throws IOException 
    {
        this.infant = infant;  
        this.week = week;
        this.fileName = String.format("%s/subject_%s_w%02d.csv", 
                directory, infantID, week);
        
        
        this.stateList = new ArrayList<State>();
        
        // Open the file
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        String strg;
        
        //throw out header
        strg = br.readLine();   
        
        // Use the first line to construct the FieldMapper
        FieldMapper trialFieldMapper = new FieldMapper(strg.split(",")); 
        
        //read first line
        strg = br.readLine();   

        //while the next line has something to read
        while (strg != null)  
        {
            //each line is used to create a State object s
            State s = new State(this, trialFieldMapper, strg);
            //s is added to stateList ArrayList
            stateList.add(s);
            //goes to next line
            strg = br.readLine();
        }

        //close the buffered reader
        br.close(); 
        
        
        //assigns the fieldMapper to the fieldMapper for the week for the infant
        this.fieldMapper = trialFieldMapper;
    } 

    
    /**
     * gets State for given index
     * @param index of object wanted
     * @return State object for given index
     */
    public State getItem(int index) 
    {
        //return the items of a certain stateList index
        return stateList.get(index);        
    }
    
    
    /**
     * gets the size of the stateList
     * @return size of stateList of type int
     */
    public int getSize()
    {
        //return the size of the Trial object
        return stateList.size();
    }
    
    

    /**
     * returns the infant associated with the trial
     * @return infant for trial
     */
    public Infant getInfant()
    {
        //return the infant associated with the trial
        return infant;
    }

    
    
    /**
     * gets the week of the trial
     * @return the week of the trial
     */
    public int getWeek()
    {
        //return the week 
        return week;
    }

    
    
    /**
     * gets the file name of the trial
     * @return the file name of the trial
     */
    public String getFileName()
    {
        //return the file name
        return fileName;
    }
    

    /**
     * Return the week
     * @return String containing week for trial
     */
    public String toString() {
        
        String weekString = "";
        
        //if the week is 9 or less, then return "Week 0x"
        if (this.getWeek() <= 9) {
            weekString = "Week 0" + this.getWeek();
        }
        
        //if the week is 10 or greater, then return "Week x"
        else {
            weekString = "Week " + this.getWeek();
        }
        
        //return the week string
        return weekString;
    }
    
    
    
    /**
     * returns the fieldMapper for the trial
     * @return fieldMapper for trial
     */
    public FieldMapper getFieldMapper() {
             
        return fieldMapper;
    }
    
    
    

}
