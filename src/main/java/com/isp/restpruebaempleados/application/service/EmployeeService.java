package com.isp.restpruebaempleados.application.service;

import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;
import com.isp.restpruebaempleados.domain.model.Employee;
import com.isp.restpruebaempleados.infrastructure.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio para la gestión de empleados. Proporciona métodos para crear, actualizar, obtener y eliminar empleados
 * del repositorio subyacente.
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Crea un nuevo empleado con base en los datos proporcionados en la solicitud y lo guarda en el repositorio.
     *
     * @param request el {@link CreateEmployeeRequest} que contiene los detalles del nuevo empleado
     * @return un {@link EmployeeResponse} con los datos del empleado creado
     */
    public EmployeeResponse create(CreateEmployeeRequest request) {
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .maternalSurname(request.getMaternalSurname())
                .age(request.getAge())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .position(request.getPosition())
                .build();

        Employee saved = employeeRepository.save(employee);
        return mapToResponse(saved);
    }

    /**
     * Crea m&uacute;ltiples empleados con base en la lista de datos proporcionados y los guarda en el repositorio.
     *
     * @param requests una lista de instancias de {@link CreateEmployeeRequest}, cada una con los detalles de un nuevo empleado
     * @return una lista de instancias de {@link EmployeeResponse} que representan los empleados creados
     */
    public List<EmployeeResponse> createMany(List<@Valid CreateEmployeeRequest> requests) {
        return requests.stream()
                .map(this::create)
                .toList();
    }

    /**
     * Recupera todos los empleados del repositorio y los mapea a objetos de respuesta.
     *
     * @return una lista de instancias de {@link EmployeeResponse} que representan a todos los empleados
     */
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Elimina un empleado con base en el ID proporcionado. Si no existe un empleado con el ID indicado,
     * se lanza una excepci&oacute;n {@code EntityNotFoundException}.
     *
     * @param id el ID del empleado que se desea eliminar
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado
     */
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    /**
     * Actualiza un empleado existente con base en el ID proporcionado y la solicitud de actualización.
     * Si no existe un empleado con el ID indicado, se lanza una excepción {@code EntityNotFoundException}.
     *
     * @param id el ID del empleado que se desea actualizar
     * @param request el {@link UpdateEmployeeRequest} que contiene los nuevos datos del empleado
     * @return el {@link EmployeeResponse} que representa al empleado actualizado
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado
     */
    public EmployeeResponse update(Long id, UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

        Optional.ofNullable(request.getFirstName()).ifPresent(employee::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(employee::setLastName);
        Optional.ofNullable(request.getMaternalSurname()).ifPresent(employee::setMaternalSurname);
        Optional.ofNullable(request.getAge()).ifPresent(employee::setAge);
        Optional.ofNullable(request.getGender()).ifPresent(employee::setGender);
        Optional.ofNullable(request.getBirthDate()).ifPresent(employee::setBirthDate);
        Optional.ofNullable(request.getPosition()).ifPresent(employee::setPosition);

        Employee updated = employeeRepository.save(employee);
        return mapToResponse(updated);
    }

    private EmployeeResponse mapToResponse(Employee e) {
        return EmployeeResponse.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .maternalSurname(e.getMaternalSurname())
                .age(e.getAge())
                .gender(e.getGender())
                .birthDate(e.getBirthDate())
                .position(e.getPosition())
                .build();
    }
}
