import java.util.Iterator;

/**
 * Interface containing Iterable<String> 
 * @author Lucas Black, Martha Nguyen
 * @version 11/13/17
 * @param <E> each iterator has string or Trial objects
 */
public interface Iterable<E> {
    
    /**
     * iterator abstract class
     * implemented in PointND, State, Field, FieldMapper, and Infant
     * @return an iterator with string or Trial keys
     */
    public abstract Iterator<E> iterator();

        
}
