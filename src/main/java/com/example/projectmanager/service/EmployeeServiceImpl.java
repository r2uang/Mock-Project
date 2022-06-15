package com.example.projectmanager.service;

import com.example.projectmanager.entities.*;
import com.example.projectmanager.repository.EmployeeProjectRepository;
import com.example.projectmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElseGet(Employee::new);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findPaginatedWithKeyword(int pageNo, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (keyword != null) {
            return employeeRepository.findAllByEmployeeName(keyword, pageable);
        }
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findPaginatedWithRoleId(int pageNo, int pageSize, Long role) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (role != -1) {
            return employeeRepository.findAllByRole(role, pageable);
        }
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAllByEmployeeNameAndRoleAndLanguages(int pageNo, int pageSize, Long role, Long language_id, String name) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if ((role == null || role != -1) && name != null && (language_id == null || language_id != -1)) {
//            return employeeRepository.findAllByEmployeeNameAndRoleAndLanguages(name,role,language_id,pageable);
            return employeeRepository.findAll(pageable);
        } else {

            return employeeRepository.findAll(pageable);
        }
    }

    @Override
    public List<Employee> listAllByStatusAndLanguage(Long project_id) {
        return employeeRepository.listAllByStatusAndLanguage(project_id);
    }

    @Override
    public List<Employee> getActiveEmployee() {
        return employeeRepository.getEmployeeByEmployeeStatus();
    }

    @Override
    public Page<Employee> getEmployeePaginated(int pageNumber, FilterRequest filterRequest) {
        Sort sort = Sort.by("updateAt").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1,filterRequest.getPageSize(),sort);
        return employeeRepository.filterEmployee(filterRequest,pageable);
    }

    @Override
    public List<Employee> getAvailableEmployeeForProject(Project project) {
        int maxEmployeeInProject = 3;
        boolean isExistEmployee = false;
        List<Employee> employeeList = employeeRepository.getListEmployeeAvailable(maxEmployeeInProject);
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employeeList) {
            for (Language language : project.getLanguages()) {
                if (employee.getLanguages().contains(language)) {
                    result.add(employee);
                    break;
                }
            }
        }
        return result;
    }
}

