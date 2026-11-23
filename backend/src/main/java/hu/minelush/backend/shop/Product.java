package hu.minelush.backend.shop;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    private String slug;
    private String name;
    private String description;
    private Integer priceHuf;
    private Integer originalPriceHuf;
    private String serverLabel;
    private String imageUrl;

    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<String> perks;

    private Integer stockLimit;
    private Integer featuredRank;
    private Integer sortOrder;
    private boolean active;

    public Long getId() {
        return id;
    }
    public ProductCategory getCategory() {
        return category;
    }
    public String getSlug() {
        return slug;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Integer getPriceHuf() {
        return priceHuf;
    }
    public Integer getOriginalPriceHuf() {
        return originalPriceHuf;
    }
    public String getServerLabel() {
        return serverLabel;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public List<String> getPerks() {
        return perks;
    }
    public Integer getStockLimit() {
        return stockLimit;
    }
    public Integer getFeaturedRank() {
        return featuredRank;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }
    public boolean isActive() {
        return active;
    }
}
