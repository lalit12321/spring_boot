package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpControler {
    @Autowired
    EmpServices empServices;
    @GetMapping("employees")
    public List<Employee> getEmployee() {
        return empServices.getEmployee();
    }
    @GetMapping("employees/{id}")
    public Employee getEmpById(@PathVariable Long id){
        return empServices.getEmployeeById(id);
    }
    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {
        // employees.add(employee);
        empServices.createEmployee(employee);
        return "Employee created successfully";
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
       if(empServices.deleteEmployee(id)) {
            return "Employee deleted successfully";
        }
        else {
            return "Employee not found";
        }
    }
    @PutMapping("employees/{id}")
    public String updateEmp(@PathVariable Long id, @RequestBody Employee employee){
        return empServices.updateEmployee(id, employee);
    }
}
