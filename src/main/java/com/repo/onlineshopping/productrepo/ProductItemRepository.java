package com.repo.onlineshopping.productrepo;

import com.repo.onlineshopping.model.ProductItem;
import org.springframework.data.repository.CrudRepository;
/*
    CrudRepository is the interface for performing basic crud operations
    1st param, ProductItem is the table name
 */
public interface ProductItemRepository extends CrudRepository<ProductItem, Integer> {
}
