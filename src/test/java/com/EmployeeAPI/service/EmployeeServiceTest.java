package com.EmployeeAPI.service;

import com.EmployeeAPI.dto.Employee;
import com.EmployeeAPI.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "sre"));
        employees.add(new Employee("Jane", "swe"));
        when(employeeRepository.findAll()).thenReturn(employees);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeRepository(employeeRepository);

        List<Employee> result = employeeService.getAllEmployees();

        Assertions.assertEquals(employees, result);
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee("John", "sre");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeRepository(employeeRepository);

        Employee result = employeeService.getEmployeeById(1L);

        Assertions.assertEquals(employee, result);
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee("John", "sre");
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeRepository(employeeRepository);

        Employee result = employeeService.addEmployee(employee);

        Assertions.assertEquals(employee, result);
    }

    @Test
    void testUpdateEmployee() {
        Employee existingEmployee = new Employee("John", "sre");
        Employee updatedEmployee = new Employee("Jane", "swe");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeRepository(employeeRepository);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        Assertions.assertEquals(updatedEmployee, result);
    }
}

