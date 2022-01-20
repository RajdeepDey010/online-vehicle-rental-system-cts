package com.cts.controller;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
=======
import org.springframework.web.bind.annotation.GetMapping;

>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
@Controller
public class UIController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
