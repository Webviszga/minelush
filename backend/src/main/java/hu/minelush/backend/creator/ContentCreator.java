package hu.minelush.backend.creator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class ContentCreator {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String handle;
    private Integer subscriberCount;
    private String imageUrl;
    private String channelUrl;
    
    public Long getId() {
        return id;
    }
    public String getHandle() {
        return handle;
    }
    public Integer getSubscriberCount() {
        return subscriberCount;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getChannelUrl() {
        return channelUrl;
    }

}
