package com.uhu.agi.mongodb.yelp.project.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.uhu.agi.mongodb.yelp.project.collection.Business;
import com.uhu.agi.mongodb.yelp.project.collection.Review;
import com.uhu.agi.mongodb.yelp.project.collection.Tip;
import com.uhu.agi.mongodb.yelp.project.collection.User;
import com.uhu.agi.mongodb.yelp.project.data.ReviewListData;
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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
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
    
    public List<Business> getBusinessDataPage(int documentLimit)
    {
        return null;
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
    
    public List<TipListData> getTipDataPageReverse(LocalDateTime dateDelimiter, int documentLimit)
    {
        List<TipListData> tipList = new ArrayList<>();
        
        Aggregation returnTipListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.ASC, "date"),
                Aggregation.match(Criteria.where("date").gt(Date.from(dateDelimiter.atZone(ZoneId.systemDefault()).toInstant()))),
                Aggregation.limit(documentLimit),
                Aggregation.sort(Sort.Direction.DESC, "date"),
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
    
    public List<TipListData> getTipDataLastPage(int documentLimit)
    {
        List<TipListData> tipList = new ArrayList<>();
        
        Aggregation returnTipListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.ASC, "date"),
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
    
    public long getTipPageCount(int documentLimitPerPage)
    {
        MongoCollection<?> tipCollection = mongoTemplate.getCollection("tip");

        long totalTipCount = tipCollection.estimatedDocumentCount();
        
        long pageCount = (long) Math.ceil(((double) totalTipCount / (double) documentLimitPerPage));
        
        return pageCount;
    }
    
    public List<User> getUserDataPage(long documentSkip, int documentLimit)
    {
        Aggregation returnTipListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.ASC, "name"),
                Aggregation.skip(documentSkip),
                Aggregation.limit(documentLimit));
        
        AggregationResults<User> results = mongoTemplate.aggregate(returnTipListAggregation, "user", User.class);
        
        return results.getMappedResults();
    }
    
    public long getUserPageCount(int documentLimitPerPage)
    {
        MongoCollection<?> userCollection = mongoTemplate.getCollection("user");

        long totalUserCount = userCollection.estimatedDocumentCount();
        
        long pageCount = (long) Math.ceil(((double) totalUserCount / (double) documentLimitPerPage));
        
        return pageCount;
    }
    
    public List<ReviewListData> getReviewDataPage(LocalDateTime dateDelimiter, int documentLimit)
    {
        List<ReviewListData> reviewList = new ArrayList<>();
        
        Aggregation returnReviewListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.DESC, "date"),
                Aggregation.match(Criteria.where("date").lt(Date.from(dateDelimiter.atZone(ZoneId.systemDefault()).toInstant()))),
                Aggregation.limit(documentLimit),
                Aggregation.lookup("user", "user_id", "user_id", "review_user"),
                Aggregation.unwind("review_user"),
                Aggregation.lookup("business", "business_id", "business_id", "review_business"),
                Aggregation.unwind("review_business"),
                Aggregation.project("review_id", "user_id", "business_id", "stars", "useful", "funny", 
                        "cool", "text", "date")
                .and("review_user.name").as("review_author")
                .and("review_business.name").as("review_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnReviewListAggregation, "review", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Review review = new Review(
                    document.getObjectId("_id").toString(), 
                    document.getString("review_id"), document.getString("user_id"), 
                    document.getString("business_id"), document.getInteger("stars"), 
                    document.getInteger("useful"), document.getInteger("funny"), 
                    document.getInteger("cool"), document.getString("text"), 
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            
            String reviewAuthor = document.getString("review_author");
            String reviewBusiness = document.getString("review_business");

            ReviewListData reviewListData = new ReviewListData(review, reviewAuthor, reviewBusiness);
            
            reviewList.add(reviewListData);
        }
        
        return reviewList;
    }
    
    public List<ReviewListData> getReviewDataPageReverse(LocalDateTime dateDelimiter, int documentLimit)
    {
        List<ReviewListData> reviewList = new ArrayList<>();
        
        Aggregation returnReviewListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.ASC, "date"),
                Aggregation.match(Criteria.where("date").gt(Date.from(dateDelimiter.atZone(ZoneId.systemDefault()).toInstant()))),
                Aggregation.limit(documentLimit),
                Aggregation.sort(Sort.Direction.DESC, "date"),
                Aggregation.lookup("user", "user_id", "user_id", "review_user"),
                Aggregation.unwind("review_user"),
                Aggregation.lookup("business", "business_id", "business_id", "review_business"),
                Aggregation.unwind("review_business"),
                Aggregation.project("review_id", "user_id", "business_id", "stars", "useful", "funny", 
                        "cool", "text", "date")
                .and("review_user.name").as("review_author")
                .and("review_business.name").as("review_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnReviewListAggregation, "review", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Review review = new Review(
                    document.getObjectId("_id").toString(), 
                    document.getString("review_id"), document.getString("user_id"), 
                    document.getString("business_id"), document.getInteger("stars"), 
                    document.getInteger("useful"), document.getInteger("funny"), 
                    document.getInteger("cool"), document.getString("text"), 
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            
            String reviewAuthor = document.getString("review_author");
            String reviewBusiness = document.getString("review_business");

            ReviewListData reviewListData = new ReviewListData(review, reviewAuthor, reviewBusiness);
            
            reviewList.add(reviewListData);
        }
        
        return reviewList;
    }
    
    public List<ReviewListData> getReviewDataLastPage(int documentLimit)
    {
        List<ReviewListData> reviewList = new ArrayList<>();
        
        Aggregation returnReviewListAggregation = Aggregation.newAggregation( 
                Aggregation.match(new Criteria()), 
                Aggregation.sort(Sort.Direction.ASC, "date"),
                Aggregation.limit(documentLimit),
                Aggregation.lookup("user", "user_id", "user_id", "review_user"),
                Aggregation.unwind("review_user"),
                Aggregation.lookup("business", "business_id", "business_id", "review_business"),
                Aggregation.unwind("review_business"),
                Aggregation.project("review_id", "user_id", "business_id", "stars", "useful", "funny", 
                        "cool", "text", "date")
                .and("review_user.name").as("review_author")
                .and("review_business.name").as("review_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnReviewListAggregation, "review", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Review review = new Review(
                    document.getObjectId("_id").toString(), 
                    document.getString("review_id"), document.getString("user_id"), 
                    document.getString("business_id"), document.getInteger("stars"), 
                    document.getInteger("useful"), document.getInteger("funny"), 
                    document.getInteger("cool"), document.getString("text"), 
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            
            String reviewAuthor = document.getString("review_author");
            String reviewBusiness = document.getString("review_business");

            ReviewListData reviewListData = new ReviewListData(review, reviewAuthor, reviewBusiness);
            
            reviewList.add(reviewListData);
        }
        
        return reviewList;
    }
    
    public long getReviewPageCount(int documentLimitPerPage)
    {
        MongoCollection<?> reviewCollection = mongoTemplate.getCollection("review");

        long totalReviewCount = reviewCollection.estimatedDocumentCount();
        
        long pageCount = (long) Math.ceil(((double) totalReviewCount / (double) documentLimitPerPage));
        
        return pageCount;
    }
    
    public List<ReviewListData> searchReviewByText(String searchText, long documentSkip, int documentLimit)
    {
        List<ReviewListData> reviewList = new ArrayList<>();
        
        Aggregation returnReviewListAggregation = Aggregation.newAggregation( 
                Aggregation.match(TextCriteria.forLanguage("english").matchingPhrase(searchText)),
                Aggregation.skip(documentSkip),
                Aggregation.limit(documentLimit),
                Aggregation.lookup("user", "user_id", "user_id", "review_user"),
                Aggregation.unwind("review_user"),
                Aggregation.lookup("business", "business_id", "business_id", "review_business"),
                Aggregation.unwind("review_business"),
                Aggregation.project("review_id", "user_id", "business_id", "stars", "useful", "funny", 
                        "cool", "text", "date")
                .and("review_user.name").as("review_author")
                .and("review_business.name").as("review_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnReviewListAggregation, "review", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Review review = new Review(
                    document.getObjectId("_id").toString(), 
                    document.getString("review_id"), document.getString("user_id"), 
                    document.getString("business_id"), document.getInteger("stars"), 
                    document.getInteger("useful"), document.getInteger("funny"), 
                    document.getInteger("cool"), document.getString("text"), 
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            
            String reviewAuthor = document.getString("review_author");
            String reviewBusiness = document.getString("review_business");

            ReviewListData reviewListData = new ReviewListData(review, reviewAuthor, reviewBusiness);
            
            System.out.println("Review: " + reviewListData);
            reviewList.add(reviewListData);
        }
        
        return reviewList;
    }
    
    public long getPageCountForSearchReviewByText(String searchText, int documentLimitPerPage)
    {
        Query query = new Query();
        
        query.addCriteria(TextCriteria.forLanguage("english").matchingPhrase(searchText));
        
        long pageCount = (long) Math.ceil(((double) mongoTemplate.count(query, "review") / (double) documentLimitPerPage));
        
        return pageCount;
    }
    
    public void updateReviewText(String reviewId, String newText)
    {
        Query query = new Query(Criteria.where("review_id").is(reviewId));

        Update update = new Update()
                .set("text", newText)
                .set("date", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, "review");
    }
    
    public ReviewListData getReviewDataByReviewId(String reviewId)
    {
        List<ReviewListData> reviewList = new ArrayList<>();
        
        Aggregation returnReviewListAggregation = Aggregation.newAggregation( 
                Aggregation.match(Criteria.where("review_id").is(reviewId)), 
                Aggregation.lookup("user", "user_id", "user_id", "review_user"),
                Aggregation.unwind("review_user"),
                Aggregation.lookup("business", "business_id", "business_id", "review_business"),
                Aggregation.unwind("review_business"),
                Aggregation.project("review_id", "user_id", "business_id", "stars", "useful", "funny", 
                        "cool", "text", "date")
                .and("review_user.name").as("review_author")
                .and("review_business.name").as("review_business"));
        
        AggregationResults<Document> results = mongoTemplate.aggregate(returnReviewListAggregation, "review", Document.class);
        
        List<Document> documentList = results.getMappedResults();
        
        for (Document document : documentList)
        {
            Review review = new Review(
                    document.getObjectId("_id").toString(), 
                    document.getString("review_id"), document.getString("user_id"), 
                    document.getString("business_id"), document.getInteger("stars"), 
                    document.getInteger("useful"), document.getInteger("funny"), 
                    document.getInteger("cool"), document.getString("text"), 
                    document.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            
            String reviewAuthor = document.getString("review_author");
            String reviewBusiness = document.getString("review_business");

            ReviewListData reviewListData = new ReviewListData(review, reviewAuthor, reviewBusiness);
            
            reviewList.add(reviewListData);
        }
        
        return reviewList.get(0);
    }
}
