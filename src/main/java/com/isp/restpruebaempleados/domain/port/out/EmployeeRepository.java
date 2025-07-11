package com.isp.restpruebaempleados.domain.port.out;

import com.isp.restpruebaempleados.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
