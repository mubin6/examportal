package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // CREATING USER
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        // encoding password with bcryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    //GET USER BY USERNAME
    @GetMapping("/{username}")
    public User getUSer(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    // DELETE USER BY ID
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

    // UPDATE USER
    @PutMapping("/")
    public User updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @ExceptionHandler(UserFoundException.class)
        public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return new ResponseEntity(HttpStatus.FOUND);
    };
}
