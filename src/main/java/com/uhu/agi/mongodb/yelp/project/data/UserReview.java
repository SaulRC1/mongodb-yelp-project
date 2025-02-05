package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.Review;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class UserReview 
{
    private final Review review;
    private final String reviewBusiness;

    public UserReview(Review review, String reviewBusiness)
    {
        this.review = review;
        this.reviewBusiness = reviewBusiness;
    }

    public Review getReview()
    {
        return review;
    }

    public String getReviewBusiness()
    {
        return reviewBusiness;
    }
    
    public String getFormattedDate()
    {
        LocalDateTime date = this.review.getDate();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return date.format(formatter);
    }
}
