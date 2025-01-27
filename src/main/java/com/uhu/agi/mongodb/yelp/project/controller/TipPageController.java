package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.time.LocalDateTime;
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
@RequestMapping("/tips")
public class TipPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping
    public String getTipMainPage(Model model)
    {
        model.addAttribute("tips", yelpDatabaseService.getTipDataPage(LocalDateTime.now(), 50));
        return "tip_page";
    }
}
