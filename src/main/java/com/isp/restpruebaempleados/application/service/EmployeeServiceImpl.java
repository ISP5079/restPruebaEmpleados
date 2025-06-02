package com.isp.restpruebaempleados.application.service;

import com.isp.restpruebaempleados.adapter.dto.CreateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.dto.EmployeeResponse;
import com.isp.restpruebaempleados.adapter.dto.UpdateEmployeeRequest;
import com.isp.restpruebaempleados.adapter.mapper.EmployeeMapper;
import com.isp.restpruebaempleados.domain.model.Employee;
import com.isp.restpruebaempleados.domain.port.in.EmployeeService;
import com.isp.restpruebaempleados.domain.port.out.EmployeeRepository;
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
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private static final String NOT_FOUND_MESSAGE = "Employee not found with ID: %d";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Crea un nuevo empleado con base en los datos proporcionados en la solicitud y lo guarda en el repositorio.
     *
     * @param request el {@link CreateEmployeeRequest} que contiene los detalles del nuevo empleado
     * @return un {@link EmployeeResponse} con los datos del empleado creado
     */
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .lastName(request.getLastName())
                .maternalSurname(request.getMaternalSurname())
                .age(request.getAge())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .position(request.getPosition())
                .build();

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(saved);
    }

    /**
     * Crea m&uacute;ltiples empleados con base en la lista de datos proporcionados y los guarda en el repositorio.
     *
     * @param requests una lista de instancias de {@link CreateEmployeeRequest}, cada una con los detalles de un nuevo empleado
     * @return una lista de instancias de {@link EmployeeResponse} que representan los empleados creados
     */
    public List<EmployeeResponse> createMany(List<@Valid CreateEmployeeRequest> requests) {
        return requests.stream()
                .map(this::createEmployee)
                .toList();
    }

    /**
     * Recupera todos los empleados del repositorio y los mapea a objetos de respuesta.
     *
     * @return una lista de instancias de {@link EmployeeResponse} que representan a todos los empleados
     */
    public List<EmployeeResponse> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeResponse)
                .toList();
    }

    /**
     * Elimina un empleado con base en el ID proporcionado. Si no existe un empleado con el ID indicado,
     * se lanza una excepci&oacute;n {@code EntityNotFoundException}.
     *
     * @param id el ID del empleado que se desea eliminar
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado
     */
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id));
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
    public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));

        Optional.ofNullable(request.getFirstName()).ifPresent(employee::setFirstName);
        Optional.ofNullable(request.getSecondName()).ifPresent(employee::setSecondName);
        Optional.ofNullable(request.getLastName()).ifPresent(employee::setLastName);
        Optional.ofNullable(request.getMaternalSurname()).ifPresent(employee::setMaternalSurname);
        Optional.ofNullable(request.getAge()).ifPresent(employee::setAge);
        Optional.ofNullable(request.getGender()).ifPresent(employee::setGender);
        Optional.ofNullable(request.getBirthDate()).ifPresent(employee::setBirthDate);
        Optional.ofNullable(request.getPosition()).ifPresent(employee::setPosition);

        Employee updated = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(updated);
    }
}
