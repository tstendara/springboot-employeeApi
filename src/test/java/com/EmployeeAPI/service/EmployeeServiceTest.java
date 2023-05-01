package com.EmployeeAPI.service;

import com.EmployeeAPI.dto.Employee;
import com.EmployeeAPI.exceptions.EmployeeControllerExceptionHandler.*;
import com.EmployeeAPI.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    @DisplayName("Get all employees")
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "Developer"));
        employees.add(new Employee(2L, "Jane", "Doe", "Manager"));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("Developer", result.get(0).getJobRole());
        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe", result.get(1).getLastName());
        assertEquals("Manager", result.get(1).getJobRole());

        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    @DisplayName("Get employee by id")
    void testGetEmployeeById() {
        long id = 1L;
        Employee employee = new Employee(id, "John", "Doe", "Developer");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(id);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("Developer", result.getJobRole());

        verify(employeeRepository, times(1)).findById(id);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    @DisplayName("Get employee by id - EmployeeNotFoundException")
    void testGetEmployeeByIdEmployeeNotFoundException() {
        long id = 1L;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(id));

        verify(employeeRepository, times(1)).findById(id);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    @DisplayName("Add employee")
    void testAddEmployee() {
        Employee employee = new Employee(null, "John", "Doe", "Developer");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("Developer", result.getJobRole());

        verify(employeeRepository, times(1)).save(employee);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    @DisplayName("Add employee - InvalidEmployeeDataException")
    void testAddEmployeeInvalidEmployeeDataException() {
        Employee employee = new Employee();
        assertThrows(InvalidEmployeeDataException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    @DisplayName("Update employee")
    void testUpdateEmployee() {
        long id = 1L;
        Employee existingEmployee = new Employee(id, "John", "Doe", "Developer");
        Employee updatedEmployee = new Employee(id, "Jane", "Doe", "Manager");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(id, updatedEmployee);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("Manager", result.getJobRole());

        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(updatedEmployee);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    @DisplayName("Update employee - EmployeeNotFoundException")
    void testUpdateEmployeeEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(1, new Employee()));
    }

    @Test
    @DisplayName("Delete employee")
    void testDeleteEmployee() {
        long id = 1L;
        Employee employee = new Employee(id, "John", "Doe", "Manager");

        when(employeeRepository.existsById(id)).thenReturn(true);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee addedEmployee = employeeService.addEmployee(employee);

        boolean result = employeeService.deleteEmployee(addedEmployee.getId());

        assertTrue(result);
        verify(employeeRepository, times(1)).existsById(addedEmployee.getId());
        verify(employeeRepository, times(1)).deleteById(addedEmployee.getId());
        verifyNoMoreInteractions(employeeRepository);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(addedEmployee.getId()));
    }

    @Test
    @DisplayName("Delete employee - EmployeeNotFoundException")
    void testDeleteEmployeeEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(1));
    }
}