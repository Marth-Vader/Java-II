/**
 * InvalidValueException extends RuntimeException and is thrown when the GeneralValue is invalid
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/2017
 *
 */
public class InvalidValueException extends RuntimeException
{

    /**
     * Constructor for the InvalidValueException takes in message
     * @param message to be displayed with the error
     */
    public InvalidValueException(String message)
    {
        //calls super constructor
        super(message);
    }
    

}
