package practice.app.service;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import practice.app.util.SetSizeComparator;

@Service
public class SetService<T extends Comparable<T>> {
    
    private SetSizeComparator<T> setSizeComparator = new SetSizeComparator<>();
    
    /**
     * Return the powerset of a set.
     * 
     * The powerset is a set of sets containing every combination of the 
     * entries in the given set.
     * 
     * Example:
     * 
     * powerset({x, y, z}):
     * {}
     * {x}
     * {y}
     * {z}
     * {x, y}
     * {x, z}
     * {y, z}
     * {x, y, z}
     * 
     * @param set the set for which we will return a powerset
     * @return powerset of the given set
     */
    public SortedSet<SortedSet<T>> powerset(Set<T> set) {
        int size = set.size();
        int twoToPowerOfSetSize = (int)Math.pow(2, size);
        Object[] elements = set.toArray();

        // TreeSet will keep the set of sets in ascending order by size
        SortedSet<SortedSet<T>> output = new TreeSet<>(setSizeComparator);
        
        for (int i=0; i<twoToPowerOfSetSize; i++) {
            SortedSet<T> current = new TreeSet<>();
            for(int j=0; j<size; j++) {
                if( ((i >> j) & 1) == 1) {
                    current.add((T)elements[j]);
                }
            }
            output.add(current);
        }
        
        return output;
    }

}
