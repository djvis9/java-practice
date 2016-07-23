package practice.app.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import practice.app.service.SetService;

@Controller
public class PowersetController {

    @Autowired
    private SetService<String> setService;
    
    private final static String ALPHA_CHARS = "abcdefghijklmnopqrstuvwxyz";

    @RequestMapping(value = "/powerset", method = RequestMethod.GET)
    public ResponseEntity<SortedSet<SortedSet<String>>> post(@RequestParam("text") String text) {

        Set<String> input = new HashSet<>();
        
        for(int i=0; i<text.length(); i++) {
            if(ALPHA_CHARS.contains("" + text.charAt(i))) {
                input.add("" + text.charAt(i));
            }
        }
        
        return new ResponseEntity<SortedSet<SortedSet<String>>>(setService.powerset(input), HttpStatus.OK);
    }
}
