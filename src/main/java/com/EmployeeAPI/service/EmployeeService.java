package com.EmployeeAPI.service;

import com.EmployeeAPI.dto.Employee;
import com.EmployeeAPI.exceptions.EmployeeNotFoundException;
import com.EmployeeAPI.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setJobRole(employee.getJobRole());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public EmployeeNotFoundException deleteEmployee(long id) {
        employeeRepository.findById(id).ifPresentOrElse(employee -> employeeRepository.deleteById(id),
                () -> {throw new EmployeeNotFoundException(id);});
        return null;
    }
}
