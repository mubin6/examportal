package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    //CREATE USER
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    // GET USER BY USERNAME
    public User getUser(String username);

    // DELETE USER BY ID
    public void deleteUser(Long userId);

    // UPDATE USER
    public User updateUser(User user);


}
