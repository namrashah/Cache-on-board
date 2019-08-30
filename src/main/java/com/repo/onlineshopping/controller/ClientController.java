package com.repo.onlineshopping.controller;

import com.repo.onlineshopping.clientrepo.ClientRepository;
import com.repo.onlineshopping.model.Client;
import com.repo.onlineshopping.model.ProductItem;
import com.repo.onlineshopping.model.RedisProduct;
import com.repo.onlineshopping.productrepo.ProductItemRepository;
import com.repo.onlineshopping.redisrepo.RedisProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/*
    Handles endpoints to interact with client database
 */
@Controller
@RequestMapping(path = "/client")
public class ClientController {
    public ClientController() {
        System.out.println("From Client controller");
    }

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private RedisProductRepository redisProductRepository;

    /*
        Adds a new entry to the client database when client orders some product
     */
    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestHeader Integer id, @RequestHeader String clientName, @RequestHeader Integer productId){
        Optional<ProductItem> optional = productItemRepository.findById(productId);
        ProductItem productItem = optional.get();
        Client client = new Client();
        client.setClientName(clientName);
        client.setId(id);
        client.setPrice(productItem.getPrice());
        client.setProductCategory(productItem.getCategory());
        client.setProductId(productItem.getId());
        client.setProductName(productItem.getProduct_name());
        clientRepository.save(client);
        return "saved";
    }

    /*
        Gets all the entries present in the client database
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Client> all(){
        return clientRepository.findAll();
    }

    /*
        Calls findAll method of the RedisProductRepositoryImpl to get the data containing string typed in the search box
     */
    @GetMapping(path="/redisAll")
    public @ResponseBody Map<String, RedisProduct> redisAll(@RequestHeader String key){
        return redisProductRepository.findAll(key);
    }

}
