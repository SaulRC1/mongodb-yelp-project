package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.collection.Business;
import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.util.List;
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
@RequestMapping("/businesses")
public class BusinessPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping
    public String getBusinessPage(Model model, @RequestParam String page)
    {   
        if(page.equals("last"))
        {
            long pageCount = yelpDatabaseService.getBusinessPageCount(50);
            List<Business> businessList = yelpDatabaseService.getBusinessDataPage(((pageCount - 1) * 50), 50);
            

            model.addAttribute("businesses", businessList);
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "business_page";
        }
        
        int pageNumber = Integer.parseInt(page);
        List<Business> businessList = yelpDatabaseService.getBusinessDataPage(((pageNumber - 1) * 50), 50);
        long pageCount = yelpDatabaseService.getBusinessPageCount(50);
        
        model.addAttribute("businesses", businessList);
        model.addAttribute("current_page", pageNumber);
        model.addAttribute("page_count", pageCount);
        
        return "business_page";
    }
}
