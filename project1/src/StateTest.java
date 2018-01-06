import org.junit.Assert;

import org.junit.Test;
/**
 * 
 * Test the methods of the State class
 * @author Lucas Black, Martha Nguyen
 * @version 2017-09-17
 *
 */
public class StateTest
{
//
//    /**
//     * Test the State constructor and methods  
//     */
    @Test
    public void test()
    {
//
//        //create test object
        State test = new State("1, 1, 2, 3, 4, 5, 6");
//        
//        //test getLeftWrist method
        Assert.assertEquals("1.000,2.000,3.000", test.getLeftWrist().toString());
//        
//        //test getRightWrist method
        Assert.assertEquals("4.000,5.000,6.000", test.getRightWrist().toString());
//        
//        //test getTime method
        Assert.assertEquals(1.00, test.getTime(), 0.0001);  
//        
//        //test toString
//        //create string to compare
        String compare = "1.00: left_wrist=<1.000,2.000,3.000>, right_wrist=<4.000,5.000,6.000>";
        Assert.assertEquals(compare, test.toString()); 
//
    } 
//    
//
//
}













