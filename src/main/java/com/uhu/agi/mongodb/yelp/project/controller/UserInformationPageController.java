package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.collection.User;
import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.time.LocalDateTime;
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
public class UserInformationPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping("/user-information/reviews/{userId}")
    public String getUserInformationPage(Model model, @PathVariable("userId") String userId,
            @RequestParam String page, @RequestParam(required = false) String date,
            @RequestParam(required = false) boolean backwards)
    {
        User user = yelpDatabaseService.getUserByUserId(userId);
        
        model.addAttribute("user", user);
        
        if(page.equals("last"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewLastPageForUser(user.getUserId(), 50));
            
            long pageCount = yelpDatabaseService.getReviewPageCountForUser(user.getUserId(), 50);
            
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "user_information_page";
        }
        
        if(page.equals("1"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewPageForUser(user.getUserId(), LocalDateTime.now(), 50));
            model.addAttribute("current_page", page);
            model.addAttribute("page_count", yelpDatabaseService.getReviewPageCountForUser(user.getUserId(), 50));
            
            return "user_information_page";
        }
        
        LocalDateTime dateDelimiter = LocalDateTime.parse(date);
        
        if(backwards)
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewPageForUserReverse(user.getUserId(), dateDelimiter, 50));
        }
        else
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewPageForUser(user.getUserId(), dateDelimiter, 50));
        }
        
        model.addAttribute("current_page", page);
        model.addAttribute("page_count", yelpDatabaseService.getReviewPageCountForUser(user.getUserId(), 50));
        
        return "user_information_page";
    }
}
