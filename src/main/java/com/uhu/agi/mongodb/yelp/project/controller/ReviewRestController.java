package com.uhu.agi.mongodb.yelp.project.controller;

import com.uhu.agi.mongodb.yelp.project.service.YelpDatabaseService;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@RestController
public class ReviewRestController 
{
    @Autowired
    private YelpDatabaseService yelpDatabaseService;
    
    @PostMapping("/edit-review/{reviewId}")
    public ResponseEntity<Map<String, String>> editReviewWithNewText(@PathVariable("reviewId") String reviewId, 
            @RequestBody Map<String, Object> newTextJSON)
    {
        Map<String, String> response = new HashMap<>();
        
        
        try
        {
            yelpDatabaseService.updateReviewText(reviewId, (String) newTextJSON.get("newText"));
        } 
        catch (Exception e)
        {
            Logger.getLogger(ReviewRestController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        
        response.put("message", "Guardado correctamente");
        return ResponseEntity.ok(response);
    }
}
