package com.example.employee_service.service;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee findEmployeeById(String userId) {
        return employeeRepository.findEmployeeById(userId);
    }
}