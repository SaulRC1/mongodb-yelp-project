package com.uhu.agi.mongodb.yelp.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
public class WelcomePageController 
{
    @GetMapping("/")
    public String getWelcomePage()
    {
        return "welcome_page";
    }
}
