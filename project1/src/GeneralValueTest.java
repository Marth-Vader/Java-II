import org.junit.Assert;

import org.junit.Test;

///**
// * Test the constructors and methods of GeneralValue class
// * @author Lucas Black, Martha Nguyen
// * @version 2017-09-17
// */
public class GeneralValueTest 
{ 
//    /**
//     * Test constructor with no string input
//     */
    @Test
    public void testEmptyConstructor()
    {
//        //empty constructor
        GeneralValue empty = new GeneralValue();
//        //test valid false, empty
        Assert.assertFalse(empty.isValid());
//
    }    
//    
//    
//     /**
//      * Test constructor with valid string input
//      */
    @Test 
    public void testValidConstructor()
    {
//      //constructor with valid value
        GeneralValue test = new GeneralValue("2");
//        //test valid true
        Assert.assertTrue(test.isValid());
//        //test getDoubleValue method
        Assert.assertEquals(2, test.getDoubleValue(), 0.00001);
//        //test true toString
        Assert.assertEquals("2.000", test.toString()); 
    } 
//    
//   
//    /**
//     * Test constructor with invalid string input
//     */
    @Test 
    public void testNaNConstructor()
    {
        //constructor with NaN
        GeneralValue invalid = new GeneralValue("NaN");
//        //test valid false, no value
        Assert.assertFalse(invalid.isValid());
//        //test false toString
        Assert.assertEquals("invalid", invalid.toString());
//        
    }
}

