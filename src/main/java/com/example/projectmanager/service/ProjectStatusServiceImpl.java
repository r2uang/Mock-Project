package com.example.projectmanager.service;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.ProjectStatus;
import com.example.projectmanager.repository.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {
    @Autowired
    ProjectStatusRepository projectStatusRepository;

    @Override
    public ProjectStatus findById(long id) {
        Optional<ProjectStatus> optionalProjectStatus = projectStatusRepository.findById(id);
        return optionalProjectStatus.orElseGet(ProjectStatus::new);
    }
}
