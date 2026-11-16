package hu.minelush.backend.gameserver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class GameServer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String versionLabel;
    private String imageUrl;
    
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getVersionLabel() {
        return versionLabel;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    
}
