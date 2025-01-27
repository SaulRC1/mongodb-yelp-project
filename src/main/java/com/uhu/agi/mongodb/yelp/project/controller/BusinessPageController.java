package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
@RequestMapping("/businesses")
public class BusinessPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping
    public String getBusinessPage(Model model)
    {
        model.addAttribute("businesses", yelpDatabaseService.getAllBusinesses());
        return "business_page";
    }
}
