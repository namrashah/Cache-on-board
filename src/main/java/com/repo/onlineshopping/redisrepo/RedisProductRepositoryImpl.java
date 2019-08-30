package com.repo.onlineshopping.redisrepo;

import com.repo.onlineshopping.model.RedisProduct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
    This class contains all the required methods to interact with redis with the help of hashoperations interface
 */
@Repository
public class RedisProductRepositoryImpl implements RedisProductRepository {

    /*
        Helper class instance that simplifies Redis data access code.
        1st parameter <String> is the Redis key type against which the template works
        2nd parameter <RedisProduct> is the Redis value type against which the template works
     */
    private RedisTemplate<String, RedisProduct> redisTemplate;

    /*
        To perform map specific operations
        There is a key, a hashkey, and a value
        key- it can be referred as the name of the hash table
        hashkey- it can be referred the key of a specific hash table
        value- it is the value you put against a specific key inside a hash table
    */

    private HashOperations hashOperations;

    /*
        initializes hashoperations and redistempelate
     */
    public RedisProductRepositoryImpl(RedisTemplate<String, RedisProduct> redisTemplate) {
        System.out.println("Redis product repository implementation");
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    /*
        Saves an entry to the product hash table
     */
    @Override
    public void save(RedisProduct redisProduct) {
        hashOperations.put("PRODUCT", redisProduct.getProduct_name() + "_" + redisProduct.getCategory(), redisProduct);
    }

    /*
        This method basically finds all the entries in the product hash table which contains the inserted text in the search box
        Implementation logic- It takes all the keys(combination of productName and productCategory) present in the product hash table
        and performs string.contains() operation over all the keys and if the result is true, it saves that particular entry into a hash map.
        Finally it returns the hash map containing all the entries from the product hash table which contains the inserted text in the search box.
     */
    @Override
    public Map<String, RedisProduct> findAll(String key)  {
        Set<String> set = hashOperations.keys("PRODUCT");
        Map<String, RedisProduct> map = new HashMap();
        System.out.println(set);
        for(String str : set){
            if(str.contains(key)){
                map.put(str, findExact(str));
            }
        }
        System.out.println(map);
        return map;
    }

    /*
    * Finds and returns the value against the provided key in the product hash table
    * */
    @Override
    public RedisProduct findExact(String hashKey) {
        return (RedisProduct)hashOperations.get("PRODUCT", hashKey);
        //return (RedisProduct)hashOperations.get(product_name, category);
    }
}
