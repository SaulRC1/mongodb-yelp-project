package com.uhu.agi.mongodb.yelp.project.collection;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Document
public class User 
{
    @Id
    private final String id;
    
    @Field("user_id")
    private final String userId;

    @Field
    private final String name;
    
    @Field("review_count")
    private int reviewCount;
    
    @Field("yelping_since")
    private final String yelpingSince;
    
    @Field
    private int useful;
    
    @Field
    private int funny;
    
    @Field
    private int cool;
    
    @Field
    private List<String> elite;
    
    @Field
    private List<String> friends;
    
    @Field
    private int fans;
    
    @Field("average_stars")
    private float averageStars;
    
    @Field("compliment_hot")
    private int complimentHot;
    
    @Field("compliment_more")
    private int complimentMore;
    
    @Field("compliment_profile")
    private int complimentProfile;
    
    @Field("compliment_cute")
    private int complimentCute;
    
    @Field("compliment_list")
    private int complimentList;
    
    @Field("compliment_note")
    private int complimentNote;
    
    @Field("compliment_plain")
    private int complimentPlain;
    
    @Field("compliment_cool")
    private int complimentCool;
    
    @Field("compliment_funny")
    private int complimentFunny;
    
    @Field("compliment_writer")
    private int complimentWriter;
    
    @Field("compliment_photos")
    private int complimentPhotos;

    public User(String id, String userId, String name, String yelpingSince)
    {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.yelpingSince = yelpingSince;
    }

    public User(String id, String userId, String name, int reviewCount, String yelpingSince, 
            int useful, int funny, int cool, List<String> elite, List<String> friends, 
            int fans, float averageStars, int complimentHot, int complimentMore, int complimentProfile, 
            int complimentCute, int complimentList, int complimentNote, int complimentPlain, 
            int complimentCool, int complimentFunny, int complimentWriter, int complimentPhotos)
    {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.reviewCount = reviewCount;
        this.yelpingSince = yelpingSince;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.elite = elite;
        this.friends = friends;
        this.fans = fans;
        this.averageStars = averageStars;
        this.complimentHot = complimentHot;
        this.complimentMore = complimentMore;
        this.complimentProfile = complimentProfile;
        this.complimentCute = complimentCute;
        this.complimentList = complimentList;
        this.complimentNote = complimentNote;
        this.complimentPlain = complimentPlain;
        this.complimentCool = complimentCool;
        this.complimentFunny = complimentFunny;
        this.complimentWriter = complimentWriter;
        this.complimentPhotos = complimentPhotos;
    }

    public int getReviewCount()
    {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount)
    {
        this.reviewCount = reviewCount;
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

    public List<String> getElite()
    {
        return elite;
    }

    public void setElite(List<String> elite)
    {
        this.elite = elite;
    }

    public List<String> getFriends()
    {
        return friends;
    }

    public void setFriends(List<String> friends)
    {
        this.friends = friends;
    }

    public int getFans()
    {
        return fans;
    }

    public void setFans(int fans)
    {
        this.fans = fans;
    }

    public float getAverageStars()
    {
        return averageStars;
    }

    public void setAverageStars(float averageStars)
    {
        this.averageStars = averageStars;
    }

    public int getComplimentHot()
    {
        return complimentHot;
    }

    public void setComplimentHot(int complimentHot)
    {
        this.complimentHot = complimentHot;
    }

    public int getComplimentMore()
    {
        return complimentMore;
    }

    public void setComplimentMore(int complimentMore)
    {
        this.complimentMore = complimentMore;
    }

    public int getComplimentProfile()
    {
        return complimentProfile;
    }

    public void setComplimentProfile(int complimentProfile)
    {
        this.complimentProfile = complimentProfile;
    }

    public int getComplimentCute()
    {
        return complimentCute;
    }

    public void setComplimentCute(int complimentCute)
    {
        this.complimentCute = complimentCute;
    }

    public int getComplimentList()
    {
        return complimentList;
    }

    public void setComplimentList(int complimentList)
    {
        this.complimentList = complimentList;
    }

    public int getComplimentNote()
    {
        return complimentNote;
    }

    public void setComplimentNote(int complimentNote)
    {
        this.complimentNote = complimentNote;
    }

    public int getComplimentPlain()
    {
        return complimentPlain;
    }

    public void setComplimentPlain(int complimentPlain)
    {
        this.complimentPlain = complimentPlain;
    }

    public int getComplimentCool()
    {
        return complimentCool;
    }

    public void setComplimentCool(int complimentCool)
    {
        this.complimentCool = complimentCool;
    }

    public int getComplimentFunny()
    {
        return complimentFunny;
    }

    public void setComplimentFunny(int complimentFunny)
    {
        this.complimentFunny = complimentFunny;
    }

    public int getComplimentWriter()
    {
        return complimentWriter;
    }

    public void setComplimentWriter(int complimentWriter)
    {
        this.complimentWriter = complimentWriter;
    }

    public int getComplimentPhotos()
    {
        return complimentPhotos;
    }

    public void setComplimentPhotos(int complimentPhotos)
    {
        this.complimentPhotos = complimentPhotos;
    }
}
