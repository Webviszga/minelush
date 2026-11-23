package hu.minelush.backend.shop;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    private final ProductCategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ShopController(ProductCategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/categories")
    public List<ProductCategoryResponse> listCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc().stream()
            .map(ProductCategoryResponse::from)
            .toList();
    }

    // /api/shop/products                    -> minden termék
    // /api/shop/products?category=rangok    -> egy kategória
    // /api/shop/products?featured=true      -> a "Kiemelt" sor
    @GetMapping("/products")
    public List<ProductResponse> listProducts(
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "false") boolean featured) {
        List<Product> products;
        if (featured) {
            products = productRepository.findByActiveTrueAndFeaturedRankNotNullOrderByFeaturedRankAsc();
        } else if (category != null) {
            products = productRepository.findByActiveTrueAndCategorySlugOrderBySortOrderAsc(category);
        } else {
            products = productRepository.findByActiveTrueOrderByCategorySortOrderAscSortOrderAsc();
        }
        return products.stream()
            .map(ProductResponse::from)
            .toList();
    }

    @GetMapping("/products/{slug}")
    public ProductResponse getProduct(@PathVariable String slug) {
        return productRepository.findBySlugAndActiveTrue(slug)
            .map(ProductResponse::from)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nincs ilyen termék."));
    }
}
