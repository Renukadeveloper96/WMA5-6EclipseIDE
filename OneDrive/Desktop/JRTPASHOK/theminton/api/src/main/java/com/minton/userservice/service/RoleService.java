package com.minton.userservice.service;

import com.minton.userservice.entities.Role;
import com.minton.userservice.entities.UserType;
import com.minton.userservice.repository.RoleRepository;
import com.minton.userservice.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<Role> getAllRoles() {
       return roleRepository.findAll();
    }

    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }


    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role saveRole(Role role) {
      return   roleRepository.save(role);
    }

    public UserType saveUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public UserType getUserType(Long id) {
        return userTypeRepository.findById(id).orElse(null);
    }

    public List<UserType> getAllUserTypes() {

        return userTypeRepository.findAllByDeleteFlag(false);
    }
}
