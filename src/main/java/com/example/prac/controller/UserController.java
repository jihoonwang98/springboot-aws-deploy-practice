package com.example.prac.controller;

import com.example.prac.entity.User;
import com.example.prac.exception.ResourceNotFoundException;
import com.example.prac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // get all users api
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
    }


    // create user
    @PostMapping
    public void createUser(@RequestBody User userRequest) {
        userRepository.save(userRequest);
    }


    // update user
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User userRequest, @PathVariable Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));

        findUser.setEmail(userRequest.getEmail());
        findUser.setFirstName(userRequest.getFirstName());
        findUser.setLastName(userRequest.getLastName());

        userRepository.save(findUser);
    }


    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(findUser);

        return ResponseEntity.ok().build();
    }
}
