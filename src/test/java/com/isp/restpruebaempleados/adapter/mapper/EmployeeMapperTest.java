package com.isp.restpruebaempleados.adapter.mapper;

import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void testToResponse() {
        Employee employee = Employee.builder()
                .id(10L)
                .firstName("Mario")
                .secondName("Alberto")
                .lastName("López")
                .position("QA")
                .build();

        EmployeeResponse response = mapper.toEmployeeResponse(employee);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Mario", response.getFirstName());
        assertEquals("Alberto", response.getSecondName());
        assertEquals("López", response.getLastName());
        assertEquals("QA", response.getPosition());
    }

}