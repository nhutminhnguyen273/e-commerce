package com.example.shared_library.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private T data;

    public Response(String message, T data) {
        this.success = true;
        this.statusCode = HttpStatus.OK.value();
        this.message = message;
        this.data = data;
    }

    public Response(boolean success, int statusCode, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
    }
}
