import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * Representation of a single trial
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class Trial
{
    /** Sequence of states.   */
    private ArrayList<State> stateList;
    /** ID for the infant  */
    private String infantID;
    /** Week index.  */
    private int week;
    /** File name that was loaded.  */
    private String fileName; 
    
    /**
     * Trial constructor
     * 
     * @param directory String representing the directory containing the data files
     * @param infantID String representing the infant ID
     * @param week int week for the data file.
     * 
     * @throws IOException If there is an error finding or loading the data file.
     */
    public Trial(String directory, String infantID, int week) throws IOException
    {
        this.infantID = infantID; 
        this.week = week;
        this.fileName = String.format("%s/subject_%s_w%02d.csv", 
                directory, infantID, week);
        
        
        this.stateList = new ArrayList<State>();
        
        // Open the file
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        String strg;
        
        //throw out header
        strg = br.readLine(); 
        
        //read first line
        strg = br.readLine();

        
        while (strg != null)
        {
            //each line is used to create a State object s
            State s = new State(strg);
            //s is added to stateList ArrayList
            stateList.add(s);
            //goes to next line
            strg = br.readLine();
        }

        br.close();
    }

    /**
     * gets the infant ID
     * @return the infant ID
     */
    public String getInfantID()
    {
        return infantID;
    }

    /**
     * gets the week of the trial
     * @return the week of the trial
     */
    public int getWeek()
    {
        return week;
    }

    /**
     * gets the file name of the trial
     * @return the file name of the trial
     */
    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * gets the specific state from stateList
     * @param index of the specific state
     * @return state the state from the state list at the given index
     */
    public State getState(int index) 
    {
        
        return stateList.get(index);
    }
    
    /**
     * gets the size of the state list
     * @return the size of the state list
     */
    public int getSize()
    {
        return stateList.size();
    }
    
    /**
     * Gets the maximum value for the given dimension of the left wrist from the data
     * @param dimension x, y, or z coordinate to be checked (0, 1, or 2 respectively)
     * @return maxLeft the maximum left wrist value for the given dimension
     */
    public double getMaxLeftWrist(int dimension)
    {
        //max value used to compare
        double maxLeft = stateList.get(0).getLeftWrist().getDimValue(dimension).getDoubleValue();
        
        //goes through each state in the stateList and compares the value to the first value on the list
        for (State i : stateList)
        {
            //Checks to make sure the value of the left wrist for the given dimension is valid and > maxLeft
            if (i.getLeftWrist().getDimValue(dimension).isValid() && 
                    i.getLeftWrist().getDimValue(dimension).getDoubleValue() > maxLeft)
            {
                //reassigns value to maxLeft if greater than last value
                maxLeft = i.getLeftWrist().getDimValue(dimension).getDoubleValue();
                
            }
        }
        
        return maxLeft;
    }
    
    /**
     * Gets the minimum value for the given dimension of the left wrist from the data
     * @param dimension x, y, or z coordinate to be checked (0, 1, or 2 respectively)
     * @return minLeft the minimum left wrist value for the given dimension
     */
    public double getMinLeftWrist(int dimension)
    {
        //min value used to compare
        double minLeft = stateList.get(0).getLeftWrist().getDimValue(dimension).getDoubleValue();
        
        //goes through each state in the stateList and compares the value to the first value on the list
        for (State i : stateList)
        {
          //Checks to make sure the value of the left wrist for the given dimension is valid and < minLeft
            if (i.getLeftWrist().getDimValue(dimension).isValid() && 
                    i.getLeftWrist().getDimValue(dimension).getDoubleValue() < minLeft)
            {
                //reassigns value to maxLeft if less than last value
                minLeft = i.getLeftWrist().getDimValue(dimension).getDoubleValue();
                
            }
        }
        
        return minLeft;  
    }
    
    /**
     * Gets the average value for the given dimension of the left wrist from the data
     * @param dimension x, y, or z coordinate to be checked (0, 1, or 2 respectively)
     * @return avgLeft the average left wrist value for the given dimension
     */
    public double getAverageLeftWrist(int dimension)
    {
        //average value for the given dimension, the sum to be added to, and the count for the number of variables
        double avgLeft;
        double sum = 0;
        int count = 0;
        
        //goes through each state in the stateList and compares the value to the first value on the list
        for (State i : stateList)
        {
            //Checks to make sure the value of the left wrist for the given dimension is valid
            if (i.getLeftWrist().getDimValue(dimension).isValid())
            {
                //adds to the sum and increments the count by 1
                sum += i.getLeftWrist().getDimValue(dimension).getDoubleValue();
                count++;
            }
        }
        
        //calculates the average by diving the sum by the count
        avgLeft = sum / count;
        
        return avgLeft;
    }
    
    
    

}
