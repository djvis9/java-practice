package practice.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import practice.app.service.BalanceParenthesesService;

@Controller
public class BalanceParenthesesController {
  
    @Autowired
    private BalanceParenthesesService balanceParenthesesService;
  
    /**
     *  RESTful endpoint to check if the specified text contains balanced parentheses.
     * 
     *  @param text check this text for balanced parentheses
     *  @return true if text contains balanced parentheses; false otherwise
     */
    @RequestMapping(value = "/parentheses", method = RequestMethod.POST)
    public ResponseEntity<Boolean> post(@RequestParam("text") String text) {
        
        return new ResponseEntity<Boolean>(balanceParenthesesService.isValid(text), HttpStatus.OK);
    }
}