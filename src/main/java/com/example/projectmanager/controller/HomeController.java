package com.example.projectmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("")
    public String homePage(ModelMap model){
        String title = "Home";
        model.addAttribute("title", title);
        return "home";
    }
}
