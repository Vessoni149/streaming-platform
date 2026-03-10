package com.streaming.user_service.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T>{

    private LocalDateTime timestamp;  // Momento exacto en que se generó la respuesta
    private int statusCode;           // Código HTTP numérico (200, 404, etc.)
    private HttpStatus status;        // Enum de Spring que representa el estado HTTP
    private String message;           // Mensaje descriptivo de la respuesta
    private T data;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    //Respuesta a error simple
    public static ApiResponse error(HttpStatus status, String message) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .status(status)
                .message(message)
                .build();
    }

    //respuesta a error compleja
    public static <T> ApiResponse<T> error(HttpStatus status, T data, String message) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

}
