package com.example.projectmanager.service;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.EmployeeStatus;
import com.example.projectmanager.entities.FilterRequest;
import com.example.projectmanager.entities.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> listAll();

    void saveOrUpdateEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee findById(Long id);

    Page<Employee> findPaginated(int pageNo, int pageSize);

    Page<Employee> findPaginatedWithKeyword(int pageNo, int pageSize, String keyword);

    Page<Employee> findPaginatedWithRoleId(int pageNo, int pageSize, Long role);

    Page<Employee> findAllByEmployeeNameAndRoleAndLanguages(int pageNo, int pageSize, Long role, Long language_id, String name);

    List<Employee> listAllByStatusAndLanguage(Long project_id);

    List<Employee> getAvailableEmployeeForProject(Project project);

    List<Employee> getActiveEmployee();

    Page<Employee> getEmployeePaginated(int pageNumber, FilterRequest filterRequest);
}
