package com.isp.restpruebaempleados.domain.port.in;

import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest employeeResponse);
    List<EmployeeResponse> createMany(List<CreateEmployeeRequest> employeeResponses);
    List<EmployeeResponse> findAllEmployees();
    void deleteEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request);
}
