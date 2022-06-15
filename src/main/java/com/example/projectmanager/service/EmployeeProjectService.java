package com.example.projectmanager.service;

import com.example.projectmanager.entities.EmployeeProject;

import java.util.List;

public interface EmployeeProjectService {

    List<EmployeeProject> getAllEmployeeByProjectId(long id);

    List<EmployeeProject> getAllProjectByEmployeeId(long id);

    void saveOrUpdateEmployeeProject(EmployeeProject employeeProject);

    void deleteEmployeeProjectByEmployeeId(long id);

    EmployeeProject findByEmployeeIdAndProjectId(long eid , long pid);


}
