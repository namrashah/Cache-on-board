package com.repo.onlineshopping.redisrepo;

import com.repo.onlineshopping.model.RedisProduct;

import java.util.Map;

/*
    Interface to define all the required methods to interact with redis cache
 */
public interface RedisProductRepository {

    void save(RedisProduct redisProduct);
    Map<String, RedisProduct> findAll(String key);
    RedisProduct findExact(String hashKey);

}
