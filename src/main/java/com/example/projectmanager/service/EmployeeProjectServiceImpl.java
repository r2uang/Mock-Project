package com.example.projectmanager.service;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.EmployeeProject;
import com.example.projectmanager.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService{

    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Override
    public List<EmployeeProject> getAllEmployeeByProjectId(long id) {
        return employeeProjectRepository.getAllByProjectId(id);
    }

    @Override
    public List<EmployeeProject> getAllProjectByEmployeeId(long id) {
        return employeeProjectRepository.getAllByEmployeeId(id);
    }

    @Override
    public void saveOrUpdateEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.saveAndFlush(employeeProject);
    }

    @Override
    public void deleteEmployeeProjectByEmployeeId(long id) {

    }

    @Override
    public EmployeeProject findByEmployeeIdAndProjectId(long eid , long pid) {
        Optional<EmployeeProject> employeeProject = employeeProjectRepository.findByEmployeeIdAndProjectId(eid,pid);
        return employeeProject.orElseGet(EmployeeProject::new);
    }

}
