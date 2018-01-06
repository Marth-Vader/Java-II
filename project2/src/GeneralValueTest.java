import org.junit.Assert;

import org.junit.Test;


/**
 * Test the constructors and methods of GeneralValue class
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 */
public class GeneralValueTest
{ 
    /**
     * Test constructor with no string input
     */
    @Test
    public void testEmptyConstructor()
    {
        //empty constructor
        GeneralValue empty = new GeneralValue();
        //test valid false, empty
        Assert.assertFalse(empty.isValid());

    }    
    
    
     /**
      * Test constructor with valid string input
      */
    @Test 
    public void testValidConstructor()
    {
      //constructor with valid value
        GeneralValue test = new GeneralValue("2");
        //equal valued constructor for testing
        GeneralValue testSameVal = new GeneralValue("2");
        //constructor with a valid value greater than "test"
        //also tests the GeneralValue constructor with double as param
        GeneralValue compare = new GeneralValue(3); 
        //test valid true
        Assert.assertTrue(test.isValid());
        //test getDoubleValue method
        Assert.assertEquals(2, test.getDoubleValue(), 0.00001);
        //test true toString
        Assert.assertEquals("2.000", test.toString()); 
       
        //test isLessThan where this value < compared value
        Assert.assertTrue(test.isLessThan(compare));
        //test isLessThan where this value > compared value
        Assert.assertFalse(compare.isLessThan(test));
        //test isLessThan where this value = compared value
        Assert.assertFalse(test.isLessThan(testSameVal));
         
        //test isGreaterThan where this value > compared value
        Assert.assertTrue(compare.isGreaterThan(test));
        //test isGreaterThan where this value < compared value
        Assert.assertFalse(test.isGreaterThan(compare));
        //test isGreaterThan where this value = compared value
        Assert.assertFalse(test.isGreaterThan(testSameVal)); 
    }    
    
   
    /**
     * Test constructor with invalid string input
     */
    @Test 
    public void testNaNConstructor() throws InvalidValueException
    {
        //constructor with NaN
        GeneralValue invalid = new GeneralValue("NaN"); 
        //constructor with NaN used to evaluate isGreater/isLessThan
        GeneralValue compInvalid = new GeneralValue("NaN");
        //constructor with a valid value used to evaluate isGreater/isLessThan
        GeneralValue compValid = new GeneralValue(3);
        GeneralValue five = new GeneralValue(5);
      
        //parse NaN to a double to test double General Value constructor
        //
        double nan = Double.parseDouble("NaN"); 
        //constructor to test if the parsed NaN General value is invalid
        GeneralValue invalidDouble = new GeneralValue(nan); 
        
        //test valid false, no value
        Assert.assertFalse(invalid.isValid()); 
        //test false toString
        Assert.assertEquals("invalid", invalid.toString());
        
               
        //test isLessThan for this value = invalid vs invalid 
        Assert.assertFalse(invalid.isLessThan(compInvalid));  
        //test isLessThan for this = invalid vs valid
        Assert.assertFalse(invalid.isLessThan(compValid));   
        //test isLessThan for this = valid vs invalid
        Assert.assertTrue(compValid.isLessThan(invalid));      
        Assert.assertTrue(compValid.isLessThan(five));  
        
        //test isGreaterThan for this value = invalid vs invalid 
        Assert.assertFalse(invalid.isGreaterThan(compInvalid));
        //test isGreaterThan for this = invalid vs valid
        Assert.assertFalse(invalid.isGreaterThan(compValid));
        //test isLessThan for this = valid vs invalid
        Assert.assertTrue(compValid.isGreaterThan(invalid));
         
        
    }     
    
    
    
    /**
     * Test the InvalidValue exception
     * @throws InvalidValueException happens when value is invalid
     */
    @Test
    public void testException() throws InvalidValueException
    {
        //create invalid GeneralValue
        GeneralValue inval = new GeneralValue();
        boolean thrown = false;
       

        //checks validity of value
        if (inval.isValid () == false)
        {

            //error will be thrown so throw is assigned to true
            thrown = true;
            Assert.assertTrue(thrown); 
            throw new InvalidValueException("Invalid");
        }


          
           
        
    }
    
}

