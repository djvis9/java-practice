package practice.app.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class SetServiceTest {

    @Test
    public void testPowerset() {
        
        SetService<String> setService = new SetService<>();
        Set<Set<String>> expected = new HashSet<>();
        expected.add(createSet(""));
        expected.add(createSet("x"));
        expected.add(createSet("y"));
        expected.add(createSet("z"));
        expected.add(createSet("xy"));
        expected.add(createSet("xz"));
        expected.add(createSet("yz"));
        expected.add(createSet("xyz"));
        
        Set<String> input = new HashSet<>();
        input.add("x");
        input.add("y");
        input.add("z");
        
        
        Set<Set<String>> actual = setService.powerset(input);
        
        Assert.assertEquals(expected.size(), actual.size());
        
        // assert that every set in the expected set is also in the powerset
        for(Set<String> target : expected ) {
            Assert.assertTrue(doesSetOfSetsContainSet(actual, target));
        }
    }
    
    public boolean doesSetOfSetsContainSet(Set<Set<String>> setOfSets, Set<String> target) {
        for(Set<String> set : setOfSets) {
            if (set.isEmpty() && target.isEmpty()) {
                return true;
            }
            if(set.containsAll(target)) {
                return true;
            }
        }
        
        return false;
    }
    
    public Set<String> createSet(String s) {
        Set<String> set = new HashSet<>();
        
        for(int i=0; i<s.length(); i++) {
            set.add("" + s.charAt(i));
        }
        
        return set;
    }
}
