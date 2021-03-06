import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class contains all the testing, tests constructors and methods for each
 * class
 * 
 * @author Lucas Black, Martha Nguyen
 * @version 11/20/17
 * 
 *          Test FieldMapper for null PointND
 */
public class ProgramTest
{

    // create an object using each GeneralValue constructor with every parameter
    private static GeneralValue defaultObj = new GeneralValue();
    private static GeneralValue nAn = new GeneralValue("NaN");
    private static GeneralValue numStrg = new GeneralValue("3.72");
    private static GeneralValue dblInval = new GeneralValue(Double.parseDouble("NaN"));
    private static GeneralValue dblVal = new GeneralValue(6.98);

    // create PointND objects for testing
    private static PointND testPoint = new PointND();
    private static PointND emptyPoint = new PointND();

    /**
     * Test the GeneralValue class, constructors and methods
     */
    @Test
    public void generalValueTest()
    {

        // test validity of each object
        Assert.assertFalse(defaultObj.isValid());
        Assert.assertFalse(nAn.isValid());
        Assert.assertTrue(numStrg.isValid());
        Assert.assertFalse(dblInval.isValid());
        Assert.assertTrue(dblVal.isValid());

        // test both branches of getDoubleValue
        // test getDoubleValue for invalid object
        try
        {
            defaultObj.getDoubleValue();
        }
        catch (InvalidValueException e)
        {
            Assert.assertEquals(e.getMessage(), ("Oh no"));
        }

        // getDoubleValue for valid object
        Assert.assertEquals(3.72, numStrg.getDoubleValue(), 0.00001);

        // test isLessThan
        Assert.assertFalse(defaultObj.isLessThan(nAn));
        Assert.assertTrue(numStrg.isLessThan(dblVal));
        Assert.assertFalse(dblVal.isLessThan(numStrg));
        Assert.assertTrue(dblVal.isLessThan(defaultObj));

        // test isGreaterThan
        Assert.assertTrue(numStrg.isGreaterThan(nAn));
        Assert.assertTrue(dblVal.isGreaterThan(numStrg));
        Assert.assertFalse(numStrg.isGreaterThan(dblVal));
        Assert.assertFalse(nAn.isGreaterThan(dblVal));

        // test toString
        Assert.assertEquals("invalid", nAn.toString());
        Assert.assertEquals("3.720", numStrg.toString());

    }

    /**
     * Test the PointND class, constructors and methods
     */
    @Test
    public void pointNDTest()
    {

        // test that nothing is in the iterator before anything is added
        Assert.assertFalse(emptyPoint.iterator().hasNext());

        // test the add method

        testPoint.add("numStrg", numStrg);
        Assert.assertEquals("3.720", testPoint.getValue("numStrg").toString());

        // test getting a value for a subfield that doesn't exist
        Assert.assertEquals("invalid", testPoint.getValue("lucas").toString());

        // add some more
        testPoint.add("dblVal", dblVal);
        testPoint.add("naN", nAn);

        // test the size method
        Assert.assertEquals(3, testPoint.size());

        // test iterator
        Assert.assertTrue(testPoint.iterator().hasNext());

        // test the toString method
        Assert.assertEquals("dblVal = 6.980; naN = invalid; numStrg = 3.720; ", testPoint.toString());

    }

    /**
     * Test the fieldMapper and Field classes, constructors and methods
     * 
     * @throws IOException
     *             handles any input or output that's bad
     */
    @Test
    public void fieldMapperFieldTest() throws IOException
    {

        // tests the constructor where the first column name doesnt have a
        // subfield
        String[] xyz = { "three", "one_x", "two_y", "one_y" };
        FieldMapper test = new FieldMapper(xyz);
        Field fieldone = test.getField("one");
        Assert.assertEquals(3, test.size());
        Assert.assertEquals(fieldone, test.getField("one"));

        // test getIndex for subField that doesn't exist in an existing Field
        Assert.assertNull(test.getField("one").getIndex("o"));

        // tests the constructor where the first column name has a subfield
        String[] abc = { "one_x", "three", "two_y", "one_y" };
        FieldMapper test2 = new FieldMapper(abc);

        // test the iterator for FieldMapper
        Assert.assertTrue(test2.iterator().hasNext());

        // test size of field with no subfields
        Assert.assertEquals(1, test2.getField("three").size());

        // create Infant object
        Infant infant = new Infant("data", "k2");

        // buffered reader to take in file
        BufferedReader br = new BufferedReader(new FileReader(infant.getItem(0).getFileName()));

        // strg to store lines of file
        String strg;

        // assign first line to strg
        strg = br.readLine();

        // Use the first line to construct the FieldMapper
        FieldMapper fieldMapper = new FieldMapper(strg.split(","));
        // read next line of file (values)
        strg = br.readLine();

        // test extractPointND
        Assert.assertEquals("x = 0.214; y = -0.292; z = -0.019; ",
                fieldMapper.extractPointND(strg.split(","), "right_wrist").toString());

        // test getField
        Assert.assertEquals("x(0); y(3); ", test2.getField("one").toString());

        // close the bufferedreader
        br.close();

    }

    /**
     * Test the State and Trial and Infant classes, constructors and methods
     * 
     * @throws IOException
     *             handles any input or output that's bad
     */
    @Test
    public void stateTrialInfantTest() throws IOException
    {

        // create Infant object
        Infant infant = new Infant("data", "k2");

        // buffered reader to take in file
        BufferedReader br = new BufferedReader(new FileReader(infant.getItem(0).getFileName()));
        // strg to store lines of file
        String strg;
        // assign first line to strg
        strg = br.readLine();
        // Use the first line to construct the FieldMapper
        FieldMapper fieldMapper = new FieldMapper(strg.split(","));
        // read next line of file (values)
        strg = br.readLine();

        // create test State object
        State testState = new State(infant.getItem(0), fieldMapper, strg);

        // create State object w/empty constructor
        State emptyState = new State();

        // test empty State constructor
        Assert.assertEquals("", emptyState.toString());

        // test getFile
        Assert.assertEquals("data/subject_k2_w01.csv", testState.getTrial().getFileName());

        // test getPoint
        Assert.assertEquals("x = 0.248; y = 0.060; z = 0.248; ", testState.getPoint("left_wrist").toString());

        // test getValue
        Assert.assertEquals("0.248", testState.getValue("left_wrist", "x").toString());

        // test getMinState
        Assert.assertEquals("x = 0.214; y = -0.292; z = -0.019; ",
                testState.getMinState("right_wrist", "x").getPoint("right_wrist").toString());

        // test getMaxState
        Assert.assertEquals("x = 0.248; y = 0.060; z = 0.248; ",
                testState.getMaxState("left_wrist", "x").getPoint("left_wrist").toString());

        // test iterator
        Assert.assertTrue(testState.iterator().hasNext());

        // string to compare State
        String testStateString = "left_ankle(x = -0.081; y = 0.278; z = 0.009; )\n"
                + "left_elbow(x = 0.292; y = 0.117; z = 0.174; )\n" + "left_foot(x = 0.011; y = 0.320; z = -0.009; )\n"
                + "left_knee(x = -0.096; y = 0.142; z = -0.037; )\n"
                + "left_shoulder(x = 0.193; y = 0.038; z = 0.126; )\n"
                + "left_wrist(x = 0.248; y = 0.060; z = 0.248; )\n"
                + "right_ankle(x = -0.260; y = -0.098; z = -0.024; )\n"
                + "right_elbow(x = 0.165; y = -0.262; z = 0.066; )\n"
                + "right_foot(x = -0.361; y = -0.101; z = -0.041; )\n"
                + "right_knee(x = -0.116; y = -0.101; z = -0.038; )\n"
                + "right_shoulder(x = 0.182; y = -0.136; z = 0.021; )\n"
                + "right_wrist(x = 0.214; y = -0.292; z = -0.019; )\n" + "robot_vel(l = 0.005; r = -0.012; )\n"
                + "sippc_action( = 0.000; )\n" + "time( = 0.000; )\n"
                + "upper_back(x = 0.187; y = -0.049; z = 0.073; )\n";

        // test State toString
        Assert.assertEquals(testStateString, testState.toString());

        // test getAverageValue
        Assert.assertEquals("0.248", testState.getAverageValue("left_wrist", "x").toString());

        // test getAverageValue for a field that doesn't exist
        Assert.assertEquals("invalid", testState.getAverageValue("left_wristt", "x").toString());

        // test getValue for a field that doesn't exist
        Assert.assertEquals("invalid", testState.getValue("left_wristt", "x").toString());

        // test getFileName
        Assert.assertEquals("data/subject_k2_w01.csv", infant.getItem(0).getFileName());

        // test getSize for Infant
        Assert.assertEquals(4, infant.getSize());

        // test getInfantID
        Assert.assertEquals("k2", infant.getInfantID());

        // test getSize for Trial
        Assert.assertEquals(15000, infant.getItem(0).getSize());

        // test getInfantID
        Assert.assertEquals("k2", testState.getTrial().getInfant().getInfantID());

        // test getWeek
        Assert.assertEquals(1, testState.getTrial().getWeek());

        // test the getAverageValue
        Assert.assertEquals("0.248", infant.getItem(0).getItem(1).getAverageValue("left_wrist", "x").toString());

        // test getValue for empty constructor
        Assert.assertEquals("invalid", emptyState.getValue("this_is", "notok").toString());

        // close the bufferedreader
        br.close();

    }

    /**
     * Test the multipleItemAbstract methods
     * 
     * @throws IOException
     *             handles any input or output that's bad
     */
    @Test
    public void multipleAbstractTest() throws IOException
    {

        // infant object to use for testing
        Infant infant = new Infant("data", "c1");

        // trial object to use for testing
        Trial testTrial = new Trial(infant, "data", infant.getInfantID(), 1);

        // test the multipleItemAbstract getMaxState
        Assert.assertEquals("x = 0.424; y = 0.164; z = -0.063; ",
                testTrial.getMaxState("left_wrist", "x").getPoint("left_wrist").toString());

        // test the multipleItemAbstract getMinState
        Assert.assertEquals("x = 0.009; y = 0.267; z = -0.059; ",
                testTrial.getMinState("left_wrist", "x").getPoint("left_wrist").toString());

        // test the multipleItemAbstract getAverageValue on a Trial
        Assert.assertEquals("0.183", testTrial.getAverageValue("left_wrist", "x").toString());

        // test the multipleItemAbstract getAverageValue on an Infant
        Assert.assertEquals("0.186", infant.getAverageValue("left_wrist", "x").toString());

        // create Infant and trial object (different trial)
        Infant infant2 = new Infant("data", "c1");
        Trial infant2trial = new Trial(infant2, "data", infant2.getInfantID(), 4);

        // test multipleItemAbstract getMaxState on Trial Object
        Assert.assertEquals("-0.125",
                infant2.getItem(2).getMaxState("right_wrist", "y").getValue("right_wrist", "y").toString());

        // test getMinState on Infant object
        Assert.assertEquals("-0.359", infant2.getMinState("right_wrist", "y").getValue("right_wrist", "y").toString());

        // test getAverageValue on Infant object
        Assert.assertEquals("-0.233", infant2.getAverageValue("right_wrist", "y").toString());

        // ***added this test to test getMaxState for an Infant object
        Assert.assertEquals("x = 0.329; y = -0.034; z = -0.053; ",
                infant2.getMaxState("right_wrist", "y").getPoint("right_wrist").toString());

        // try to getMaxState that doesn't exist
        Assert.assertEquals("invalid",
                infant2trial.getMaxState("left_wrist", "o").getValue("left_wrist", "o").toString());

        // try to getMinState that doesn't exist
        Assert.assertEquals("invalid",
                infant2trial.getMinState("left_wrist", "u").getValue("left_wrist", "u").toString());

        // try to getAverageValue that doesn't exist
        Assert.assertEquals("invalid", infant2trial.getAverageValue("left_wrist", "f").toString());

    }

    /**
     * tests for new things implemented in Project 4
     * 
     * @throws IOException
     *             If there is an error finding or loading the data file.
     */
    @Test
    public void newTests() throws IOException
    {

        // test new Infant constructor
        int[] indices = { 1, 3 }; 
        Infant infant = new Infant("data", "c1"); 
        Infant infant2 = new Infant(infant, indices);
        State state = infant.getItem(0).getItem(35);

        Assert.assertEquals("c1", infant2.getInfantID());

        // test Iterable<Trial> in Infant class
        Assert.assertTrue(infant2.iterator().hasNext());

        // test toString from Trial class
        Assert.assertEquals("Week 01", infant.getItem(0).toString());
        Assert.assertEquals("Week 11", infant.getItem(4).toString()); 

        // test getFieldMapper for Trial
        Assert.assertTrue(infant.getItem(0).getFieldMapper().iterator().hasNext());

        // need to test null PointND
        Assert.assertNull(infant.getItem(0).getFieldMapper().extractPointND(null, "null")); 
        
        //test the new classes
        //InfantFrame test = new InfantFrame();
        KinematicPointState testAbs = new KinematicPointState(Color.PINK, 1, "right_wrist");

        Assert.assertEquals(0.228, testAbs.getScreenCoordinate(state, "x").getDoubleValue(), 0.001);

        KinematicPointConstant testConst = new KinematicPointConstant(Color.PINK, 1, 1.0, 2.0, 3.0);
        Assert.assertEquals(1.0, testConst.getScreenCoordinate(state, "x").getDoubleValue(), 0.1);
        
        
        Graphics2D g = null; 
        testAbs.draw(g, state, "x", "y");
    }
    
    

}
