package com.uhu.agi.mongodb.yelp.project.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.uhu.agi.mongodb.yelp.project.collection.Business;
import com.uhu.agi.mongodb.yelp.project.collection.Tip;
import com.uhu.agi.mongodb.yelp.project.data.TipListData;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class YelpDatabaseService 
{
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Business> getAllBusinesses()
    {
        BasicQuery query = (BasicQuery) new BasicQuery("{}").limit(30);
        List<Business> result = mongoTemplate.find(query, Business.class);
        
        for (Business business : result)
        {
            System.out.println("Business: " + business.toString());
        }
        return result;
    }
    
    public List<TipListData> getTipDataPage(LocalDateTime dateDelimiter, int documentLimit)
    {
        List<TipListData> tipList = new ArrayList<>();
        
        Aggregation returnTipListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.DESC, "date"),
                Aggregation.match(Criteria.where("date").lt(Date.from(dateDelimiter.atZone(ZoneId.systemDefault()).toInstant()))),
                Aggregation.limit(documentLimit),
                Aggregation.lookup("user", "user_id", "user_id", "tip_user"),
                Aggregation.unwind("tip_user"),
                Aggregation.lookup("business", "business_id", "business_id", "tip_business"),
                Aggregation.unwind("tip_business"),
                Aggregation.project("text", "date", "compliment_count", "business_id", "user_id")
                .and("tip_user.name").as("tip_author")
                .and("tip_business.name").as("tip_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnTipListAggregation, "tip", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Tip tip = new Tip(
                    document.getObjectId("_id").toString(),
                    document.getString("user_id"),
                    document.getString("business_id"),
                    document.getString("text"),
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    document.getInteger("compliment_count")
            );

            String tipAuthor = document.getString("tip_author");
            String tipBusiness = document.getString("tip_business");

            TipListData tipListData = new TipListData(tip, tipAuthor, tipBusiness);
            tipList.add(tipListData);
        }
        
        return tipList;
    }
}
