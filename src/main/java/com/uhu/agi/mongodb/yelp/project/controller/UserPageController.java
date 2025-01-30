package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.collection.User;
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
@RequestMapping("/users")
public class UserPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping
    public String getUserPage(Model model, @RequestParam String page)
    {
        if(page.equals("last"))
        {
            long pageCount = yelpDatabaseService.getUserPageCount(50);
            List<User> userList = yelpDatabaseService.getUserDataPage(((pageCount - 1) * 50), 50);
            

            model.addAttribute("users", userList);
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "user_page";
        }
        
        int pageNumber = Integer.parseInt(page);
        List<User> userList = yelpDatabaseService.getUserDataPage(((pageNumber - 1) * 50), 50);
        long pageCount = yelpDatabaseService.getUserPageCount(50);
        
        model.addAttribute("users", userList);
        model.addAttribute("current_page", pageNumber);
        model.addAttribute("page_count", pageCount);
        
        return "user_page";
    }
}
