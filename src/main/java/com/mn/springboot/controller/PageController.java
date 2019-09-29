package com.mn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    //引导界面地址
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView neo = new ModelAndView("index");
        return neo;
    }

    //应用界面地址
    @RequestMapping("neo4j")
    public ModelAndView neo4j(){
        ModelAndView neo = new ModelAndView("neo4j");
        return neo;
    }

}
