package hu.minelush.backend.shop;

public record ProductCategoryResponse(
    String slug,
    String name,
    String description
    ) {
    static ProductCategoryResponse from(ProductCategory category) {
        return new ProductCategoryResponse(
            category.getSlug(),
            category.getName(),
            category.getDescription()
        );
    }
}
