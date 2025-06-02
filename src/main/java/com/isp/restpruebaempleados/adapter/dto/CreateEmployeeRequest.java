package com.isp.restpruebaempleados.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Create Employee Request")
public class CreateEmployeeRequest {
    @NotBlank
    @Size(max = 50)
    @Schema(description = "First Name", example = "Juan")
    private String firstName;

    @Size(max = 50)
    @Schema(description = "Second Name", example = "Arturo")
    private String secondName;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Last Name", example = "Perez")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Maternal Surname", example = "Torres")
    private String maternalSurname;

    @NotNull
    @Schema(description = "Age", example = "25")
    private Integer age;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "Gender", example = "MALE")
    private String gender;

    @NotNull
    @Past
    @Schema(description = "Birth Date", example = "2022-01-01")
    private LocalDate birthDate;

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Position", example = "Developer")
    private String position;
}
