import java.io.IOException;

/**
 * Driver class, used for informal testing

 * 
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class Driver
{
    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        Trial trial = new Trial("data", "k1", 6); 
        
        System.out.println(trial.getState(100));
        
        System.out.println("MaxLeft:" + trial.getMaxLeftWrist (0));
        
    } 
}
