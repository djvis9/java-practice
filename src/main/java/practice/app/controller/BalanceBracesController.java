package practice.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import practice.app.service.BalanceBracesService;

@Controller
public class BalanceBracesController {
    
    @Autowired
    private BalanceBracesService balanceBracesService;
    
    /**
     * RESTful endpoint to check if the specified text contains balanced
     * braces. Braces can be '(', '[', or '{'.
     * 
     * @param text check this text for balanced braces
     * @return true if text contains balanced braces; false otherwise
     */
    @RequestMapping(value = "/braces", method = RequestMethod.GET)
    public ResponseEntity<Boolean> post(@RequestParam("text") String text) {
        
        return new ResponseEntity<Boolean>(balanceBracesService.isValid(text), HttpStatus.OK);
    }   
}
