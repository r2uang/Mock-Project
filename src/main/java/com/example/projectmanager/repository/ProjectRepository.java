package com.example.projectmanager.repository;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("SELECT p FROM Project p WHERE p.projectName  like %?1% order by p.Id DESC")
    Page<Project> findAllByProjectName(String name, Pageable pageable);
}
