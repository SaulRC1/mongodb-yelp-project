package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.Review;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class ReviewData 
{
    private final Review review;
    private final String businessName;

    public ReviewData(Review review, String businessName)
    {
        this.review = review;
        this.businessName = businessName;
    }

    public Review getReview()
    {
        return review;
    }

    public String getBusinessName()
    {
        return businessName;
    }
}
