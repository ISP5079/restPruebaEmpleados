package com.isp.restpruebaempleados.infrastructure.repository;


import com.isp.restpruebaempleados.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long> {
}
