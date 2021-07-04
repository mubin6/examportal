package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // CREATING USER
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());

        if(local != null) {
            System.out.println("User is already there!!!");
            throw new UserFoundException();
            //throw new Exception("User already present!!!");
        } else {
            // New User Creation
            for (UserRole ur:userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }

        return local;
    }

    // GET USER BY USERNAME
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        User user1 = this.userRepository.findByUsername(user.getUsername());
        System.out.println(user1);
        if (user1 != null) {
            user1.setFirstname(user.getFirstname());
            user1.setLastname(user.getLastname());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());

            return this.userRepository.save(user1);

        } else {
            return null;
        }

    }
}
