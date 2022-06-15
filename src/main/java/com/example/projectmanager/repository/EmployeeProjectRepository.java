package com.example.projectmanager.repository;

import com.example.projectmanager.entities.EmployeeProject;
import com.example.projectmanager.entities.EmployeeProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectKey> {
    @Query(value = "SELECT * FROM project_manager.employee_project where project_id = ?1 and status = true", nativeQuery = true)
    List<EmployeeProject> getAllByProjectId(long id);
    @Query(value = "SELECT * FROM project_manager.employee_project where employee_id = ?1",nativeQuery = true)
    List<EmployeeProject> getAllByEmployeeId(long id);

    @Query(value = "SELECT * FROM project_manager.employee_project ep where ep.employee_id = ?1 and ep.project_id = ?2" ,nativeQuery = true)
    Optional<EmployeeProject> findByEmployeeIdAndProjectId(long eid , long pid);
}
