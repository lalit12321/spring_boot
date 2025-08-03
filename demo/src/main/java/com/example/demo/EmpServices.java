package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface EmpServices {
    List<Employee> getEmployee();
    String createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
}
