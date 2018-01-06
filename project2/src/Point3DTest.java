import org.junit.Assert;

import org.junit.Test;

/**
 * Test the methods of Point3D class
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 */
public class Point3DTest
{
    /**
     * Test constructor, getDimValue, getValues
     */
    @Test
    public void test() 
    {
        GeneralValue first = new GeneralValue("1");
        GeneralValue second = new GeneralValue("2");
        GeneralValue third = new GeneralValue("3");
        
        Point3D test = new Point3D(first, second, third);
        
        //test getDimValue
        Assert.assertEquals(first, test.getDimValue(0));
        
        
        //test getValues
        //create array to compare
        GeneralValue[] expected = {first, second, third};
        Assert.assertArrayEquals(expected, test.getValues());
        
        
        
    } 
    
    
    /**
     * Test toString
     */
    @Test
    public void testToString()
    {
        GeneralValue first = new GeneralValue("1"); 
        GeneralValue second = new GeneralValue("2");
        GeneralValue third = new GeneralValue("3");
        
        Point3D test = new Point3D(first, second, third);
        
        //test toString
        Assert.assertEquals("1.000,2.000,3.000", test.toString());
    }
}
    


