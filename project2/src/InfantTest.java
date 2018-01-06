import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

/**
 * Tests the methods of the Infant class
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 *
 */
public class InfantTest 
{
    

    
    @Test
    /**
     * Tests the methods of the Infant class
     * @throws IOException happens when input/output bad/doesn't exist
     */
    public void testConstructor() throws IOException
    {
        //test the constructor
        final Infant newBlood = new Infant("data", "c1"); 

        //test the getItem method
        Assert.assertEquals("0.64: left_wrist=<0.115,0.283,-0.112>, right_wrist=<0.262,-0.288,-0.116>",
                newBlood.getItem(2).getItem(32).toString());
         
        //test the getSize method
        Assert.assertEquals(5, newBlood.getSize());  
        
        //test the getInfantId method
        Assert.assertEquals("c1", newBlood.getInfantID());
        
    } 
    

    

}
