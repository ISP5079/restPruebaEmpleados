package com.isp.restpruebaempleados.infrastructure.repository.impl;

import com.isp.restpruebaempleados.domain.model.Employee;
import com.isp.restpruebaempleados.domain.port.out.EmployeeRepository;
import com.isp.restpruebaempleados.infrastructure.repository.JpaEmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JpaEmployeeRepository jpaEmployeeRepository;

    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaEmployeeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaEmployeeRepository.existsById(id);
    }
}
