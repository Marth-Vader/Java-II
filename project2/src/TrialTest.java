import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;
 
/**
 * Test the methods of the Trial class
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 *
 */
public class TrialTest  
{

    /**
     * Test Trial methods with file that has no NaN values
     * @throws IOException which happens when input/output bad/doesn't exist
     */
    @Test
    public void testTrialMethods() throws IOException
    {
        //create test object
        Trial trial = new Trial("data", "c1", 8);  
        
        //test getInfantID
        Assert.assertEquals("c1", trial.getInfantID());
        
        //test getWeek
        Assert.assertEquals(8, trial.getWeek()); 
        
        //test getFileName 
        Assert.assertEquals("data/subject_c1_w08.csv", trial.getFileName());
        
        //test getItem (was previously getState)
        Assert.assertEquals("0.64: left_wrist=<0.288,0.238,-0.047>, right_wrist=<0.146,-0.065,-0.238>",
                trial.getItem(32).toString());
        
        //test getSize
        Assert.assertEquals(15000, trial.getSize()); 

    }    
    
    


}
