package hu.minelush.backend.shop;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface ProductCategoryRepository extends Repository<ProductCategory, Long> {
    List<ProductCategory> findAllByOrderBySortOrderAsc();
}
