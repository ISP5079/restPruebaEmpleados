package com.isp.restpruebaempleados.adapter.mapper;

import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.domain.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toEmployeeResponse(Employee employee);
}
