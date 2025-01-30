package com.uhu.agi.mongodb.yelp.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
public class UserInformationPageController 
{
    @GetMapping("/user-information/{userId}")
    public String getUserInformationPage(Model model, @PathVariable("userId") String userId)
    {
        return "user_information_page";
    }
}
