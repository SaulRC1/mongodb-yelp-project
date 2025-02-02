package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.User;
import java.util.List;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class UserInformation 
{
    private User user;
    private List<ReviewData> reviewDataList;

    public UserInformation(User user, List<ReviewData> reviewDataList)
    {
        this.user = user;
        this.reviewDataList = reviewDataList;
    }

    public UserInformation()
    {
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<ReviewData> getReviewDataList()
    {
        return reviewDataList;
    }

    public void setReviewDataList(List<ReviewData> reviewDataList)
    {
        this.reviewDataList = reviewDataList;
    }
}
