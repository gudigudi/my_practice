package com.gudigudigudi.sb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gu on 4/4/17.
 */
@Controller
public class HelloController{


    @RequestMapping(value = "/welcome")
    public ModelAndView HelloController() {
        ModelAndView modelAndView=new ModelAndView("HelloPage");
        modelAndView.addObject("welcomeMessage",
                "Hi, welcome to the first Spring MVC Application");
        return modelAndView;
    }
}
