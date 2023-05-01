package com.EmployeeAPI.service;

import com.EmployeeAPI.dto.Employee;
import com.EmployeeAPI.exceptions.EmployeeControllerExceptionHandler.*;
import com.EmployeeAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getFirstName() == null || employee.getLastName() == null || employee.getJobRole() == null) {
            throw new InvalidEmployeeDataException();
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        existingEmployee.setFirstName((employee.getFirstName()));
        existingEmployee.setLastName((employee.getLastName()));
        existingEmployee.setJobRole(employee.getJobRole());
        return employeeRepository.save(existingEmployee);
    }

    public boolean deleteEmployee(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
