package com.isp.restpruebaempleados.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Employee Response")
public class EmployeeResponse {
    @Schema(description = "Employee Id", example = "1")
    private Long id;
    @Schema(description = "Employee First Name", example = "Juan")
    private String firstName;
    @Schema(description = "Employee Second Name", example = "Arturo")
    private String secondName;
    @Schema(description = "Employee Last Name", example = "Perez")
    private String lastName;
    @Schema(description = "Maternal Surname", example = "Torres")
    private String maternalSurname;
    @Schema(description = "Employee Age", example = "25")
    private Integer age;
    @Schema(description = "Employee Gender", example = "MALE")
    private String gender;
    @Schema(description = "Employee Birth Date", example = "2022-01-01")
    private LocalDate birthDate;
    @Schema(description = "Employee Position", example = "Developer")
    private String position;
}
