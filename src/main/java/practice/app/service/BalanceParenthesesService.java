package practice.app.service;

import org.springframework.stereotype.Service;

@Service
public class BalanceParenthesesService {
    
    /**
     * Check if the parentheses in the given string are valid.
     * 
     * examples:
     * "()" valid
     * "(" invalid
     * "())" invalid
     * "(()())" valid
     * ")(" invalid
     */
    public boolean isValid(String text) {
        int counter = 0;
        
        for(int i=0; i<text.length(); i++) {
            if(text.charAt(i) == '(') {
                counter++;
            } else if(text.charAt(i) == ')') {
                counter--;
            }
          
            if(counter < 0) {
                return false;
            }
        }
      
        return counter == 0;
    }
}