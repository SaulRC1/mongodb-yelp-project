package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.Review;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class ReviewListData 
{
    private Review review;
    private String reviewAuthor;
    private String reviewBusiness;

    public ReviewListData(Review review, String reviewAuthor, String reviewBusiness)
    {
        this.review = review;
        this.reviewAuthor = reviewAuthor;
        this.reviewBusiness = reviewBusiness;
    }

    public Review getReview()
    {
        return review;
    }

    public void setReview(Review review)
    {
        this.review = review;
    }

    public String getReviewAuthor()
    {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor)
    {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewBusiness()
    {
        return reviewBusiness;
    }

    public void setReviewBusiness(String reviewBusiness)
    {
        this.reviewBusiness = reviewBusiness;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ReviewListData{");
        sb.append("review=").append(review);
        sb.append(", reviewAuthor=").append(reviewAuthor);
        sb.append(", reviewBusiness=").append(reviewBusiness);
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
