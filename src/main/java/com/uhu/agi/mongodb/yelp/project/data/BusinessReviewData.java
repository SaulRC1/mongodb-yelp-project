package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.Review;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class BusinessReviewData 
{
    private final Review review;
    private final String reviewAuthor;

    public BusinessReviewData(Review review, String reviewAuthor)
    {
        this.review = review;
        this.reviewAuthor = reviewAuthor;
    }

    public Review getReview()
    {
        return review;
    }

    public String getReviewAuthor()
    {
        return reviewAuthor;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("BusinessReviewData{");
        sb.append("review=").append(review);
        sb.append(", reviewAuthor=").append(reviewAuthor);
        sb.append('}');
        return sb.toString();
    }
    
    public String getFormattedDate()
    {
        LocalDateTime date = this.review.getDate();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return date.format(formatter);
    }
}
