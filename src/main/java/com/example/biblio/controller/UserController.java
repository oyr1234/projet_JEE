package com.example.biblio.controller;

import com.example.biblio.dto.PersonneRequest;
import com.example.biblio.dto.PersonneResponse;
import com.example.biblio.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "https://biblio-six-fawn.vercel.app/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public PersonneResponse addUser(@RequestBody PersonneRequest request) {
        return userService.addUser(request);
    }

    @GetMapping
    public List<PersonneResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public PersonneResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public PersonneResponse updateUser(
            @PathVariable Long id,
            @RequestBody PersonneRequest request
    ) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}