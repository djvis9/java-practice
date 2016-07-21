package practice.app.service;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import practice.app.util.SetSizeComparator;

public class SetServiceTest {

    @Test
    public void testPowerset() {
        
        SetService<String> setService = new SetService<>();
        SortedSet<SortedSet<String>> expected = new TreeSet<>(new SetSizeComparator<String>());
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
        
        SortedSet<SortedSet<String>> actual = setService.powerset(input);
        
        Assert.assertTrue(expected.equals(actual));
    }
    
    public SortedSet<String> createSet(String s) {
        SortedSet<String> set = new TreeSet<>();
        
        for(int i=0; i<s.length(); i++) {
            set.add("" + s.charAt(i));
        }
        
        return set;
    }
}
