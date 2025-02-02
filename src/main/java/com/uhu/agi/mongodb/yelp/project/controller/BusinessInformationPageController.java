package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.collection.Business;
import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
public class BusinessInformationPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping("/business-information/{businessId}")
    public String getBusinessInformationPage(Model model, @PathVariable("businessId") String businessId, 
            @RequestParam String page, @RequestParam(required = false) String date,
            @RequestParam(required = false) boolean backwards)
    {
        Business business = yelpDatabaseService.getBusinessByBusinessId(businessId);
        
        model.addAttribute("business", business);
        
        if(page.equals("last"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getBusinessReviewDataPageLast(businessId, 50));
            
            long pageCount = yelpDatabaseService.getBusinessReviewPageCount(business.getBusinessId(), 50);
            
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "business_information_page";
        }
        
        if(page.equals("1"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getBusinessReviewDataPage(business.getBusinessId(), LocalDateTime.now(), 50));
            model.addAttribute("current_page", page);
            model.addAttribute("page_count", yelpDatabaseService.getBusinessReviewPageCount(business.getBusinessId(), 50));
            
            return "business_information_page";
        }
        
        LocalDateTime dateDelimiter = LocalDateTime.parse(date);
        
        if(backwards)
        {
            model.addAttribute("reviews", yelpDatabaseService.getBusinessReviewDataPageReverse(businessId, dateDelimiter, 50));
        }
        else
        {
            model.addAttribute("reviews", yelpDatabaseService.getBusinessReviewDataPage(business.getBusinessId(), dateDelimiter, 50));
        }
        
        model.addAttribute("current_page", page);
        model.addAttribute("page_count", yelpDatabaseService.getBusinessReviewPageCount(business.getBusinessId(), 50));
        
        return "business_information_page";
    }
}
