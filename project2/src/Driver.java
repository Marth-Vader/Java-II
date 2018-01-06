import java.io.IOException;

/**
 * Driver class used for informal testing
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 */
public class Driver 
{

    
    public static void main (String[] args) throws IOException, InvalidValueException
    {
        
        Trial trial = new Trial("data", "c1",8); 
        
        System.out.println(trial.getSize());
        
        System.out.println (trial.getItem(32));
        
        
        
        Infant baby = new Infant("data", "c1");
        System.out.println(baby.getSize());
        System.out.println(baby.getItem(0).getFileName());
        System.out.println(baby.getItem(1).getFileName());
        System.out.println(baby.getItem(2).getFileName());
        System.out.println(baby.getItem(3).getFileName());
        System.out.println(baby.getItem(4).getFileName());
        
        
        System.out.println("Maxes:");
        System.out.println(baby.getItem(0).getMaxLeftWrist(0));
        System.out.println(baby.getItem(1).getMaxLeftWrist(0));
        System.out.println(baby.getItem(2).getMaxLeftWrist(0));
        System.out.println(baby.getItem(3).getMaxLeftWrist(0));
        System.out.println(baby.getItem(4).getMaxLeftWrist(0)); 
        
        System.out.println(baby.getMaxLeftWrist(0));
        
        
        System.out.println(baby.getItem(0).getMaxLeftWrist(1));
        System.out.println(baby.getItem(1).getMaxLeftWrist(1));
        System.out.println(baby.getItem(2).getMaxLeftWrist(1));
        System.out.println(baby.getItem(3).getMaxLeftWrist(1));
        System.out.println(baby.getItem(4).getMaxLeftWrist(1)); 
        
        System.out.println(baby.getMaxLeftWrist(1));
        
        
        System.out.println(baby.getItem(0).getMaxLeftWrist(2));
        System.out.println(baby.getItem(1).getMaxLeftWrist(2));
        System.out.println(baby.getItem(2).getMaxLeftWrist(2));
        System.out.println(baby.getItem(3).getMaxLeftWrist(2));
        System.out.println(baby.getItem(4).getMaxLeftWrist(2)); 
        
        System.out.println(baby.getMaxLeftWrist(2));
        
        
        System.out.println("Mins:");
        System.out.println(baby.getItem(0).getMinLeftWrist(0));
        System.out.println(baby.getItem(1).getMinLeftWrist(0));       
        System.out.println(baby.getItem(2).getMinLeftWrist(0));
        System.out.println(baby.getItem(3).getMinLeftWrist(0));        
        System.out.println(baby.getItem(4).getMinLeftWrist(0)); 
        
        System.out.println(baby.getMinLeftWrist(0));
        
        System.out.println("Mins split y");
        System.out.println(baby.getItem(0).getMinLeftWrist(1));
        System.out.println(baby.getItem(1).getMinLeftWrist(1));       
        System.out.println(baby.getItem(2).getMinLeftWrist(1));
        System.out.println(baby.getItem(3).getMinLeftWrist(1));        
        System.out.println(baby.getItem(4).getMinLeftWrist(1)); 
        
        //System.out.println ("total y");
        //only this line fucks up everything works fine
        System.out.println(baby.getMinLeftWrist(1));
        
        
        System.out.println(baby.getItem(0).getMinLeftWrist(2));
        System.out.println(baby.getItem(1).getMinLeftWrist(2));       
        System.out.println(baby.getItem(2).getMinLeftWrist(2));
        System.out.println(baby.getItem(3).getMinLeftWrist(2));        
        System.out.println(baby.getItem(4).getMinLeftWrist(2)); 
        
        System.out.println(baby.getMinLeftWrist(2));
        
        
        System.out.println ("Avgs:");
        System.out.println(baby.getItem(0).getAverageLeftWrist(0));
        System.out.println(baby.getItem(1).getAverageLeftWrist(0));
        System.out.println(baby.getItem(2).getAverageLeftWrist(0));
        System.out.println(baby.getItem(3).getAverageLeftWrist(0));
        System.out.println(baby.getItem(4).getAverageLeftWrist(0));
        
        System.out.println(baby.getAverageLeftWrist(0));
        
        
        System.out.println(baby.getItem(0).getAverageLeftWrist(1));
        System.out.println(baby.getItem(1).getAverageLeftWrist(1));
        System.out.println(baby.getItem(2).getAverageLeftWrist(1));
        System.out.println(baby.getItem(3).getAverageLeftWrist(1));
        System.out.println(baby.getItem(4).getAverageLeftWrist(1));
        
        System.out.println(baby.getAverageLeftWrist(1));
        
        
        System.out.println(baby.getItem(0).getAverageLeftWrist(2));
        System.out.println(baby.getItem(1).getAverageLeftWrist(2));
        System.out.println(baby.getItem(2).getAverageLeftWrist(2));
        System.out.println(baby.getItem(3).getAverageLeftWrist(2));
        System.out.println(baby.getItem(4).getAverageLeftWrist(2));
        
        System.out.println(baby.getAverageLeftWrist(2));
        
        
        
        
    }
}
