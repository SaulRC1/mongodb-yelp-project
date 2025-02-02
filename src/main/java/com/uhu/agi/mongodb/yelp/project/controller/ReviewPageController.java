package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
public class ReviewPageController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @GetMapping("/reviews")
    public String getReviewsPage(Model model, @RequestParam String page, @RequestParam(required = false) String date,
            @RequestParam(required = false) boolean backwards)
    {
        if(page.equals("last"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewDataLastPage(50));
            
            long pageCount = yelpDatabaseService.getReviewPageCount(50);
            
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            
            return "review_page";
        }
        
        if(page.equals("1"))
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewDataPage(LocalDateTime.now(), 50));
            model.addAttribute("current_page", page);
            model.addAttribute("page_count", yelpDatabaseService.getReviewPageCount(50));
            
            return "review_page";
        }
        
        LocalDateTime dateDelimiter = LocalDateTime.parse(date);
        
        if(backwards)
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewDataPageReverse(dateDelimiter, 50));
        }
        else
        {
            model.addAttribute("reviews", yelpDatabaseService.getReviewDataPage(dateDelimiter, 50));
        }
        
        model.addAttribute("current_page", page);
        model.addAttribute("page_count", yelpDatabaseService.getReviewPageCount(50));
        
        return "review_page";
    }
    
    @GetMapping("/review-search")
    public String getReviewsFromSearch(Model model, @RequestParam String searchText, @RequestParam String page, HttpSession httpSession)
    {
        Map<String, Long> searchReviewByTextPageCountMap = new HashMap<>();
        
        if(httpSession.getAttribute("searchReviewByTextPageCountMap") != null)
        {
            searchReviewByTextPageCountMap = (Map<String, Long>) httpSession.getAttribute("searchReviewByTextPageCountMap");
        }
        
        long pageCount = 0;

        if (searchReviewByTextPageCountMap.containsKey(searchText))
        {
            pageCount = searchReviewByTextPageCountMap.get(searchText);
        } 
        else
        {
            pageCount = yelpDatabaseService.getPageCountForSearchReviewByText(searchText, 50);
            searchReviewByTextPageCountMap.put(searchText, pageCount);
            httpSession.setAttribute("searchReviewByTextPageCountMap", searchReviewByTextPageCountMap);
        }
        
        if(page.equals("last"))
        {
            model.addAttribute("reviews", yelpDatabaseService.searchReviewByText(searchText, ((pageCount - 1) * 50), 50));
            model.addAttribute("current_page", pageCount);
            model.addAttribute("page_count", pageCount);
            model.addAttribute("usedSearchText", searchText);
            
            return "review_page_search";
        }
        
        if(page.equals("1"))
        {
            model.addAttribute("reviews", yelpDatabaseService.searchReviewByText(searchText, 0, 50));
            model.addAttribute("current_page", page);
            model.addAttribute("page_count", pageCount);
            model.addAttribute("usedSearchText", searchText);
            
            return "review_page_search";
        }
        
        model.addAttribute("reviews", yelpDatabaseService.searchReviewByText(searchText, ((Integer.parseInt(page) - 1) * 50), 50));
        model.addAttribute("current_page", page);
        model.addAttribute("page_count", pageCount);
        model.addAttribute("usedSearchText", searchText);
        
        return "review_page_search";
    }
    
    @GetMapping("/edit-review/{reviewId}")
    public String getEditReviewPage(Model model, @PathVariable("reviewId") String reviewId)
    {
        model.addAttribute("reviewData", yelpDatabaseService.getReviewDataByReviewId(reviewId));
        return "review_edit";
    }
}
