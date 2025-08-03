package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpServices {

    private final EmpReposetory empReposetory;
    public EmpServiceImpl(EmpReposetory empReposetory) {
        this.empReposetory = empReposetory;
    }

    @Override
    public List<Employee> getEmployee() {
        List<Employee> employees = new ArrayList<>();
        List<EmpEntity> empEntities = empReposetory.findAll();
        for (EmpEntity empEntity : empEntities) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(empEntity, employee);
            employees.add(employee);
        }
        return employees;
    }

    @Override
public String createEmployee(Employee employee) {
    EmpEntity empEntity = new EmpEntity();
    BeanUtils.copyProperties(employee, empEntity);
    empEntity.setId(null);
    empReposetory.save(empEntity);
    return "Saved successfully";
}


    @Override
    public boolean deleteEmployee(Long id) {
        EmpEntity empEntity = empReposetory.findById(id).orElse(null);
        if (empEntity != null) {
            empReposetory.delete(empEntity);
            return true;
        }
        return false;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmpEntity empEntity = empReposetory.findById(id).orElse(null);
        if (empEntity != null) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(empEntity, employee);
            return employee;
        }
        return null;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmpEntity existingEmp = empReposetory.findById(id).orElse(null);
        if (existingEmp != null) {
            existingEmp.setEmail(employee.getEmail());
            existingEmp.setName(employee.getName());
            existingEmp.setPhone(employee.getPhone());
            empReposetory.save(existingEmp);
            return "Updated successfully";
        }
        return "Employee not found";
    }
}
