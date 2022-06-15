package com.example.projectmanager.controller;

import com.example.projectmanager.entities.Role;
import com.example.projectmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/all")
    public String listRole(ModelMap model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);
        return "role";
    }
}
