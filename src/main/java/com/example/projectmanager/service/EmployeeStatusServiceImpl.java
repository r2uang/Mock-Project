package com.example.projectmanager.service;

import com.example.projectmanager.entities.EmployeeStatus;
import com.example.projectmanager.repository.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeStatusServiceImpl implements EmployeeStatusService {

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;

    @Override
    public List<EmployeeStatus> listAll() {
        return employeeStatusRepository.findAll();
    }
}
