package com.example.projectmanager.service;

import com.example.projectmanager.entities.Project;
import com.example.projectmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public List<Project> listAll() {
        return projectRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public void saveOrUpdateProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public Project findById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.orElseGet(Project::new);
    }

    @Override
    public Page<Project> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<Project> findPaginatedWithKeyword(int pageNo, int pageSize, String keyword) {
        Pageable pageable =  PageRequest.of(pageNo - 1, pageSize);
        if(keyword != ""){
            return projectRepository.findAllByProjectName(keyword,pageable);
        }else {
            return projectRepository.findAll(pageable);
        }
    }
}
