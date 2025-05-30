package com.isp.restpruebaempleados.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;
import com.isp.restpruebaempleados.application.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EmployeeService service;

    @Test
    @DisplayName("GET /employees/find/all should return list")
    void testGetAllEmployees() throws Exception {
        var mockList = List.of(
                EmployeeResponse.builder().id(1L).firstName("Israel").build(),
                EmployeeResponse.builder().id(2L).firstName("Isabel").build()
        );

        when(service.findAll()).thenReturn(mockList);

        mockMvc.perform(get("/employees/find/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Israel"))
                .andExpect(jsonPath("$[1].firstName").value("Isabel"));
    }

    @Test
    @DisplayName("POST /employees/create should return MethodArgumentNotValidException")
    void testArgumentNotValid() throws Exception {
        var request = CreateEmployeeRequest.builder().maternalSurname("Porras").build();

        var response = EmployeeResponse.builder().id(1L).build();

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /employees/create should create employee")
    void testCreateEmployee() throws Exception {
        var request = CreateEmployeeRequest.builder()
                .firstName("Fernando")
                .lastName("Hueso")
                .maternalSurname("Rivera")
                .age(30)
                .gender("MALE")
                .birthDate(LocalDate.of(2000, 5, 30))
                .position("Dev")
                .build();

        var response = EmployeeResponse.builder()
                .id(1L)
                .firstName("Fernando")
                .build();

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Fernando"));
    }

    @Test
    @DisplayName("POST /employees/create/many should create employee")
    void testCreateManyEmployee() throws Exception {
        var request = List.of(
                CreateEmployeeRequest.builder()
                        .firstName("Fernando")
                        .lastName("Hueso")
                        .maternalSurname("Rivera")
                        .age(30)
                        .gender("MALE")
                        .birthDate(LocalDate.of(1988, 3, 11))
                        .position("Dev")
                        .build(),
                CreateEmployeeRequest.builder()
                        .firstName("Israel")
                        .lastName("Sandoval")
                        .maternalSurname("Porras")
                        .age(30)
                        .gender("MALE")
                        .birthDate(LocalDate.of(1994, 6, 1))
                        .position("Dev")
                        .build()
        );

        var response = List.of(
                EmployeeResponse.builder()
                        .id(1L)
                        .firstName("Fernando")
                        .build(),
                EmployeeResponse.builder()
                        .id(2L)
                        .firstName("Isabel")
                        .build()
        );

        when(service.createMany(any())).thenReturn(response);

        mockMvc.perform(post("/employees/create/many")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("Fernando"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].firstName").value("Isabel"));
    }

    @Test
    @DisplayName("PUT /employees/create/{id} should update employee")
    void testUpdateEmployee() throws Exception {
        var request = UpdateEmployeeRequest.builder()
                .firstName("Israel")
                .build();

        var response = EmployeeResponse.builder()
                .id(1L)
                .firstName("Israel")
                .build();

        when(service.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/employees/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Israel"));
    }

    @Test
    @DisplayName("DELETE /employees/{id} should return 204")
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/delete/1"))
                .andExpect(status().isNoContent());
    }
}