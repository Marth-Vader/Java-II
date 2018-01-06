import java.util.Iterator;

public interface Iterable<String> {
    
    /**
     * 
     * @return an iterator
     */
    public abstract Iterator<String> iterator();
        
}
