package com.meme.meme.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.meme.meme.models.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
