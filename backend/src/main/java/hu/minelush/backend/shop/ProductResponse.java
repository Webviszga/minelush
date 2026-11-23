package hu.minelush.backend.shop;

import java.util.List;

public record ProductResponse(
    Long id,
    String slug,
    String name,
    String description,
    String category,
    String categoryName,
    Integer priceHuf,
    Integer originalPriceHuf,
    String serverLabel,
    String imageUrl,
    List<String> perks,
    Integer stockLimit
    ) {
    static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getSlug(),
            product.getName(),
            product.getDescription(),
            product.getCategory().getSlug(),
            product.getCategory().getName(),
            product.getPriceHuf(),
            product.getOriginalPriceHuf(),
            product.getServerLabel(),
            product.getImageUrl(),
            product.getPerks(),
            product.getStockLimit()
        );
    }
}
