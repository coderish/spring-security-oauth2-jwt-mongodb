package com.codeclef.spring.security.mongo.oauth2.jwt.repository;

import com.codeclef.spring.security.mongo.oauth2.jwt.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    Optional<User> findByUsername(String username);
}
