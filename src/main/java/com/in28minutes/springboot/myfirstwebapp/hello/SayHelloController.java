package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String sayHelloNorm() {
        return "hello lelelle";
    }
    @GetMapping("/hello-jsp")
    public String sayHelloJsp(ModelMap model) {

        return "sayHello";
    }
}