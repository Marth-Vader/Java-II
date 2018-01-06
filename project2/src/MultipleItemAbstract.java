
/**
 * MultipleItemAbstract class contains methods that can be used for Infant/Trial objects
 * @author Lucas Black, Martha Nguyen
 * @version 10/11/2017
 *
 */
public abstract class MultipleItemAbstract extends SingleItemAbstract
{

    /**
     * abstract getSize method
     * not implemented in this class; implemented in Infant/Trial class
     * @return int, which is size of either Trial or Infant item, depending on what it was called for
     */
    public abstract int getSize();
    

    /**
     * abstract getItem method
     * not implemented in this class; implemented in Infant/Trial class
     * @param index of either Infant or Trial object
     * @return item (either Trial for Infant object or State for Trial object) for a certain index
     */
    public abstract SingleItemAbstract getItem(int index);
    
    
    /**
     * method that returns the max value of left wrist
     * can be called for Infant or Trial objects
     * @param dim 0 for x, 1 for y, 2 for z
     * @return the max left wrist value for called object for given dimension
     */
    public GeneralValue getMaxLeftWrist(int dim)
    {
        
        //create value to be used to compare/to store the max value for given dimension
        GeneralValue maxLeft = this.getItem(dim).getMaxLeftWrist(dim);
        
        //this for loop starts at the beginning of data for given dimension
        //and finds the first valid value to compare to other values
        for (int i = 0; i < this.getSize(); i++)
        {
            //if the value is valid
            if (maxLeft.isValid())
            {
                //reassign maxLeft to the first valid value found
                maxLeft = this.getItem(i).getMaxLeftWrist(dim);
                //exit
                continue;
            }
            
            //valid value found, go onto next for loop
        }

        //this for loop iterates through object
        for (int m = 0; m < this.getSize(); m++)
        {
            if (this.getItem(m).getMaxLeftWrist(dim).isValid()) {
            //if the GeneralValue is greater than the maxLeft variable
            if (this.getItem(m).getMaxLeftWrist(dim).isGreaterThan(maxLeft))
            {
                //reassign the maxLeft variable
                maxLeft = this.getItem(m).getMaxLeftWrist(dim);

            }
            
            }
            
            else {
                
            }
        }
        
        //returns the greatest value for given dimension
        return maxLeft;
        
            
    }
    
    
    /**
     * method that returns the min value of left wrist
     * can be called for Infant or Trial objects
     * @param dim 0 for x, 1 for y, 2 for z
     * @return the min left wrist value for called object for given dimension
     */
    public GeneralValue getMinLeftWrist(int dim)
    {

        GeneralValue nullX = new GeneralValue(Double.NaN);
        //create value to be used to compare/to store the min value for given dimension
        GeneralValue minLeft = nullX;
        
        //this for loop starts at the beginning of data for given dimension
        //and finds the first valid value to compare to other values
        for (int i = 0; i < this.getSize(); i++)
        {
            //if the value is valid
            if (this.getItem(i).getMinLeftWrist (dim).isValid())
            {
                //reassign maxLeft to the first valid value found
                minLeft = this.getItem(i).getMinLeftWrist(dim);
                //exit
                
                
                if (this.getItem(this.getSize()-1).getMinLeftWrist(dim).getDoubleValue () == Double.NaN ) {
                    //GeneralValue nullX = new GeneralValue(Double.NaN);
                    minLeft = nullX;
                }
                
                continue;
            }
            
            //valid value found, go onto next for loop
            

        }

        //this for loop iterates through object
        for (int m = 0; m < this.getSize(); m++)
        {
            if (this.getItem(m).getMinLeftWrist(dim).isValid()) {
            //if the GeneralValue is less than the minLeft variable
            if (this.getItem(m).getMinLeftWrist(dim).isLessThan(minLeft))
            {
                //reassign the minLeft variable
                minLeft = this.getItem(m).getMinLeftWrist(dim);

            }
            
        }
        }
        
        //returns the greatest value for given dimension
        return minLeft;
    }

    
    /**
     * method that returns the average value of left wrist
     * can be called for Infant or Trial objects
     * @param dim 0 for x, 1 for y, 2 for z
     * @return the average left wrist value for called object for given dimension
     */
    public GeneralValue getAverageLeftWrist(int dim)
    {
        //create numerator variable and initiate to 0
        double num = 0;
        //create denominator variable and initiate to 0
        double den = 0;

        //loops through object
        for (int j = 0; j < this.getSize(); j++)
        {
            //if the GeneralValue is valid
            if (this.getItem(j).getAverageLeftWrist(dim).isValid())
            {
                //convert that value to double, then add to numerator
                num += this.getItem(j).getAverageLeftWrist(dim).getDoubleValue();
                //increase the denominator by 1
                den++;
            }

        }
        
        //creates GeneralValue object, calculates average by dividing numerator by denominator
        GeneralValue leftAvg = new GeneralValue(num / den);
        
        //return the average of the left wrist
        return leftAvg;
        
    }

}
