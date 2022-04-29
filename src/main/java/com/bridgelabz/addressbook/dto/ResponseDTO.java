package com.bridgelabz.addressbook.dto;

import lombok.Data;

@Data
/**
 * create a class name as ResponseDTO
 */
public class ResponseDTO {
    /**
     * variables
     */
    private String message;
    private Object data;

    /**
     * create a parameterized constructor
     * @param message - msg
     * @param data - person data
     */
    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }
}