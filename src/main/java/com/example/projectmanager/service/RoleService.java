package com.example.projectmanager.service;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> listAll();

    Role getRoleById(Long id);
}
