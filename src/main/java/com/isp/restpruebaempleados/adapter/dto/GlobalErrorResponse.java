package com.isp.restpruebaempleados.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Error Response")
public class GlobalErrorResponse {
    @Schema(description = "Employee Birth Date", example = "2022-01-01")
    LocalDateTime timestamp;
    @Schema(description = "Status", example = "1")
    int status;
    @Schema(description = "Error", example = "Bad Request")
    String error;
    @Schema(description = "Message", example = "Invalid request")
    String message;
    @Schema(description = "Errors", example = """
            
            """)
    Map<String, String> errors;

    public GlobalErrorResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }

    public GlobalErrorResponse(HttpStatus status, String message, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.errors = errors;
    }
}
