package com.fbr.ecommerce.controller;

import com.fbr.ecommerce.controller.dto.CreateUserDto;
import com.fbr.ecommerce.entities.UserEntity;
import com.fbr.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        var user = userService.createUser(dto);

        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable("userId") UUID userId) {
        var user = userService.findById(userId);

        return user.
                map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> deleteById(@PathVariable("userId") UUID userId) {
        var user = userService.deleteById(userId);

        return user
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
