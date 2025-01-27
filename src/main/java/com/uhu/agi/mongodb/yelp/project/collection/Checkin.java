package com.uhu.agi.mongodb.yelp.project.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Document
public class Checkin 
{
    @Id
    private final String id;
    
    @Field("business_id")
    private final String businessId;
    
    @Field("date")
    private final String timestamps;

    public Checkin(String id, String businessId, String timestamps)
    {
        this.id = id;
        this.businessId = businessId;
        this.timestamps = timestamps;
    }
}
