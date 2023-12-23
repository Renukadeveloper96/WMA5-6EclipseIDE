package com.minton.userservice.service;


import com.minton.userservice.entities.Role;
import com.minton.userservice.entities.User;
import com.minton.userservice.entities.UserType;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.ModifyProfileRequest;
import com.minton.userservice.repository.RoleRepository;
import com.minton.userservice.repository.UserRepository;
import com.minton.userservice.repository.UserTypeRepository;
import com.minton.userservice.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);

    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

    System.out.println("user is ========================================"+user);
        return UserPrincipal.create(user);
    }

    public void deleteUser(User user)
    {
        //change delete flag to true
        //change deleted on data
        //set isActive to false
        user.setDeletedOn(new Date());
        user.setActive(false);
        user.setDeleteFlag(true);
        userRepository.save(user);
    }

    public User updateUser(User user, ModifyProfileRequest modifyUserRequest) {

        if(modifyUserRequest.getRole()!=null && modifyUserRequest.getRole().isEmpty()) {
            Role role = roleRepository.findByName(modifyUserRequest.getRole());
            user.getRoles().add(role);
        }
        if(modifyUserRequest.getUserType()!=null && modifyUserRequest.getUserType().isEmpty())
        {
            UserType type =userTypeRepository.findByName(modifyUserRequest.getUserType());
            user.setUserType(type);
        }

        user.setFirstName(modifyUserRequest.getFirstName());
        user.setLastName(modifyUserRequest.getLastName());
        user.setPhoneNumber(modifyUserRequest.getPhoneNumber());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users =
                userRepository.findAllByIsActive(true);
        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String  email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}