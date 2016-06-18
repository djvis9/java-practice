package practice.app.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * These tests prove the BalanceParenthesesService correctly validates if a 
 * string of parentheses are balanced. 
 */
public class BalanceParenthesesServiceTest {
    
    BalanceParenthesesService balanceParenthesesService = new BalanceParenthesesService();
  
    @Test
    public void isValid_shouldReturnTrueForSingleParentheses() {
        Assert.assertTrue(balanceParenthesesService.isValid("()"));
    }
  
    @Test
    public void isValid_shouldReturnTrueForDoubleParentheses() {
        Assert.assertTrue(balanceParenthesesService.isValid("()()"));
    }
  
    @Test
    public void isValid_shouldReturnTrueForDoubleParenthesesInsideSingleParentheses() {
        Assert.assertTrue(balanceParenthesesService.isValid("(()())"));
    }
  
    @Test
    public void isValid_shouldReturnFalseForSingleOpenParentheses() {
        Assert.assertFalse(balanceParenthesesService.isValid("("));
    }
  
    @Test
    public void isValid_shouldReturnFalseForSingleCloseParentheses() {
        Assert.assertFalse(balanceParenthesesService.isValid(")"));
    }
  
    @Test
    public void isValid_shouldReturnFalseForOutOfOrderParentheses() {
        Assert.assertFalse(balanceParenthesesService.isValid(")("));
    }
  
    @Test
    public void isValid_shouldReturnFalseForTooManyOpenParentheses() {
        Assert.assertFalse(balanceParenthesesService.isValid("(()"));
    }
  
    @Test
    public void isValid_shouldReturnFalseForTooManyCloseParentheses() {
        Assert.assertFalse(balanceParenthesesService.isValid("())"));
    }}