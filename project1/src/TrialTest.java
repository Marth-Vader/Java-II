import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;
 
/**
 * Test the methods of the Trial class
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class TrialTest 
{

    /**
     * Test Trial methods with file that has no NaN values
     * @throws IOException hehe
     */
    @Test
    public void testTrialMethods() throws IOException
    {
        //create test object
        Trial trial = new Trial("data", "k1", 3);  
        
        //test getInfantID
        Assert.assertEquals("k1", trial.getInfantID());
        
        //test getWeek
        Assert.assertEquals(03, trial.getWeek());
        
        //test getFileName 
        Assert.assertEquals("data/subject_k1_w03.csv", trial.getFileName());
        
        //test getState
        Assert.assertEquals("1.96: left_wrist=<0.109,0.158,-0.220>, right_wrist=<0.156,-0.279,-0.055>",
                trial.getState(98).toString());
        
        //test getSize
        Assert.assertEquals(15000, trial.getSize());
        
        //test getMaxLeftWrist
        Assert.assertEquals(0.172851, trial.getMaxLeftWrist(0), 0.0001);
        
        //test getMinLeftWrist
        Assert.assertEquals(0.025236, trial.getMinLeftWrist(0), 0.0001);
        
        //test getAverageLeftWrist
        Assert.assertEquals(0.0996798019333339, trial.getAverageLeftWrist(0), 0.0001);
        
    }    
    
    

    /**
     * Test Trial methods with file that has NaN values
     * @throws IOException hehe
     */
    @Test
    public void testTrialMethodsNaN() throws IOException
    {
        //create test object with different file that has NaN
        Trial trial = new Trial("data", "k1", 6); 
        
        //test getMaxLeftWrist
        Assert.assertEquals(0.244385, trial.getMaxLeftWrist(0), 0.0001);  
        
        //test getMinLeftWrist
        Assert.assertEquals(-0.045396, trial.getMinLeftWrist(0), 0.0001);
        
        //test getAverageLeftWrist
        Assert.assertEquals(0.08539534211055193, trial.getAverageLeftWrist(0), 0.0001);
        
    }

}
