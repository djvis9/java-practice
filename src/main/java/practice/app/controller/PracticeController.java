package practice.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller returns the HTML page that contains this project's one 
 * page app. The returned page makes RESTful calls to other controllers in 
 * this project.
 */
@Controller
public class PracticeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPractice() {
        return "practice";
    }
}