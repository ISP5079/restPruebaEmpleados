package com.isp.restpruebaempleados.adapter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Update Employee Request")
public class UpdateEmployeeRequest {
    @Size(max = 50)
    @Schema(description = "First Name", example = "Juan")
    private String firstName;

    @Size(max = 50)
    @Schema(description = "Second Name", example = "Arturo")
    private String SecondName;

    @Size(max = 50)
    @Schema(description = "Last Name", example = "Perez")
    private String lastName;

    @Size(max = 50)
    @Schema(description = "Maternal Surname", example = "Torres")
    private String maternalSurname;

    @Schema(description = "Age", example = "25")
    private Integer age;

    @Size(max = 10)
    @Schema(description = "Gender", example = "MALE")
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Birth Date", example = "2022-01-01")
    private LocalDate birthDate;

    @Size(max = 100)
    @Schema(description = "Position", example = "Developer")
    private String position;
}
