package com.repo.onlineshopping.clientrepo;

import com.repo.onlineshopping.model.Client;
import org.springframework.data.repository.CrudRepository;

/*
    CrudRepository is the interface for performing basic crud operations
    1st param, Client is the table name
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
