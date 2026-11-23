package hu.minelush.backend.shop;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {
    // összes aktív termék, kategóriánként sorban
    List<Product> findByActiveTrueOrderByCategorySortOrderAscSortOrderAsc();

    // egy kategória termékei, pl. "rangok"
    List<Product> findByActiveTrueAndCategorySlugOrderBySortOrderAsc(String categorySlug);

    // a "Kiemelt" sor
    List<Product> findByActiveTrueAndFeaturedRankNotNullOrderByFeaturedRankAsc();

    Optional<Product> findBySlugAndActiveTrue(String slug);
}
