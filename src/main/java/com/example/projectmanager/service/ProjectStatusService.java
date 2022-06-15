package com.example.projectmanager.service;

import com.example.projectmanager.entities.ProjectStatus;

public interface ProjectStatusService {

    ProjectStatus findById(long id);
}
