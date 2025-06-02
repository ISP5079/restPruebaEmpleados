package com.isp.restpruebaempleados.application.service;

import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.mapper.EmployeeMapper;
import com.isp.restpruebaempleados.domain.model.Employee;
import com.isp.restpruebaempleados.domain.port.in.EmployeeService;
import com.isp.restpruebaempleados.domain.port.out.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository, Mappers.getMapper(EmployeeMapper.class));
    }

    @Test
    @DisplayName("Should create employee")
    void testCreate() {
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .firstName("Isabel")
                .position("Developer")
                .build();

        Employee saved = Employee.builder()
                .id(1L)
                .firstName("Isabel")
                .position("Developer")
                .build();

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(saved);

        var result = employeeService.createEmployee(request);

        verify(employeeRepository).save(any(Employee.class));
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Isabel", result.getFirstName());
        assertEquals("Developer", result.getPosition());
    }

    @Test
    @DisplayName("Should find all employees")
    void testFindAll() {
        when(employeeRepository.findAll()).thenReturn(List.of(
                Employee.builder().id(1L).firstName("Isabel").build(),
                Employee.builder().id(2L).firstName("Ma. de Jesus").build()
        ));

        var result = employeeService.findAllEmployees();

        assertEquals(2, result.size());
        assertEquals("Isabel", result.get(0).getFirstName());
        assertEquals("Ma. de Jesus", result.get(1).getFirstName());
    }

    @Test
    @DisplayName("Should delete employee by id")
    void testDeleteById_whenExists() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when employee not exists")
    void testDeleteById_whenNotExists() {
        when(employeeRepository.existsById(999L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployeeById(999L));
    }

    @Test
    @DisplayName("Should update employee")
    void testUpdate() {
        Employee existing = Employee.builder()
                .id(1L)
                .firstName("Israel")
                .build();

        UpdateEmployeeRequest updateRequest = UpdateEmployeeRequest.builder()
                .firstName("Isabel")
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(existing)).thenReturn(existing);

        var result = employeeService.updateEmployee(1L, updateRequest);

        assertEquals("Isabel", result.getFirstName());
        verify(employeeRepository).save(existing);
    }

    @Test
    @DisplayName("Should throw exception when employee not exists")
    void testUpdate_whenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        UpdateEmployeeRequest request = new UpdateEmployeeRequest();

        assertThrows(EntityNotFoundException.class, () -> employeeService.updateEmployee(1L, request));
    }
}