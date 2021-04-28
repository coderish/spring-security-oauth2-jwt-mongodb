package com.codeclef.spring.security.mongo.oauth2.jwt.service;

import com.codeclef.spring.security.mongo.oauth2.jwt.model.mongo.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(String id);

    User getByUsername(String username);

    List<GrantedAuthority> getAuthoritiesByUsername(String username);

    User signUp(User user);

    List<User> createAll(List<User> users);

    List<User> updateAll(List<User> users);

    boolean deleteById(String id);
}
