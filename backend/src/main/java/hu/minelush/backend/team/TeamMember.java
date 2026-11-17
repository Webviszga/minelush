package hu.minelush.backend.team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String description;
    private String headUrl;
    private String renderUrl;
    
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getDescription() {
        return description;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public String getRenderUrl() {
        return renderUrl;
    }

}
