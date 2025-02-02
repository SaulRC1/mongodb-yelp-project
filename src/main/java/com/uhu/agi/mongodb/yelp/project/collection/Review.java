package com.uhu.agi.mongodb.yelp.project.collection;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Document
public class Review 
{
    @Id
    private final String id;
    
    @Field("review_id")
    private final String reviewId;
    
    @Field("user_id")
    private String userId;
    
    @Field("business_id")
    private String businessId;
    
    @Field
    private int stars;
    
    @Field
    private int useful;
    
    @Field
    private int funny;
    
    @Field
    private int cool;
    
    @Field
    private String text;
    
    @Field
    private LocalDateTime date;

    public Review(String id, String reviewId, String userId, String businessId, 
            int stars, int useful, int funny, int cool, String text, LocalDateTime date)
    {
        this.id = id;
        this.reviewId = reviewId;
        this.userId = userId;
        this.businessId = businessId;
        this.stars = stars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.text = text;
        this.date = date;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }

    public int getStars()
    {
        return stars;
    }

    public void setStars(int stars)
    {
        this.stars = stars;
    }

    public int getUseful()
    {
        return useful;
    }

    public void setUseful(int useful)
    {
        this.useful = useful;
    }

    public int getFunny()
    {
        return funny;
    }

    public void setFunny(int funny)
    {
        this.funny = funny;
    }

    public int getCool()
    {
        return cool;
    }

    public void setCool(int cool)
    {
        this.cool = cool;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    public String getId()
    {
        return id;
    }

    public String getReviewId()
    {
        return reviewId;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Review{");
        sb.append("id=").append(id);
        sb.append(", reviewId=").append(reviewId);
        sb.append(", userId=").append(userId);
        sb.append(", businessId=").append(businessId);
        sb.append(", stars=").append(stars);
        sb.append(", useful=").append(useful);
        sb.append(", funny=").append(funny);
        sb.append(", cool=").append(cool);
        sb.append(", text=").append(text);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
