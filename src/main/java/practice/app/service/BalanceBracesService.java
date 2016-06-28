package practice.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Service;

@Service
public class BalanceBracesService {
    
    /**
     * Maps opening braces to their coresponding closing brace
     */
    private static final Map<Character, Character> matchingBraces = new HashMap<>();

    
    private static final List<Character> openingBraces = new ArrayList<>();
    private static final List<Character> closingBraces = new ArrayList<>();
    static {
        matchingBraces.put('(', ')');
        matchingBraces.put('{', '}');
        matchingBraces.put('[', ']');
        
        openingBraces.addAll(matchingBraces.keySet());
        closingBraces.addAll(matchingBraces.values());
    }
    
    /**
     * Check if the braces in the given string are valid (balanced).
     * 
     * example:
     * "[()]{}{[()()]()}" valid
     * "[(])" invalid
     */
    public boolean isValid(String text) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<text.length(); i++) {
            char current = text.charAt(i);
            if(openingBraces.contains(current)) {
                // found opening brace so push it onto the stack
                stack.push(current);
            } else if(closingBraces.contains(current)) {
                // found closing brace
                if(stack.empty()) {
                    // if their's no opening brace on the stack to match the
                    // closing brace we just found then text contains invalid braces
                    return false;
                }
                if(matchingBraces.get(stack.pop()).equals(current) == false) {
                    // expected to find the coresponding opening brace for the
                    // closing brace we just found, but didn't, so text contains invalid braces
                    return false;
                }
            }
        }
        
        // if stack is empty then braces are valid (balanced)
        return stack.empty();
    }
}
