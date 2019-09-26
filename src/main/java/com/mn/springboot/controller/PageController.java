package com.mn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    @RequestMapping("neo4j")
    public ModelAndView neo4j(){
        ModelAndView neo = new ModelAndView("neo4j");
        return neo;
    }
}

