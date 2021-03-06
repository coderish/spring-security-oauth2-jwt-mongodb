package com.codeclef.spring.security.mongo.oauth2.jwt.controller;

import com.codeclef.spring.security.mongo.oauth2.jwt.constant.AuthorizationGrant;
import com.codeclef.spring.security.mongo.oauth2.jwt.model.mongo.User;
import com.codeclef.spring.security.mongo.oauth2.jwt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @see <a href="https://github.com/slantz/spring-boot-2-oauth2-jwt-docker-mongo/wiki">API</a>
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @PreAuthorize(AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    @PreAuthorize(AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(this.userService.getById(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/me")
    @PreAuthorize(AuthorizationGrant.AUTHORITY_USER)
    public ResponseEntity<User> getMe(Principal principal) {
        return new ResponseEntity<>(this.userService.getByUsername(principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "/username/{username}")
    @PreAuthorize(AuthorizationGrant.SCOPE_RESOURCE_SERVICE + " or " + AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<User> getByUserName(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping(path = "/username/{username}/authorities")
    @PreAuthorize(AuthorizationGrant.SCOPE_RESOURCE_SERVICE + " or " + AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<List<GrantedAuthority>> getAuthoritiesByUsername(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.getAuthoritiesByUsername(username), HttpStatus.OK);
    }

    @PostMapping(path = "/sign-up")
    @PreAuthorize(AuthorizationGrant.SCOPE_WRITE)
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.signUp(user), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        return new ResponseEntity<>(this.userService.createAll(users), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize(AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> users) {
        return new ResponseEntity<>(this.userService.updateAll(users), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    @PreAuthorize(AuthorizationGrant.AUTHORITY_ADMIN)
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) {
        return new ResponseEntity<>(this.userService.deleteById(userId), HttpStatus.OK);
    }
}
