package practice.app.service;

import org.junit.Assert;
import org.junit.Test;

public class BalanceBracesServiceTest {
    
    BalanceBracesService balanceBracesService = new BalanceBracesService();
    
    @Test
    public void shouldReturnTrue() {
        Assert.assertTrue(balanceBracesService.isValid("[()]{}{[()()]()}"));
    }
    
    @Test
    public void shouldReturnFalse() {
        Assert.assertFalse(balanceBracesService.isValid("[(])"));
    }
}
