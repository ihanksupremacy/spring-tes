package com.meme.meme.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.meme.meme.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
