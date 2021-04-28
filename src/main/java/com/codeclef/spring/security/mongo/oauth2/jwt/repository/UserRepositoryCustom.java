package com.codeclef.spring.security.mongo.oauth2.jwt.repository;


import com.codeclef.spring.security.mongo.oauth2.jwt.model.mongo.User;

import java.util.Collection;
import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAndUpdateByUsernames(Collection<User> users);
}
