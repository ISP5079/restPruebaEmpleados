package com.isp.restpruebaempleados.adapter.controller;

import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;
import com.isp.restpruebaempleados.application.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que expone endpoints para la gesti&oacute;n de empleados.
 * Proporciona operaciones para crear, obtener, actualizar y eliminar empleados.
 */
@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "API REST para gestionar empleados")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    /**
     * Recupera una lista de todos los empleados.
     *
     * @return un {@link ResponseEntity} que contiene una lista de {@link EmployeeResponse},
     *         representando a todos los empleados.
     */
    @Operation(summary = "Get all employees")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/find/all")
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Crea un nuevo empleado con base en los datos proporcionados en la solicitud.
     *
     * @param request los detalles del empleado que se desea crear, proporcionados como
     *                una instancia de {@link CreateEmployeeRequest}
     * @return un {@link ResponseEntity} que contiene los detalles del empleado creado
     *         como una instancia de {@link EmployeeResponse}, con un estado HTTP 201 (Created)
     */
    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Crea múltiples empleados con base en la lista proporcionada de datos de solicitud de empleados.
     *
     * @param requests una lista de objetos {@link CreateEmployeeRequest}, cada uno con los detalles
     *                 de un empleado que se desea crear
     * @return un {@link ResponseEntity} que contiene una lista de objetos {@link EmployeeResponse}
     *         que representan a los empleados creados, con un estado HTTP 201 (Created)
     */
    @Operation(summary = "Create many employees")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/create/many")
    public ResponseEntity<List<EmployeeResponse>> createMany(
            @Valid @RequestBody List<CreateEmployeeRequest> requests) {
        List<EmployeeResponse> responses = service.createMany(requests);
        return new ResponseEntity<>(responses, HttpStatus.CREATED);
    }

    /**
     * Actualiza un empleado existente identificado por el ID proporcionado con la informaci&oacute;n
     * incluida en la solicitud de actualizaci&oacute;n.
     *
     * @param id el identificador &uacute;nico del empleado a actualizar
     * @param request los datos actualizados del empleado, representados como una instancia de
     *                {@link UpdateEmployeeRequest}
     * @return un {@link ResponseEntity} que contiene los detalles del empleado actualizado
     *         como una instancia de {@link EmployeeResponse} con estado HTTP 200 (OK)
     */
    @Operation(summary = "Update an employee by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateEmployeeRequest request) {
        EmployeeResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Elimina un empleado identificado por el ID proporcionado.
     *
     * @param id el identificador único del empleado que se desea eliminar
     * @return un {@link ResponseEntity} sin contenido y con estado HTTP 204 (No Content)
     *         si la eliminación es exitosa
     */
    @Operation(summary = "Delete an employee by ID")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
