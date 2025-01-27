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
public class Tip
{
    @Id
    private final String id;
    
    @Field("user_id")
    private final String userId;
    
    @Field("business_id")
    private final String businessId;
    
    @Field
    private final String text;
    
    @Field
    private final LocalDateTime date;
    
    @Field("compliment_count")
    private final int complimentCount;

    public Tip(String id, String userId, String businessId, String text, LocalDateTime date, int complimentCount)
    {
        this.id = id;
        this.userId = userId;
        this.businessId = businessId;
        this.text = text;
        this.date = date;
        this.complimentCount = complimentCount;
    }

    public String getId()
    {
        return id;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public String getText()
    {
        return text;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public int getComplimentCount()
    {
        return complimentCount;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Tip{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", businessId=").append(businessId);
        sb.append(", text=").append(text);
        sb.append(", date=").append(date);
        sb.append(", complimentCount=").append(complimentCount);
        sb.append('}');
        return sb.toString();
    }
}
