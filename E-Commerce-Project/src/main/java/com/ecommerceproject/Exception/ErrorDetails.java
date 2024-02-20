package com.ecommerceproject.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String errorCode;
    private String message;
    private LocalDateTime timeStamp;
}
