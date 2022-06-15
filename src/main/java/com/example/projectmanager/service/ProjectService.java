package com.example.projectmanager.service;

import com.example.projectmanager.entities.Project;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> listAll();

    void saveOrUpdateProject(Project project);

    void deleteEmployee(Long id);

    Project findById(Long id);

    Page<Project> findPaginated(int pageNo, int pageSize);

    Page<Project> findPaginatedWithKeyword(int pageNo, int pageSize, String keyword);
}
