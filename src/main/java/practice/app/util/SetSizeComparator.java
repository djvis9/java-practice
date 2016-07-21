package practice.app.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Comparator that sorts a set of sets by the size of the sets in ascending order.
 * 
 * If two sets have the same size then the two sets are copied, sorted, and 
 * each element is compared to see if they match. This is necessary in order
 * for this comparator to function properly when used with a TreeSet. See
 * https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html for more
 * information, particularly the note about "consistent with equals."
 */
public class SetSizeComparator<T extends Comparable<T>> implements Comparator<Set<T>> {
    
    @Override
    public int compare(Set<T> a, Set<T> b) {
        int sizeDifference = a.size() - b.size();
        
        if(sizeDifference == 0) {
            // the two sets have the same size so sort the two sets and compare
            // each elemnt to see if their is a difference
            SortedSet<T> aSorted = new TreeSet<>(a);
            SortedSet<T> bSorted = new TreeSet<>(b);
            
            Iterator<T> iteratorA = aSorted.iterator();
            Iterator<T> iteratorB = bSorted.iterator();
            
            while(iteratorA.hasNext() && iteratorB.hasNext()) {
                T elementA = iteratorA.next();
                T elementB = iteratorB.next();
                
                // if either element is null and the other element is not null then we found a difference
                if(elementA == null && elementB != null) {
                    return -1;
                } else if(elementB == null && elementA != null) {
                    return 1;
                }
                
                int diff = elementA.compareTo(elementB);
                if(diff != 0) {
                    // found difference
                    return diff;
                }
                
            }
                
            // if we get here then the two sets are equal
            return 0;
        }
            
        return sizeDifference;
    }
}