package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getTipMainPage(Model model, @RequestParam String page, @RequestParam(required = false) String date,
            @RequestParam(required = false) boolean backwards)
    {
        if(page.equals("last"))
        {
            model.addAttribute("tips", yelpDatabaseService.getTipDataLastPage(50));
            
            long pageCount = yelpDatabaseService.getTipPageCount(50);
            
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "tip_page";
        }
        
        if(page.equals("1"))
        {
            model.addAttribute("tips", yelpDatabaseService.getTipDataPage(LocalDateTime.now(), 50));
            model.addAttribute("current_page", page);
            model.addAttribute("page_count", yelpDatabaseService.getTipPageCount(50));
            
            return "tip_page";
        }
        
        LocalDateTime dateDelimiter = LocalDateTime.parse(date);
        
        if(backwards)
        {
            model.addAttribute("tips", yelpDatabaseService.getTipDataPageReverse(dateDelimiter, 50));
        }
        else
        {
            model.addAttribute("tips", yelpDatabaseService.getTipDataPage(dateDelimiter, 50));
        }
        
        model.addAttribute("current_page", page);
        model.addAttribute("page_count", yelpDatabaseService.getTipPageCount(50));
        
        return "tip_page";
    }
}
