package practice.app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class SetService<T> {
    
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
    public Set<Set<T>> powerset(Set<T> set) {
        int size = set.size();
        int twoToPowerOfSetSize = (int)Math.pow(2, size);
        Object[] elements = set.toArray();
        Set<Set<T>> output = new HashSet<>(twoToPowerOfSetSize);
        
        for (int i=0; i<twoToPowerOfSetSize; i++) {
            Set<T> current = new HashSet<>();
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
