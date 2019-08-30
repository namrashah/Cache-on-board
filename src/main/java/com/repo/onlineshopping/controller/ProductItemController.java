package com.repo.onlineshopping.controller;

import com.repo.onlineshopping.model.ProductItem;
import com.repo.onlineshopping.model.RedisProduct;
import com.repo.onlineshopping.productrepo.ProductItemRepository;
import com.repo.onlineshopping.redisrepo.RedisProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
    Handles endpoints to interact with productitem database and also populates redis cache
 */
@Controller
@RequestMapping(path = "/productitem")
public class ProductItemController {
    public ProductItemController() {
        System.out.println("from product item controller");
    }

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private RedisProductRepository redisProductRepository;

    /*
        Adds an entry to the productitem database and also to the redis cache where the hash table name is product
     */
    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestHeader Integer id, @RequestHeader String category, @RequestHeader String product_name, @RequestHeader Integer price){
        ProductItem p = new ProductItem();
        p.setId(id);
        p.setCategory(category);
        p.setPrice(price);
        p.setProduct_name(product_name);
        productItemRepository.save(p);
        redisProductRepository.save(new RedisProduct(id, product_name, category, price));
        return "saved";
    }

    /*
        gets all the data from the productitem database
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<ProductItem> all(){
        return productItemRepository.findAll();
    }

    /*
        Updates an entry in the productitem databse and also updates it in redis cache
     */
    @PutMapping(path = "/update")
    public @ResponseBody String update(@RequestHeader Integer id, @RequestHeader Integer price){
        Optional<ProductItem> op = productItemRepository.findById(id);
        ProductItem productItem = op.get();
        productItemRepository.deleteById(id);
        ProductItem p = new ProductItem();
        p.setId(id);
        p.setCategory(productItem.getCategory());
        p.setPrice(price);
        p.setProduct_name(productItem.getProduct_name());
        productItemRepository.save(p);
        redisProductRepository.save(new RedisProduct(id, productItem.getProduct_name(), productItem.getCategory(), price));
        return "updated";
    }


}
