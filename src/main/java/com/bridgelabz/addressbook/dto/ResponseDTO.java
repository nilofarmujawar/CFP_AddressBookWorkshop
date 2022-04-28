package com.bridgelabz.addressbook.dto;

/**
 * Created ResponseBookDTO class
 */
public class ResponseDTO {
    /**
     * variables
     */
    private String message;
    private Object data;

    /**
     * create parameterized constructor
     * @param message - msg
     * @param data - person data
     */
    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }

    /**
     * Created getter and setter
     *
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}