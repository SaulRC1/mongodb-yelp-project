package com.uhu.agi.mongodb.yelp.project.collection;

import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Document
public class Business 
{
    @Id
    private final String id;
    
    @Field("business_id")
    private final String businessId;
    
    @Field
    private String name;
    
    @Field
    private String address;
    
    @Field
    private String city;
    
    @Field
    private String state;
    
    @Field("postal_code")
    private String postalCode;
    
    @Field
    private Float latitude;
    
    @Field
    private Float longitude;
    
    @Field
    private Float stars;
    
    @Field("review_count")
    private Integer reviewCount;
    
    @Field("is_open")
    private Integer isOpen;
    
    @Field
    private Map<String, Object> attributes;
    
    @Field
    private List<String> categories;
    
    @Field
    private Map<String, String> hours;

    public Business(String id, String businessId, String name, String address, String city, 
            String state, String postalCode, Float latitude, Float longitude, 
            Float stars, Integer reviewCount, Integer isOpen, Map<String, Object> attributes, 
            List<String> categories, Map<String, String> hours)
    {
        this.id = id;
        this.businessId = businessId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
        this.reviewCount = reviewCount;
        this.isOpen = isOpen;
        this.attributes = attributes;
        this.categories = categories;
        this.hours = hours;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public Float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Float latitude)
    {
        this.latitude = latitude;
    }

    public Float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Float longitude)
    {
        this.longitude = longitude;
    }

    public Float getStars()
    {
        return stars;
    }

    public void setStars(Float stars)
    {
        this.stars = stars;
    }

    public Integer getReviewCount()
    {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount)
    {
        this.reviewCount = reviewCount;
    }

    public Integer getIsOpen()
    {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen)
    {
        this.isOpen = isOpen;
    }

    public Map<String, Object> getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes)
    {
        this.attributes = attributes;
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }

    public Map<String, String> getHours()
    {
        return hours;
    }

    public void setHours(Map<String, String> hours)
    {
        this.hours = hours;
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public String getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Business{");
        sb.append("id=").append(id);
        sb.append(", businessId=").append(businessId);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", stars=").append(stars);
        sb.append(", reviewCount=").append(reviewCount);
        sb.append(", isOpen=").append(isOpen);
        sb.append(", attributes=").append(attributes);
        sb.append(", categories=").append(categories);
        sb.append(", hours=").append(hours);
        sb.append('}');
        return sb.toString();
    }
    
}
