package com.uhu.agi.mongodb.yelp.project.data;

import com.uhu.agi.mongodb.yelp.project.collection.Tip;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class TipListData 
{
    private final Tip tip;
    
    private final String tipAuthor;

    private final String tipBusiness;

    public TipListData(Tip tip, String tipAuthor, String tipBusiness)
    {
        this.tip = tip;
        this.tipAuthor = tipAuthor;
        this.tipBusiness = tipBusiness;
    }

    public Tip getTip()
    {
        return tip;
    }

    public String getTipAuthor()
    {
        return tipAuthor;
    }

    public String getTipBusiness()
    {
        return tipBusiness;
    }

    @Override
    public String toString()
    {
        return "TipListData{" + "tip=" + tip + ", tipAuthor=" + tipAuthor + ", tipBusiness=" + tipBusiness + '}';
    }
}
